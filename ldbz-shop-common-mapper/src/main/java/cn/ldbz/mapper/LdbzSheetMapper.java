package cn.ldbz.mapper;

import java.util.List;
import java.util.Map;

import cn.ldbz.pojo.LdbzSheet;
import cn.ldbz.pojo.LdbzSheetAssign;

public interface LdbzSheetMapper extends LdbzBaseMapper<LdbzSheet>{

	List<Map<String,Object>> getSheetAssignList(long sheetId);
	
	int deleteAssign(long assignId);
	
	int deleteAssigns(List<Long> assignIds);

	int addAssign(LdbzSheetAssign entity);
	
}