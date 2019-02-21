package cn.ldbz.order.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import cn.ldbz.constant.Const;
import cn.ldbz.order.service.OrderService;
import cn.ldbz.pojo.LdbzCart;
import cn.ldbz.pojo.LdbzOrder;
import cn.ldbz.pojo.LdbzOrderItem;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.sso.service.UserService;
import cn.ldbz.utils.CookieUtils;
import cn.ldbz.utils.FastJsonConvert;
import cn.ldbz.utils.IDUtils;

/**
 * 订单Controller
 */
@Controller
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Value("${redisKey.prefix.cart_order_info_profix}")
    private String CART_ORDER_INFO_PROFIX;
    @Value("${redisKey.expire_time}")
    private Integer REDIS_ORDER_EXPIRE_TIME;
    @Value("${redisKey.prefix.cart_order_index_profix}")
    private String CART_ORDER_INDEX_PROFIX;

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    @Reference(version = Const.LDBZ_SHOP_ORDER_VERSION)
    private OrderService orderService;


    @RequestMapping("/order/getOrderInfo")
    public String showOrder(String ids, String nums, Model model, HttpServletResponse response, HttpServletRequest request) {

        String userCookieValue = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);

//        List<CartInfo> cartInfoList = new ArrayList<>();
//        List<CartInfo> cartInfos = null;
        int totalPrices = 0;

//        String orderId = IDUtils.genOrderId();
//        String key = CART_ORDER_INFO_PROFIX + orderId;
//        String key2 = CART_ORDER_INDEX_PROFIX + orderId;
        // 保存商品订单项
//        jedisClient.set(key, FastJsonConvert.convertObjectToJSON(cartInfoList));
//        // 保存商品Index --用于购物完成后删除购物车商品
//        jedisClient.set(key2, indexs);
//        jedisClient.expire(key, REDIS_ORDER_EXPIRE_TIME);
//        jedisClient.expire(key2, REDIS_ORDER_EXPIRE_TIME);

        model.addAttribute("totalPrices", totalPrices);
//        model.addAttribute("orderId", orderId);
//        model.addAttribute("cartInfos", cartInfoList);

        return "order";
    }

    /**
     * 提交订单
     * @param itemCodes 购买所有商品的ID
     * @param nums 购买商品的数量
     * @param address	邮寄地址
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/order/getPay")
    public String getPay(String orderItemCodes , String orderNums , String address , float shippingPrice , HttpServletResponse response, HttpServletRequest request) {

    	//获取cookie购物车中的商品
        String cartCookieValue = CookieUtils.getCookieValue(request, Const.CART_KEY);
        String itemsJSON = new String(Base64Utils.decodeFromString(cartCookieValue));
        List<LdbzCart> cookieItems = FastJsonConvert.convertJSONToArray(itemsJSON, LdbzCart.class);
        //获取当前在线人员
        String userCookieValue = CookieUtils.getCookieValue(request, Const.TOKEN_LOGIN);

        orderService.generateOrder(userCookieValue,cookieItems,orderItemCodes, orderNums, address, shippingPrice);
        return "success";
    }
}
