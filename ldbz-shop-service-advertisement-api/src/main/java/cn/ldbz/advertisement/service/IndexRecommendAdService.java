package cn.ldbz.advertisement.service;

import java.util.List;

import cn.ldbz.pojo.LdbzIndexRecommendAd;

public interface IndexRecommendAdService {

	List<LdbzIndexRecommendAd> getIndexRecommendAdPage(LdbzIndexRecommendAd entity , int start , int limit);
	
	long countByEntity(LdbzIndexRecommendAd entity);
	
	LdbzIndexRecommendAd selectByKey(Long id);
	
	int updateByKey(LdbzIndexRecommendAd entity);

	List<LdbzIndexRecommendAd> getIndexRecommendAdByRedis();
	
}
