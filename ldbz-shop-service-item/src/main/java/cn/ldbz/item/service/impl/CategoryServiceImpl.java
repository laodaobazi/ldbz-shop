package cn.ldbz.item.service.impl;

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
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import cn.ldbz.constant.Const;
import cn.ldbz.item.service.CategoryService;
import cn.ldbz.mapper.LdbzCategoryMapper;
import cn.ldbz.pojo.LdbzCategory;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.utils.FastJsonConvert;

@Component
@Service(version = Const.LDBZ_SHOP_ITEM_VERSION)
public class CategoryServiceImpl implements CategoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	LdbzCategoryMapper mapper ;

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    @Value("${redisKey.category.info.key}")
    private String CATEGORY_INFO_KEY;

    @Value("${redisKey.expire_time}")
    private Integer REDIS_EXPIRE_TIME;
    
    /**
     * 监听配置项是否有修改
     */
    @ApolloConfigChangeListener
	public void onChange(ConfigChangeEvent changeEvent) {
		for (String key : changeEvent.changedKeys()) {
			ConfigChange change = changeEvent.getChange(key);
			logger.debug(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s",
					change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
			switch(key) {
			case "redisKey.category.info.key" : 
				CATEGORY_INFO_KEY = change.getNewValue();
			case "redisKey.expire_time" : 
				REDIS_EXPIRE_TIME = Integer.valueOf(change.getNewValue());
			}
		}
	}
	
	@Override
	public LdbzResult countCategory(LdbzCategory entity) {
    	long total = mapper.countByEntity(entity);
		return LdbzResult.ok(total);
	}
	
	@Override
	public LdbzResult getCategoryPage(LdbzCategory entity, int pn, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = mapper.countByEntity(entity);
		map.put("total", total) ;
		logger.debug("getCategoryPage count : {} " , total);
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzCategory> ret = mapper.selectByEntity(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(0, "", map);
	}
	
	@Override
	public LdbzResult getCategoryList(LdbzCategory entity) {
		logger.debug("getCategoryList : {} " , entity);
		List<LdbzCategory> ret = mapper.selectByEntity(entity, 0, Integer.MAX_VALUE);
		return LdbzResult.build(0, "", ret);
	}

	@Override
	public LdbzResult selectByKey(Long id) {
		logger.debug("selectByKey id : {} " , id);
		return LdbzResult.ok(mapper.selectByKey(id));
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
	public LdbzResult insertByEntity(LdbzCategory entity) {
		logger.debug("insertByEntity entity : {} " , entity);
		if(mapper.insertByEntity(entity)>=1) {
			return LdbzResult.ok();
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}

	@Override
	public LdbzResult updateByKey(LdbzCategory entity) {
		logger.debug("updateByKey entity : {} " , entity);
		return LdbzResult.ok(mapper.updateByKey(entity));
	}

	@Override
	public LdbzResult getCategoryTree(long fid) {
		logger.debug("getCategoryTree fid : {} " , fid);
		return LdbzResult.ok(mapper.getCategoryTree(fid)) ;
	}

	@Override
	public LdbzResult getCategoryTreeRedis(long fid) {
		try {
	        String json = jedisClient.get(CATEGORY_INFO_KEY);
	        if (StringUtils.isNotBlank(json)) {
	        	logger.info("Redis 获取商品分类:");
	            List<LdbzCategory> list = FastJsonConvert.convertJSONToArray(json, LdbzCategory.class);
	            return LdbzResult.ok(list);
	        } else {
	            logger.error("Redis 查询不到商品分类");
	        }
	    } catch (Exception e) {
	        logger.error("商品分类 获取缓存报错",e);
	    }
		
		LdbzResult ret = LdbzResult.ok(mapper.getCategoryTree(fid)) ;
		try {
            logger.info("Redis 更新商品分类:");
	        jedisClient.set(CATEGORY_INFO_KEY, FastJsonConvert.convertObjectToJSON(ret.getData()));
	        jedisClient.expire(CATEGORY_INFO_KEY, REDIS_EXPIRE_TIME);
	    } catch (Exception e) {
	        logger.error("Redis 更新商品分类缓存报错",e);
	    }
		return ret ;
	}

	@Override
	@Transactional
	public LdbzResult updateSort(String ids) {
		logger.debug("updateSort ids : {} " , ids);
		String[] arr = ids.split(",");
		for(int i=0 ; i<arr.length ; i++) {
			mapper.updateSort(Long.parseLong(arr[i]) , i+1);
		}
		return LdbzResult.ok() ;
	}

}
