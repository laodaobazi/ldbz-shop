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

    @Value("${redisKey.prefix.user_session}")
    private String USER_SESSION;


    //图片的URL路径
    @Value("${redisKey.nginxImage.url.key}")
    private String INDEX_NGINX_IMAGE_URL;
    //商品的URL路径
    @Value("${redisKey.item.url.key}")
    private String NGINX_ITEM_URL;
    //检索访问的URL路径
    @Value("${search.web.url}")
    private String SEARCH_WEB_URL;


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
				case "redisKey.item.url.key" : 
					NGINX_ITEM_URL = change.getNewValue();
				case "search.web.url" : 
					SEARCH_WEB_URL = change.getNewValue();
				case "redisKey.prefix.user_session" : 
					USER_SESSION = change.getNewValue();
			}
		}
	}


    @RequestMapping("/cart")
    public String showCart( HttpServletRequest request, HttpServletResponse response,Model model) {
    	
    	model.addAttribute("itemUrl", NGINX_ITEM_URL);
    	model.addAttribute("searchUrl", SEARCH_WEB_URL);
    	model.addAttribute("nginxImage", INDEX_NGINX_IMAGE_URL);

        String tokenLogin = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);

        if (StringUtils.isNoneEmpty(tokenLogin)) {
        	LdbzUser user = null;
        	String userJson = null;
            try {
                userJson = jedisClient.get(USER_SESSION + tokenLogin);
            } catch (Exception e) {
                logger.error("Redis 错误", e);
            }
            if (StringUtils.isNoneEmpty(userJson)) {
                user = FastJsonConvert.convertJSONToObject(userJson, LdbzUser.class);
                
                String cookieCart = CookieUtils.getCookieValue(request, Const.CART_KEY);
                if(StringUtils.isNoneEmpty(cookieCart)) {
                	//如果cookie里面不等于空，则使用浏览器cookie里面的值
                	String itemsJSON = new String(Base64Utils.decodeFromString(cookieCart));
                	cartService.setCartListByUserId(user.getId() , itemsJSON);
//                	List<LdbzCart> itemsObj = FastJsonConvert.convertJSONToArray(itemsJSON, LdbzCart.class);
                }else {
                	//如果cookie里面没有商品，则到redis里面获取
                	String itemsJSON= cartService.getCartListByUserId(user.getId());
                	CookieUtils.setCookie(request, response, Const.CART_KEY, Base64Utils.encodeToString(itemsJSON.getBytes()), Integer.MAX_VALUE);
                }
                
            }
            model.addAttribute("user", user);
        }

        return "cart";
    }

}
