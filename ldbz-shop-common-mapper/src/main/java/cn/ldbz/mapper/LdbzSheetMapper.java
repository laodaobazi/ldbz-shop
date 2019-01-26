package cn.ldbz.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.ldbz.pojo.LdbzSheet;
import cn.ldbz.pojo.LdbzSheetAssign;

public interface LdbzSheetMapper extends LdbzBaseMapper<LdbzSheet>{

	List<Map<String,Object>> getSheetAssignList(long sheetId);

	List<Map<String,Object>> getSheetAssignListByKey(Object sheetKey);
	
	List<LdbzSheet> getEnableSheetsByKeys(@Param("sheetKeys")Object sheetKeys);
	
	int deleteAssign(long assignId);
	
	int deleteAssigns(List<Long> assignIds);

	int addAssign(LdbzSheetAssign entity);
	
}