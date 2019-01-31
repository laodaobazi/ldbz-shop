package cn.ldbz.advertisement.service;

import java.util.List;

import cn.ldbz.pojo.LdbzSearchSlideAd;

public interface SearchSlideAdService {

	List<LdbzSearchSlideAd> getSearchSlideAdPage(LdbzSearchSlideAd entity , int start , int limit);
	
	long countByEntity(LdbzSearchSlideAd entity);
	
	LdbzSearchSlideAd selectByKey(Long id);
	
	int deleteByKey(Long id);
	
	int deleteByKeys(List<Long> ids);
	
	int insertByEntity(LdbzSearchSlideAd entity);
	
	int updateByKey(LdbzSearchSlideAd entity);

	List<LdbzSearchSlideAd> getSearchSlideAdByRedis();
	
}
