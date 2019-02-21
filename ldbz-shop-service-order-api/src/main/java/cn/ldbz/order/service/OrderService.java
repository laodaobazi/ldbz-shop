package cn.ldbz.order.service;

import java.util.List;

import cn.ldbz.pojo.LdbzCart;
import cn.ldbz.pojo.LdbzOrder;
import cn.ldbz.pojo.LdbzResult;

public interface OrderService {
	
	LdbzResult countOrder(LdbzOrder entity);

	LdbzResult getOrderPage(LdbzOrder entity , int pn , int limit) ;
	
	LdbzResult selectByKey(Long id);
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult updateStatusByKey(LdbzOrder entity);

	boolean generateOrder(String userCookieValue, List<LdbzCart> cookieItems, String orderItemCodes, String orderNums,
			String address, float shippingPrice);
	
}
