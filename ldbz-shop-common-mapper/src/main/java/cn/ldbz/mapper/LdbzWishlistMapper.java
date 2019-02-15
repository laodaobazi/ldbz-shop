package cn.ldbz.mapper;

import cn.ldbz.pojo.LdbzWishlist;

public interface LdbzWishlistMapper extends LdbzBaseMapper<LdbzWishlist>{

	LdbzWishlist selectByCode(LdbzWishlist entity);
	
}