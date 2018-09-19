package cn.ldbz.cart.service;


import cn.ldbz.pojo.CartInfo;
import cn.ldbz.pojo.LdbzResult;

import java.util.List;

/**
 * 购物车相关操作 Service
 *
 */

public interface CartService {

    LdbzResult addCart(Long pid, Integer pcount, String uuid);

    List<CartInfo> getCartInfoListByCookiesId(String cookieUUID);

    /**
     *
     * 根据商品id和数量对购物车增加商品或减少商品
     *
     * @param pid       商品id
     * @param pcount    增加数量
     * @param type      1 增加 2 减少
     * @param index     商品位置   ps:用于直接定位商品 不用遍历整个购物车
     * @return
     */
    LdbzResult decreOrIncre(Long pid, Integer pcount, Integer type, Integer index, String cookieUUID);


}
