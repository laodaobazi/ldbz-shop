package cn.ldbz.item.service;

import cn.ldbz.pojo.LdbzItem;
import cn.ldbz.pojo.LdbzResult;

public interface ItemService {
	
	LdbzResult countItem(LdbzItem entity);
	
	LdbzResult getItemPage(LdbzItem entity , int pn , int limit) ;
	
	LdbzResult getItemList(LdbzItem entity);

	/**
	 * 根据板块Key获取商品列表
	 * @param key
	 * @return
	 */
	LdbzResult selectItemListBySheetKey(String key);

	LdbzResult selectByKey(Long id);
	
	LdbzResult selectByCode(Long code);
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult insertByEntity(LdbzItem entity);

	LdbzResult updateByKey(LdbzItem entity);

}