package cn.ldbz.cart.service;

/**
 * 购物车相关操作
 */
public interface CartService {

    String getCartListByUserId(Long userId);
    
    Long getCartOptTime(Long userId);

    boolean setCartListByUserId(Long userId , String items);
    
}
