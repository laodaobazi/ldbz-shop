package cn.ldbz.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.cart.service.CartService;
import cn.ldbz.constant.Const;
import cn.ldbz.item.service.ItemService;
import cn.ldbz.mapper.LdbzOrderItemMapper;
import cn.ldbz.mapper.LdbzOrderMapper;
import cn.ldbz.order.service.OrderService;
import cn.ldbz.pojo.LdbzCart;
import cn.ldbz.pojo.LdbzOrder;
import cn.ldbz.pojo.LdbzOrderItem;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzUser;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.sso.service.UserService;
import cn.ldbz.utils.IDUtils;

/**
 * 订单Service
 *
 */
@Component
@Service(version = Const.LDBZ_SHOP_ORDER_VERSION)
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Reference(version = Const.LDBZ_SHOP_SSO_VERSION)
    private UserService userService;

    @Reference(version = Const.LDBZ_SHOP_ITEM_VERSION)
    private ItemService itemService;

    @Reference(version = Const.LDBZ_SHOP_CART_VERSION)
    private CartService cartService;
    
    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    @Autowired
    private LdbzOrderMapper orderMapper;
    
    @Autowired
    private LdbzOrderItemMapper orderItemMapper;

    //@Autowired
    //private JmsTemplate jmsTemplate;

	@Override
	public LdbzResult countOrder(LdbzOrder entity) {
		long total = orderMapper.countByEntity(entity);
		return LdbzResult.ok(total);
	}

	@Override
	public LdbzResult getOrderPage(LdbzOrder entity, int pn, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = orderMapper.countByEntity(entity);
		map.put("total", total) ;
		logger.debug("getOrderPage count : {} " , total);
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzOrder> ret = orderMapper.selectByEntity(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(200, "", map);
	}

	@Override
	public LdbzResult selectByKey(Long id) {
		logger.debug("selectByKey id : {} " , id);
		return LdbzResult.ok(orderMapper.selectByKey(id));
	}

	@Override
	@Transactional
	public LdbzResult deleteByKey(String id) {
		logger.debug("deleteByKey id : {} " , id);
		if(StringUtils.contains(id, ",")) {
			List<Long> ids = new ArrayList<Long>();
			for(String _id : StringUtils.split(id , ",")) {
				ids.add(Long.valueOf(_id));
			}
			orderItemMapper.deleteByKeys(ids);
			return LdbzResult.ok(orderMapper.deleteByKeys(ids));
		}else {
			orderItemMapper.deleteByKey(Long.valueOf(id));
			return LdbzResult.ok(orderMapper.deleteByKey(Long.valueOf(id)));
		}
	}

	@Override
	@Transactional
	public boolean generateOrder(String userCookieValue , List<LdbzCart> cookieItems , String  orderItemCodes , String  orderNums , String  address , float shippingPrice) {
        
		//获取当前在线用户
		LdbzUser user = userService.token(userCookieValue);
        long orderCode = IDUtils.genOrderId() ;
        float totalPrice = 0f ;
        
        //订单中的商品条目
        List<LdbzOrderItem> orderItems = new ArrayList<LdbzOrderItem>();
        
        //获取下订单的商品
        if(StringUtils.isNoneEmpty(orderItemCodes)) {
        	String[] itemArr = orderItemCodes.split(",") ; 
        	String[] numArr = orderNums.split(",") ; 
        	for(int i=0; i<itemArr.length; i++) {
        		
        		String itemCode = itemArr[i] ;//下单的商品编号
        		int num = Integer.valueOf(numArr[i]);//下单的商品数量
        		
        		for(LdbzCart cart : cookieItems) {
        			if(cart.getItem_code().equals(itemCode)) {
        				LdbzOrderItem orderItem = new LdbzOrderItem();
        				orderItem.setImage(cart.getItem_image());
        				orderItem.setNum(num);
        				orderItem.setOrderCode(orderCode);
        				orderItem.setTitle(cart.getItem_title());
        				//到库里面获取实时的价格
        				float price = itemService.getItemPriceByCode(cart.getItem_code());
        				totalPrice += (price*num);
        				orderItem.setPrice(price);
        			}
        		}
        	}
        }
		//订单实体
		LdbzOrder order = new LdbzOrder();
       order.setOrderCode(orderCode);
       order.setCreated(new Date());
       order.setCreator(user.getId());
       order.setAddress(address);
       order.setStatus(0);
       order.setTotalPrice(totalPrice);
       order.setShippingPrice(shippingPrice);
        
		logger.debug("insertByEntity order : {} " , order);
		//添加订单
		if(orderMapper.insertByEntity(order)>=1) {
			//添加订单条目
			for(LdbzOrderItem orderItem : orderItems) {
				orderItemMapper.insertByEntity(orderItem);
			}
			//清空购物车
			cartService.deleteCartListByUserId(order.getCreator());
			return true ;
		}else {
			return false;
		}
	}

	@Override
	public LdbzResult updateStatusByKey(LdbzOrder entity) {
		logger.debug("updateStatusByKey entity : {} " , entity);
		//支付成功之后修改状态，并且要减库存，两个库分布式事务
		
		return LdbzResult.ok(orderMapper.updateByKey(entity));
	}
	
}
