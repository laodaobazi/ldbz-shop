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

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.constant.Const;
import cn.ldbz.item.service.ItemService;
import cn.ldbz.mapper.LdbzItemMapper;
import cn.ldbz.pojo.LdbzItem;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.utils.FastJsonConvert;

@Component
@Service(version = Const.LDBZ_SHOP_ITEM_VERSION)
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private LdbzItemMapper mapper ;

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    @Value("${redisKey.prefix.item_info_profix}")
    private String ITEM_INFO_PROFIX;

    @Value("${redisKey.suffix.item_info_base_suffix}")
    private String  ITEM_INFO_BASE_SUFFIX;

    @Value("${redisKey.suffix.item_info_desc_suffix}")
    private String ITEM_INFO_DESC_SUFFIX;

    @Value("${redisKey.expire_time}")
    private Integer REDIS_EXPIRE_TIME;
    
	@Override
	public LdbzResult getItemPage(LdbzItem entity, int pn, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = mapper.countByEntity(entity);
		map.put("total", total) ;
		logger.debug("getCategoryPage count : {} " , total);
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzItem> ret = mapper.selectByEntity(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(0, "", map);
	}

	@Override
	public LdbzResult getItemList(LdbzItem entity) {
		List<LdbzItem> ret = mapper.selectByEntity(entity, 0, Integer.MAX_VALUE);
		return LdbzResult.build(0, "", ret);
	}
    
	@Override
	public LdbzResult selectByKey(Long itemId) {
			String key = ITEM_INFO_PROFIX + itemId + ITEM_INFO_BASE_SUFFIX;
		    try {
		        String jsonItem = jedisClient.get(key);
		        if (StringUtils.isNotBlank(jsonItem)) {
		            logger.info("Redis 查询 商品信息 商品ID:" + itemId);
		            LdbzItem item = FastJsonConvert.convertJSONToObject(jsonItem, LdbzItem.class);
		            return LdbzResult.ok(item);
		        } else {
		            logger.error("Redis 查询不到 key:" + key);
		        }
		    } catch (Exception e) {
		        logger.error("商品信息 获取缓存报错",e);
		    }
		    logger.info("根据商品ID"+itemId+"查询商品！");
		    LdbzItem item = mapper.selectByKey(itemId);
		    try {
		        jedisClient.set(key, FastJsonConvert.convertObjectToJSON(item));
		        jedisClient.expire(key, REDIS_EXPIRE_TIME);
		        logger.info("Redis 缓存商品信息 key:" + key);
		    } catch (Exception e) {
		        logger.error("缓存错误商品ID:" + itemId, e);
		    }
		    return LdbzResult.ok(item);
	}

	@Override
	public LdbzResult deleteByKey(String itemId) {
		String key = ITEM_INFO_PROFIX + itemId + ITEM_INFO_BASE_SUFFIX;
		logger.debug("deleteByKey id : {} " , itemId);
		if(StringUtils.contains(itemId, ",")) {
			List<Long> ids = new ArrayList<Long>();
			for(String _id : StringUtils.split(itemId , ",")) {
				try {
			        jedisClient.del(key);
			        logger.info("Redis 删除商品信息 key:" + key);
			    } catch (Exception e) {
			        logger.error("缓存错误商品ID:" + itemId, e);
			    }
				ids.add(Long.valueOf(_id));
			}
			return LdbzResult.ok(mapper.deleteByKeys(ids));
		}else {
			try {
		        jedisClient.del(key);
		        logger.info("Redis 删除商品信息 key:" + key);
		    } catch (Exception e) {
		        logger.error("缓存错误商品ID:" + itemId, e);
		    }
			return LdbzResult.ok(mapper.deleteByKey(Long.valueOf(itemId)));
		}
	}

	@Override
	public LdbzResult insertByEntity(LdbzItem entity) {
		logger.debug("insertByEntity entity : {} " , entity);
		if(mapper.insertByEntity(entity)>=1) {
			return LdbzResult.ok();
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}

	@Override
	public LdbzResult updateByKey(LdbzItem entity) {
		logger.debug("updateByKey entity : {} " , entity);
		return LdbzResult.ok(mapper.updateByKey(entity));
	}

}
