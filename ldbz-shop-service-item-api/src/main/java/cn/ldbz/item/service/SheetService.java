package cn.ldbz.item.service;

import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSheet;

public interface SheetService {
	
	LdbzResult getSheetPage(LdbzSheet entity , int pn , int limit) ;
	
	LdbzResult getSheetList(LdbzSheet entity);

	LdbzResult selectByKey(Long id);
	
	LdbzResult updateByKey(LdbzSheet entity);

}