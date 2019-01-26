package cn.ldbz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ldbz.pojo.LdbzCategory;

public interface LdbzCategoryMapper extends LdbzBaseMapper<LdbzCategory>{

	List<LdbzCategory> getCategoryTree(@Param("fid")long fid);
	
	List<LdbzCategory> getCategoryByLevelAndFid(@Param("level")Object level , @Param("fid")Object fid);

	int updateSort(@Param("id")long id , @Param("sortOrder")int sortOrder);

}