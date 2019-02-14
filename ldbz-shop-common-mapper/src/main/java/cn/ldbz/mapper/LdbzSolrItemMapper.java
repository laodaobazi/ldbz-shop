package cn.ldbz.mapper;

import org.apache.ibatis.annotations.Param;

import cn.ldbz.pojo.LdbzSolrItem;

public interface LdbzSolrItemMapper extends LdbzBaseMapper<LdbzSolrItem>{

	LdbzSolrItem selectByCode(@Param("code")Long code);
	
}