package cn.ldbz.wishlist.service;

import cn.ldbz.pojo.LdbzWishlist;
import cn.ldbz.pojo.LdbzResult;

public interface WishlistService {
	
	LdbzResult getItemPage(LdbzWishlist entity , int pn , int limit) ;
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult insertByEntity(LdbzWishlist entity);
	
}