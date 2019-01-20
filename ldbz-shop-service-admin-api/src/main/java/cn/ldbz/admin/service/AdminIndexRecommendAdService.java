package cn.ldbz.admin.service;

import cn.ldbz.pojo.LdbzIndexRecommendAd;
import cn.ldbz.pojo.LdbzResult;

public interface AdminIndexRecommendAdService {
	
	LdbzResult selectByKey(Long id) ;
	
	LdbzResult countIndexRecommendAd(LdbzIndexRecommendAd entity);

	LdbzResult getIndexRecommendAdPage(LdbzIndexRecommendAd entity , int pn , int limit) ;
	
	LdbzResult updateByKey(LdbzIndexRecommendAd entity);
	
}
