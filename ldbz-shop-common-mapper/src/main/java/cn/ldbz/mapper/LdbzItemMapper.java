package cn.ldbz.mapper;

import java.util.List;

import cn.ldbz.pojo.LdbzItem;

public interface LdbzItemMapper extends LdbzBaseMapper<LdbzItem>{

	List<LdbzItem> selectItemListBySheetKey(String key);
	
	LdbzItem selectByCode(Long code);
	
}