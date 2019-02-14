package cn.ldbz.mapper;

import org.apache.ibatis.annotations.Param;

import cn.ldbz.pojo.LdbzUser;

public interface LdbzUserMapper extends LdbzBaseMapper<LdbzUser>{

	LdbzUser selectByUser(@Param("entity")LdbzUser user);
	
}