package cn.ldbz.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.cart.service.CartService;
import cn.ldbz.constant.Const;
import cn.ldbz.mapper.LdbzOrderItemMapper;
import cn.ldbz.mapper.LdbzOrderMapper;
import cn.ldbz.order.service.OrderService;
import cn.ldbz.pojo.LdbzOrder;
import cn.ldbz.pojo.LdbzOrderItem;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzUser;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.sso.service.UserService;

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

    @Value("${redisKey.prefix.cart_order_info_profix}")
    private String CART_ORDER_INFO_PROFIX;
    @Value("${redisKey.prefix.cart_order_index_profix}")
    private String CART_ORDER_INDEX_PROFIX;
    @Value("${redisKey.prefix.cart_info_profix}")
    private String CART_INFO_PROFIX;
    


    
    

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
	public LdbzResult insertByEntity(LdbzOrder order , List<LdbzOrderItem> orderItems) {
		logger.debug("insertByEntity order : {} " , order);
		//添加订单
		if(orderMapper.insertByEntity(order)>=1) {
			//添加订单条目
			for(LdbzOrderItem orderItem : orderItems) {
//				orderItem.setOrderCode(orderCode);
				orderItemMapper.insertByEntity(orderItem);
			}
			//清空购物车
			cartService.deleteCartListByUserId(order.getCreator());
			return LdbzResult.ok();
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}

	@Override
	public LdbzResult updateStatusByKey(LdbzOrder entity) {
		logger.debug("updateStatusByKey entity : {} " , entity);
		return LdbzResult.ok(orderMapper.updateByKey(entity));
	}
	
	
	
	
	
	
	
	
    
    

    public LdbzResult generateOrder(String userCookieValue, String cartCookieValue, Integer addrId, Integer noAnnoyance, Integer paymentType, String orderId, String shippingName) {

    	LdbzUser user = userService.token(userCookieValue);
        if (user == null) {
            logger.error("用户没有登录!");
            return LdbzResult.build(400, "系统错误!");
        }

        String userId = user.getId() + "";
        userId = "0000" + userId;
        userId = userId.substring(userId.length() - 4, userId.length());

        String key1 = CART_ORDER_INFO_PROFIX + orderId;
        String key2 = CART_ORDER_INDEX_PROFIX + orderId;
        String key3 = CART_INFO_PROFIX + cartCookieValue;

        orderId = paymentType.toString() + orderId + userId;

//        final TbOrder order = new TbOrder();
//        //设置订单id
//        order.setOrderId(orderId);
//        //设置用户id
//        order.setUserId(user.getId());
//        //设置地址id
//        order.setAddrId(Long.valueOf(addrId));
//        //设置支付类型
//        order.setPaymentType(paymentType);
//        //设置邮费
//        order.setPostFee("0");
//        //设置状态
//        order.setStatus(Const.NON_PAYMENT);
//        //设置物流名称
//        order.setShippingName(shippingName);
//        //设置退换无忧
//        order.setNoAnnoyance(noAnnoyance + "");
//        //设置服务费
//        order.setServicePrice("0");
//        //设置返现
//        order.setReturnPrice("0");
//        //设置没有评价
//        order.setBuyerRate(Const.EVALUATE_NO);
//        //设置订单创建时间
//        order.setCreateTime(new Date());
//        order.setUpdateTime(new Date());

        Long payment = 0L;
//        List<CartInfo> cartInfos = null;
//        List<CartInfo> cartInfoAll = null;
        String[] split = null;

        try {

            String cartInfo = jedisClient.get(key1);
            String cartIndex = jedisClient.get(key2);
            String cartInfoListString = jedisClient.get(key3);

            if (StringUtils.isBlank(cartInfo) || StringUtils.isBlank(cartIndex) || StringUtils.isBlank(cartInfoListString)) {
                return LdbzResult.build(400, "系统错误!");
            }

//            cartInfos = FastJsonConvert.convertJSONToArray(cartInfo, CartInfo.class);
//            cartInfoAll = FastJsonConvert.convertJSONToArray(cartInfoListString, CartInfo.class);
            split = cartIndex.split(",");

        } catch (Exception e) {
            logger.error("Redis 服务出错!", e);
        }

        // 保存订单项️
//        if (cartInfos.size() > 0) {
//            for (int i = 0; i < cartInfos.size(); i++) {
//                CartInfo cartInfo = cartInfos.get(i);
//
//                String orderItemId = IDUtils.genOrderItemId();
//                TbOrderItem orderItem = new TbOrderItem();
//                orderItem.setId(orderItemId);
//                orderItem.setOrderId(orderId);
//                orderItem.setItemId(cartInfo.getId() + "");
//                orderItem.setTitle(cartInfo.getName());
//                orderItem.setNum(cartInfo.getNum());
//                orderItem.setPicPath(cartInfo.getImageUrl());
//                orderItem.setPrice(cartInfo.getPrice());
//                orderItem.setTotalFee(cartInfo.getSum());
//                orderItem.setWeights(cartInfo.getWeight() + "");
//                // 记录日志
//                orderItemMapper.insert(orderItem);
//
//                logger.info("保存订单项,订单:" + orderItem.toString());
//                payment += cartInfo.getSum();
//            }
//        }

        //设置总金额
//        order.setPayment(payment + noAnnoyance + "");
//        //设置总重
//        //order.setTotalWeight();
//        // 保存订单
//        orderMapper.insert(order);


        // 移除购物车选中商品️
//        if (cartInfoAll.size() >= split.length) {
//            for (int i = split.length - 1; i >= 0; i--) {
//                cartInfoAll.remove(Integer.parseInt(split[i]));
//            }
//            logger.debug("移除购物车购买商品！数量:" + split.length);
//        } else {
//            logger.error("订单项数量小于和index数量");
//            return LdbzResult.build(400, "系统错误!");
//        }

        try {

//            jedisClient.set(key3, FastJsonConvert.convertObjectToJSON(cartInfoAll));

        } catch (Exception e) {
            logger.error("Redis 服务出错!", e);
        }

//        final String orderString = FastJsonConvert.convertObjectToJSON(order);
        // 发送消息 topic
        //jmsTemplate.send(destination, new MessageCreator() {
        //    @Override
        //    public Message createMessage(Session session) throws JMSException {
        //
        //        TextMessage message = session.createTextMessage(orderString);
        //
        //        logger.info("发送JMS消息,消息为:" + orderString);
        //
        //        return message;
        //    }
        //});

        return null;
    }








}
