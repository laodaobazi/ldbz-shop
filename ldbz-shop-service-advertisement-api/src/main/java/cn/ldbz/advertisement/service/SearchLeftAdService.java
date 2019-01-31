package cn.ldbz.advertisement.service;

import java.util.List;

import cn.ldbz.pojo.LdbzSearchLeftAd;

public interface SearchLeftAdService {

	List<LdbzSearchLeftAd> getSearchLeftAdPage(LdbzSearchLeftAd entity , int start , int limit);
	
	long countByEntity(LdbzSearchLeftAd entity);
	
	LdbzSearchLeftAd selectByKey(Long id);
	
	int deleteByKey(Long id);
	
	int deleteByKeys(List<Long> ids);
	
	int insertByEntity(LdbzSearchLeftAd entity);
	
	int updateByKey(LdbzSearchLeftAd entity);

	List<LdbzSearchLeftAd> getSearchLeftAdByRedis();
	
}
