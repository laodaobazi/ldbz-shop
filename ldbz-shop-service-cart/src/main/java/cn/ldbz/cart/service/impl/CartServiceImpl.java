package cn.ldbz.cart.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.cart.service.CartService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzCart;
import cn.ldbz.redis.service.JedisClient;

/**
 * 购物车操作实现
 *
 */
@Component
@Service(version = Const.LDBZ_SHOP_CART_VERSION)
@Transactional
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Value("${redisKey.prefix.cart_info_profix}")
    private String CART_INFO_PROFIX;
    @Value("${redisKey.prefix.redis_cart_expire_time}")
    private Integer REDIS_CART_EXPIRE_TIME;
    @Value("${redisKey.prefix.item_info_profix}")
    private String ITEM_INFO_PROFIX;
    @Value("${redisKey.prefix.item_info_base_suffix}")
    private String ITEM_INFO_BASE_SUFFIX;

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    @Override
    public List<LdbzCart> getCartInfoListByCookiesId(String cookieUUID) {

//        String cartInfoString = jedisClient.get(CART_INFO_PROFIX + cookieUUID);

//        if (StringUtils.isNotBlank(cartInfoString)) {
//            List<CartInfo> cartInfos = FastJsonConvert.convertJSONToArray(cartInfoString, CartInfo.class);
//
//            return cartInfos;
//        }

        return null;
    }

}
