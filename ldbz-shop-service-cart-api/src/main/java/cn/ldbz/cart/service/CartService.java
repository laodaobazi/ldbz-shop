package cn.ldbz.cart.service;


import java.util.List;

import cn.ldbz.pojo.LdbzCart;

/**
 * 购物车相关操作
 *
 */
public interface CartService {

    List<LdbzCart> getCartInfoListByCookiesId(String cookieUUID);

}
