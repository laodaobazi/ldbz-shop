package cn.ldbz.cart.controller;

import cn.ldbz.cart.service.CartService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.CartInfo;
import cn.ldbz.pojo.TbUser;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.utils.CookieUtils;
import cn.ldbz.utils.FastJsonConvert;
import com.alibaba.dubbo.config.annotation.Reference;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 购物车 Controller
 */
@Controller
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

//    @Reference(version = Const.LDBZ_SHOP_CART_VERSION)
//    private CartService cartService;
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

        String cookieUUID = CookieUtils.getCookieValue(request, Const.CART_KEY);
        String tokenLogin = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);
    	
    	model.addAttribute("itemUrl", NGINX_ITEM_URL);
    	model.addAttribute("searchUrl", SEARCH_WEB_URL);
    	model.addAttribute("nginxImage", INDEX_NGINX_IMAGE_URL);

        TbUser user = null;
        String userJson = null;
        if (StringUtils.isNoneEmpty(tokenLogin)) {

            try {
                userJson = jedisClient.get(USER_SESSION + tokenLogin);
            } catch (Exception e) {
                logger.error("Redis 错误", e);
            }

            if (StringUtils.isNoneEmpty(userJson)) {
                user = FastJsonConvert.convertJSONToObject(userJson, TbUser.class);
            }
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", user);
        }

//        List<CartInfo> cartInfos = new ArrayList<>();
//        if (StringUtils.isNoneEmpty(cookieUUID)) {
//            cartInfos= cartService.getCartInfoListByCookiesId(cookieUUID);
//        }

//        if (cartInfos.size() == 0) {
//            model.addAttribute("cartInfos", null);
//            return "cart";
//        }

//        int totalPrice = 0;
//
//        for (int i = 0; i < cartInfos.size(); i++) {
//            CartInfo cartInfo = cartInfos.get(i);
//            totalPrice += cartInfo.getSum();
//        }
//
//        model.addAttribute("cartInfos", cartInfos);
//        model.addAttribute("totalPrice", totalPrice);

        return "cart";
    }

    @RequestMapping("/add")
    public String addCart(Long pid, Integer pcount, HttpServletRequest request, HttpServletResponse response, Model model) {
        String cookieUUID = CookieUtils.getCookieValue(request, Const.CART_KEY);
        if (StringUtils.isBlank(cookieUUID)) {
//            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//            CookieUtils.setCookie(request, response, Const.CART_KEY, uuid);
//            LdbzResult result = cartService.addCart(pid, pcount, uuid);
//            model.addAttribute("cartInfo", (CartInfo) result.getData());
            return "success";
        } else {
//            LdbzResult result = cartService.addCart(pid, pcount, cookieUUID);
//            model.addAttribute("cartInfo", (CartInfo)result.getData());
            return "success";
        }

    }

    /**
     * 根据商品id和数量对购物车增加商品或减少商品
     *
     * @param pid       商品id
     * @param pcount    增加数量
     * @param type      1 增加 2 减少
     * @param index     商品位置   ps:用于直接定位商品 不用遍历整个购物车
     * @param request
     * @param response
     * @param model
     * @return
     */
//    @RequestMapping("/decreOrIncre")
//    @ResponseBody
//    public LdbzResult decreOrIncre(Long pid, Integer pcount,Integer type,Integer index, HttpServletRequest request, HttpServletResponse response, Model model) {
//        String cookieUUID = CookieUtils.getCookieValue(request, Const.CART_KEY);
//        if (StringUtils.isBlank(cookieUUID)) {
//            model.addAttribute("msg","没有此Cookie!");
//            return LdbzResult.build(400,"请先去购物!");
//        } else {
//            return cartService.decreOrIncre(pid, pcount,type,index, cookieUUID);
//        }
//
//    }

}
