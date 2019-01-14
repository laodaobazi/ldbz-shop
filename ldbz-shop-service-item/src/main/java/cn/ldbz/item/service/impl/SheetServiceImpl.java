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
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import cn.ldbz.constant.Const;
import cn.ldbz.item.service.SheetService;
import cn.ldbz.mapper.LdbzSheetMapper;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSheet;
import cn.ldbz.pojo.LdbzSheetAssign;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.utils.FastJsonConvert;

@Component
@Service(version = Const.LDBZ_SHOP_SHEET_VERSION)
public class SheetServiceImpl implements SheetService {

    private static final Logger logger = LoggerFactory.getLogger(SheetServiceImpl.class);

    @Autowired
    private LdbzSheetMapper mapper ;

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    //首页板块缓存商品KEY
    @Value("${redisKey.sheetAssigns.key}")
    private String SHEET_ASSIGNS_KEY;
    
    @Value("${redisKey.sheetAssign.expire_time:300}")
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
				case "redisKey.sheetAssigns.key" : 
					SHEET_ASSIGNS_KEY = change.getNewValue();
				case "redisKey.sheetAssign.expire_time" : 
					REDIS_EXPIRE_TIME = Integer.valueOf(change.getNewValue());
			}
		}
	}
	
    @Override
	public LdbzResult countSheet(LdbzSheet entity) {
    	long total = mapper.countByEntity(entity);
		return LdbzResult.ok(total);
	}

	@Override
	public LdbzResult getSheetPage(LdbzSheet entity, int pn, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = mapper.countByEntity(entity);
		map.put("total", total) ;
		logger.debug("getSheetPage count : {} " , total);
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzSheet> ret = mapper.selectByEntity(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(0, "", map);
	}

	@Override
	public LdbzResult getSheetList(LdbzSheet entity) {
		logger.debug("getSheetList : {} " , entity);
		List<LdbzSheet> ret = mapper.selectByEntity(entity, 0, Integer.MAX_VALUE);
		return LdbzResult.build(0, "", ret);
	}
    
	@Override
	public LdbzResult selectByKey(Long itemId) {
		    logger.info("根据商品板块ID"+itemId+"查询商品板块！");
		    LdbzSheet item = mapper.selectByKey(itemId);
		    return LdbzResult.ok(item);
	}

	@Override
	public LdbzResult updateByKey(LdbzSheet entity) {
		logger.debug("updateByKey entity : {} " , entity);
		return LdbzResult.ok(mapper.updateByKey(entity));
	}

	@Override
	public LdbzResult getSheetAssignList(long sheetId) {
		return LdbzResult.build(0, "", mapper.getSheetAssignList(sheetId));
	}

	@Override
	public LdbzResult deleteAssign(String id) {
		logger.debug("deleteAssign id : {} " , id);
		if(StringUtils.contains(id, ",")) {
			List<Long> ids = new ArrayList<Long>();
			for(String _id : StringUtils.split(id , ",")) {
				ids.add(Long.valueOf(_id));
			}
			return LdbzResult.ok(mapper.deleteAssigns(ids));
		}else {
			return LdbzResult.ok(mapper.deleteAssign(Long.valueOf(id)));
		}
	}

	public LdbzResult addAssign(LdbzSheetAssign entity) {
		logger.debug("addAssign entity : {} " , entity);
		if(mapper.addAssign(entity)>=1) {
			return LdbzResult.ok();
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Map> getSheetAssignListByRedis(long sheetId) {
		try {
	        String jsonItem = jedisClient.get(SHEET_ASSIGNS_KEY);
	        if (StringUtils.isNotBlank(jsonItem)) {
	            logger.info("Redis 查询 板块ID:" + sheetId);
	            List<Map> items = FastJsonConvert.convertJSONToArray(jsonItem, Map.class);
	            return items;
	        } else {
	            logger.error("Redis 查询不到 key:" + sheetId);
	        }
	    } catch (Exception e) {
	        logger.error("板块信息 获取缓存报错",e);
	    }
		
		List<Map> items = (List)mapper.getSheetAssignList(sheetId) ;
		
		try {
	        jedisClient.set(SHEET_ASSIGNS_KEY, FastJsonConvert.convertObjectToJSON(items));
	        jedisClient.expire(SHEET_ASSIGNS_KEY, REDIS_EXPIRE_TIME);
	        logger.info("Redis 缓存板块信息 key:" + SHEET_ASSIGNS_KEY);
	    } catch (Exception e) {
	        logger.error("缓存错误板块ID:" + sheetId, e);
	    }
		return items;
	}

}