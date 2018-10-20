package cn.ldbz.admin.service;

import cn.ldbz.pojo.LdbzAdminRole;
import cn.ldbz.pojo.LdbzResult;

public interface AdminRoleService {

	LdbzResult getAdminRolePage(LdbzAdminRole entity , int pn , int limit) ;
	
	LdbzResult selectByKey(Long id);
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult insertByEntity(LdbzAdminRole entity);

	LdbzResult updateByKey(LdbzAdminRole entity);
	
}
