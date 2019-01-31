package cn.ldbz.admin.service;

import cn.ldbz.pojo.LdbzSearchSlideAd;
import cn.ldbz.pojo.LdbzResult;

public interface AdminSearchSlideAdService {
	
	LdbzResult countSearchSlideAd(LdbzSearchSlideAd entity);

	LdbzResult getSearchSlideAdPage(LdbzSearchSlideAd entity , int pn , int limit) ;
	
	LdbzResult selectByKey(Long id);
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult insertByEntity(LdbzSearchSlideAd entity);

	LdbzResult updateByKey(LdbzSearchSlideAd entity);
}
