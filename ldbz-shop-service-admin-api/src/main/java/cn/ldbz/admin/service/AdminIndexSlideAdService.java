package cn.ldbz.admin.service;

import cn.ldbz.pojo.LdbzIndexSlideAd;
import cn.ldbz.pojo.LdbzResult;

public interface AdminIndexSlideAdService {
	
	LdbzResult countIndexSlideAd(LdbzIndexSlideAd entity);

	LdbzResult getIndexSlideAdPage(LdbzIndexSlideAd entity , int pn , int limit) ;
	
	LdbzResult selectByKey(Long id);
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult insertByEntity(LdbzIndexSlideAd entity);

	LdbzResult updateByKey(LdbzIndexSlideAd entity);
}
