package cn.ldbz.admin.service;

import cn.ldbz.pojo.LdbzSearchLeftAd;
import cn.ldbz.pojo.LdbzResult;

public interface AdminSearchLeftAdService {
	
	LdbzResult countSearchLeftAd(LdbzSearchLeftAd entity);

	LdbzResult getSearchLeftAdPage(LdbzSearchLeftAd entity , int pn , int limit) ;
	
	LdbzResult selectByKey(Long id);
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult insertByEntity(LdbzSearchLeftAd entity);

	LdbzResult updateByKey(LdbzSearchLeftAd entity);
}
