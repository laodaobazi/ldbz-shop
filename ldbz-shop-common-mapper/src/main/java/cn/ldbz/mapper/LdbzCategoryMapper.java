package cn.ldbz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ldbz.pojo.LdbzCategory;

public interface LdbzCategoryMapper extends LdbzBaseMapper<LdbzCategory>{

	List<LdbzCategory> getCategoryTree(@Param("fid")long fid);

	int updateSort(@Param("id")long id , @Param("sortOrder")int sortOrder);

}