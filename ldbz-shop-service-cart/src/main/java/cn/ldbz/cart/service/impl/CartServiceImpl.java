package cn.ldbz.cart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import cn.ldbz.cart.service.CartService;
import cn.ldbz.constant.Const;
import cn.ldbz.redis.service.JedisClient;

/**
 * 购物车操作实现
 *
 */
@Component
@Service(version = Const.LDBZ_SHOP_CART_VERSION)
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Value("${redisKey.prefix.cart_info_profix}")
    private String CART_INFO_PROFIX;
    @Value("${redisKey.prefix.redis_cart_expire_time}")
    private Integer REDIS_CART_EXPIRE_TIME;

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;


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
				case "redisKey.prefix.cart_info_profix" : 
					CART_INFO_PROFIX = change.getNewValue();
				case "redisKey.prefix.redis_cart_expire_time" : 
					REDIS_CART_EXPIRE_TIME = Integer.valueOf(change.getNewValue());
			}
		}
	}

	@Override
	public String getCartListByUserId(Long userId) {
		logger.debug("getCartListByUserId  userId:{}" , userId);
		return jedisClient.get(CART_INFO_PROFIX + userId);
	}

	@Override
	public boolean setCartListByUserId(Long userId, String items) {
		String key = CART_INFO_PROFIX + userId ;
		logger.debug("setCartListByUserId  userId:{}  ,  items:{}" , userId , items);
		jedisClient.set(key , items);
		jedisClient.expire(key , REDIS_CART_EXPIRE_TIME);
		return true;
	}

}
