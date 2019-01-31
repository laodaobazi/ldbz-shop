package cn.ldbz.advertisement.service.impl;

import java.util.List;

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

import cn.ldbz.advertisement.service.SearchSlideAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.mapper.LdbzSearchSlideAdMapper;
import cn.ldbz.pojo.LdbzSearchSlideAd;
import cn.ldbz.redis.service.JedisClient;
import cn.ldbz.utils.FastJsonConvert;


@Component
@Service(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
public class SearchSlideAdServiceImpl implements SearchSlideAdService{

	private static final Logger logger = LoggerFactory.getLogger(SearchSlideAdServiceImpl.class);

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;
    
	@Autowired
	LdbzSearchSlideAdMapper mapper ;
	@Value("${redisKey.searchSlide.info.key}")
    private String SEARCH_SLIDE_INFO_KEY;

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
			case "redisKey.searchSlide.info.key" : 
				SEARCH_SLIDE_INFO_KEY = change.getNewValue();
			case "redisKey.expire_time" : 
				REDIS_EXPIRE_TIME = Integer.valueOf(change.getNewValue());
			}
		}
	}
	
	@Override
	public List<LdbzSearchSlideAd> getSearchSlideAdPage(LdbzSearchSlideAd entity, int start, int limit) {
		logger.debug("getIndexSlideAdPage {} ，{} ，{}" , entity , start , limit);
		return mapper.selectByEntity(entity, start, limit);
	}

	@Override
	public long countByEntity(LdbzSearchSlideAd entity) {
		logger.debug("countByEntity {}" , entity);
		return mapper.countByEntity(entity);
	}
	
	public LdbzSearchSlideAd selectByKey(Long id) {
		logger.debug("selectByKey {}" , id);
		return mapper.selectByKey(id);
	}
	
	public int deleteByKey(Long id) {
		logger.debug("deleteByKey {}" , id);
		return mapper.deleteByKey(id);
	}
	
	public int deleteByKeys(List<Long> ids){
		logger.debug("deleteByKeys {}" , ids);
		return mapper.deleteByKeys(ids);
	}
	
	public int insertByEntity(LdbzSearchSlideAd entity) {
		logger.debug("insertByEntity {}" , entity);
		return mapper.insertByEntity(entity);
	}
	
	public int updateByKey(LdbzSearchSlideAd entity) {
		logger.debug("updateByEntity {}" , entity);
		return mapper.updateByKey(entity);
	}
	
	@Override
	public List<LdbzSearchSlideAd> getSearchSlideAdByRedis() {
		logger.debug("getIndexSlideAdByRedis ");
		try {
	        String json = jedisClient.get(SEARCH_SLIDE_INFO_KEY);
	        if (StringUtils.isNotBlank(json)) {
	        	logger.info("Redis 获取检索页广告");
	        	return FastJsonConvert.convertJSONToArray(json, LdbzSearchSlideAd.class);
	        } else {
	            logger.error("Redis 查询不到检索页广告");
	        }
	    } catch (Exception e) {
	        logger.error("检索页广告获取缓存报错",e);
	    }
		
		List<LdbzSearchSlideAd> ret = mapper.selectByEntity( new LdbzSearchSlideAd(), 0, Integer.MAX_VALUE);
		try {
            logger.info("Redis 更新检索页广告");
	        jedisClient.set(SEARCH_SLIDE_INFO_KEY, FastJsonConvert.convertObjectToJSON(ret));
	        jedisClient.expire(SEARCH_SLIDE_INFO_KEY, REDIS_EXPIRE_TIME);
	    } catch (Exception e) {
	        logger.error("Redis 更新检索页广告缓存报错",e);
	    }
		return ret ;
	}

}
