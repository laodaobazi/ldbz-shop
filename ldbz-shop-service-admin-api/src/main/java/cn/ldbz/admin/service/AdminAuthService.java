package cn.ldbz.admin.service;

import cn.ldbz.pojo.LdbzAdminAuth;
import cn.ldbz.pojo.LdbzResult;

public interface AdminAuthService {

	LdbzResult selectUserByRole(Long roleId , int pn , int limit , String realName) ;
	
	LdbzResult selectMenuByRole(Long roleId , int pn , int limit , String menuName) ;
	
	LdbzResult deleteByUser(Long userId , Long roleId);
	
	LdbzResult deleteByMenu(Long menuId , Long roleId);
	
	LdbzResult insertByUser(LdbzAdminAuth entity);

	LdbzResult insertByMenu(LdbzAdminAuth entity);
	
	LdbzResult getUserByNameOrAccount(String queryKey) ;
	
	LdbzResult getResByNameOrUrl(String queryKey) ;
	
}
