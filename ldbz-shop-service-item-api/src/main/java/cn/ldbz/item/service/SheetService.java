package cn.ldbz.item.service;

import java.util.List;
import java.util.Map;

import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSheet;
import cn.ldbz.pojo.LdbzSheetAssign;

public interface SheetService {
	
	LdbzResult countSheet(LdbzSheet entity);
	
	LdbzResult getSheetPage(LdbzSheet entity , int pn , int limit) ;
	
	LdbzResult getSheetList(LdbzSheet entity);

	LdbzResult selectByKey(Long id);
	
	LdbzResult updateByKey(LdbzSheet entity);

	LdbzResult getSheetAssignList(long sheetId);

	@SuppressWarnings("rawtypes")
	List<Map> getSheetAssignListByRedis(long sheetId);
	
	LdbzResult deleteAssign(String ids);

	LdbzResult addAssign(LdbzSheetAssign entity);
	
}