package cn.ldbz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ldbz.pojo.LdbzAdminAuth;

public interface LdbzAdminAuthMapper extends LdbzBaseMapper<LdbzAdminAuth> {

	Long countUserByRole(@Param("roleId")Long roleId , @Param("realName")String realName);

	List<LdbzAdminAuth> selectUserByRole(@Param("roleId")Long roleId , 
			@Param("start")int start , @Param("limit")int limit , @Param("realName")String realName);
	
	Long countMenuByRole(@Param("roleId")Long roleId , @Param("menuName")String menuName);
	
	List<LdbzAdminAuth> selectMenuByRole(@Param("roleId")Long roleId , 
			@Param("start")int start , @Param("limit")int limit , @Param("menuName")String menuName);
	
	int deleteByUser(@Param("userId")Long userId , @Param("roleId")Long roleId);
	
	int deleteByMenu(@Param("menuId")Long menuId , @Param("roleId")Long roleId);
	
	int insertByUser(LdbzAdminAuth entity);
	
	int insertByMenu(LdbzAdminAuth entity);
	
	List<LdbzAdminAuth> getUserByNameOrAccount(String queryKey) ;
	
	List<LdbzAdminAuth> getResByNameOrUrl(String queryKey) ;
}