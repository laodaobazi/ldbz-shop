package cn.ldbz.admin.service;

import cn.ldbz.pojo.LdbzItem;
import cn.ldbz.pojo.LdbzResult;

public interface AdminItemService {
	
	LdbzResult countItem() ;

	LdbzResult selectByKey(long id);
	
	LdbzResult getItemList(LdbzItem entity) ;
	
	LdbzResult getItemPage(LdbzItem entity , int page , int limit) ;
	
	LdbzResult deleteByKey(String id) ;
	
	LdbzResult insertByEntity(LdbzItem entity) ;
	
	LdbzResult updateByKey(LdbzItem entity) ;

	LdbzResult syncItemToSolrByCode(long code) ;
	
}
