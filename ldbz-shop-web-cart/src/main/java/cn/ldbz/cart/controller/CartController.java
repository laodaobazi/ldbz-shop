package cn.ldbz.cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import cn.ldbz.cart.service.CartService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzUser;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.utils.CookieUtils;
import cn.ldbz.utils.FastJsonConvert;

/**
 * 购物车 Controller
 */
@Controller
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Reference(version = Const.LDBZ_SHOP_CART_VERSION)
    private CartService cartService;
    
    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    //图片的URL路径
    @Value("${redisKey.nginxImage.url.key}")
    private String INDEX_NGINX_IMAGE_URL;


    /**
     * 监听配置项是否有修改
     */
    @ApolloConfigChangeListener
	public void onChange(ConfigChangeEvent changeEvent) {
		for (String key : changeEvent.changedKeys()) {
			ConfigChange change = changeEvent.getChange(key);
			logger.debug("Found change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
					change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType());
			switch(key) {
				case "redisKey.nginxImage.url.key" : 
					INDEX_NGINX_IMAGE_URL = change.getNewValue();
			}
		}
	}


    @RequestMapping("/cart")
    public String showCart( HttpServletRequest request, HttpServletResponse response,Model model) {
    	
    	model.addAttribute("nginxImage", INDEX_NGINX_IMAGE_URL);

        String tokenLogin = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);

        //如果用户在线
        if (StringUtils.isNoneEmpty(tokenLogin)) {
        	String userJson = null;
            try {
                userJson = jedisClient.get(Const.REDIS_KEY_USER_SESSION + tokenLogin);
            } catch (Exception e) {
                logger.error("Redis 错误", e);
            }
            if (StringUtils.isNoneEmpty(userJson)) {
            	//当前登录用户
            	LdbzUser user = FastJsonConvert.convertJSONToObject(userJson, LdbzUser.class);
                
                String cookieCart = CookieUtils.getCookieValue(request, Const.CART_KEY);
                if(StringUtils.isNoneEmpty(cookieCart)) {
                	//如果cookie里面不等于空，则获取cookie的最后更新的时间
                	String cookieTimestamp = CookieUtils.getCookieValue(request, Const.CART_TIMESTAMP_KEY);
                	Long redisTimestamp = cartService.getCartOptTime(user.getId());
                	if(StringUtils.isNoneEmpty(cookieTimestamp) && Long.valueOf(cookieTimestamp)>redisTimestamp) {
                		//如果cookie中的商品比redis里面维护的时间比较新，则将cookie里面的写入redis
                		String itemsJSON = new String(Base64Utils.decodeFromString(cookieCart));
                		cartService.setCartListByUserId(user.getId() , itemsJSON);
                	}else {
                		//否则使用redis中的商品覆盖到cookie中额商品
                		String itemsJSON= cartService.getCartListByUserId(user.getId());
                		CookieUtils.setCookie(request, response, Const.CART_KEY, Base64Utils.encodeToString(itemsJSON.getBytes()), 2592000);//30天
                		CookieUtils.setCookie(request, response, Const.CART_TIMESTAMP_KEY, String.valueOf(System.currentTimeMillis()), 2592000);//30天
                	}
                }else {
                	//如果cookie里面没有商品，则到redis里面获取
                	String itemsJSON= cartService.getCartListByUserId(user.getId());
                	CookieUtils.setCookie(request, response, Const.CART_KEY, Base64Utils.encodeToString(itemsJSON.getBytes()), 2592000);//30天
                	CookieUtils.setCookie(request, response, Const.CART_TIMESTAMP_KEY, String.valueOf(System.currentTimeMillis()), 2592000);//30天
                }
            }
        }
        return "cart";
    }

}
