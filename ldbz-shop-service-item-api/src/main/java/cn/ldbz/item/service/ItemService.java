package cn.ldbz.item.service;

import cn.ldbz.pojo.LdbzItem;
import cn.ldbz.pojo.LdbzResult;

public interface ItemService {
	
	LdbzResult countItem(LdbzItem entity);
	
	LdbzResult getItemPage(LdbzItem entity , int pn , int limit) ;
	
	LdbzResult getItemList(LdbzItem entity);

	LdbzResult selectByKey(Long id);
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult insertByEntity(LdbzItem entity);

	LdbzResult updateByKey(LdbzItem entity);

}