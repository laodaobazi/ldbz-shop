package cn.ldbz.admin.service;

import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSheet;
import cn.ldbz.pojo.LdbzSheetAssign;

public interface AdminSheetService {
	
	LdbzResult countSheet();

	LdbzResult selectByKey(long id);
	
	LdbzResult getSheetList(LdbzSheet entity) ;
	
	LdbzResult getSheetPage(LdbzSheet entity , int page , int limit) ;
	
	LdbzResult updateByKey(LdbzSheet entity) ;
	
	
	LdbzResult getSheetAssignList(long sheetId) ;
	
	LdbzResult deleteAssign(String id) ;
	
	LdbzResult addAssign(LdbzSheetAssign entity) ;

}
