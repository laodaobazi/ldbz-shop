package cn.ldbz.wishlist.service.impl;

import java.util.ArrayList;
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

import cn.ldbz.constant.Const;
import cn.ldbz.mapper.LdbzWishlistMapper;
import cn.ldbz.pojo.LdbzWishlist;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.sso.service.UserService;
import cn.ldbz.wishlist.service.WishlistService;

/**
 * 收藏Service
 */
@Component
@Service(version = Const.LDBZ_SHOP_WISHLIST_VERSION)
@Transactional
public class WishlistServiceImpl implements WishlistService {

    private static final Logger logger = LoggerFactory.getLogger(WishlistServiceImpl.class);

    @Reference(version = Const.LDBZ_SHOP_SSO_VERSION)
    private UserService userService;

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    @Autowired
    private LdbzWishlistMapper mapper;

	@Override
	public LdbzResult getItemPage(LdbzWishlist entity, int pn, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = mapper.countByEntity(entity);
		map.put("total", total) ;
		logger.debug("getItemPage count : {} " , total);
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzWishlist> ret = mapper.selectByEntity(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(200, "", map);
	}

	@Override
	public LdbzResult deleteByKey(String id) {
		logger.debug("deleteByKey id : {} " , id);
		if(StringUtils.contains(id, ",")) {
			List<Long> ids = new ArrayList<Long>();
			for(String _id : StringUtils.split(id , ",")) {
				ids.add(Long.valueOf(_id));
			}
			return LdbzResult.ok(mapper.deleteByKeys(ids));
		}else {
			return LdbzResult.ok(mapper.deleteByKey(Long.valueOf(id)));
		}
	}

	@Override
	public LdbzResult insertByEntity(LdbzWishlist entity) {
		logger.debug("insertByEntity ==>>>  selectByCode entity : {} " , entity);
		if(mapper.selectByCode(entity)!=null) {
			return LdbzResult.ok("exist");
		}
		logger.debug("insertByEntity entity : {} " , entity);
		if(mapper.insertByEntity(entity)>=1) {
			return LdbzResult.ok("success");
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}

}
