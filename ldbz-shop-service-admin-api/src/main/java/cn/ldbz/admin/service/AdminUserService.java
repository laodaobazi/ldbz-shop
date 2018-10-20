package cn.ldbz.admin.service;

import cn.ldbz.pojo.LdbzAdminUser;
import cn.ldbz.pojo.LdbzResult;

public interface AdminUserService {

	LdbzResult getAdminUserPage(LdbzAdminUser entity , int pn , int limit) ;
	
	LdbzResult selectByKey(Long id);
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult insertByEntity(LdbzAdminUser entity);

	LdbzResult updateByKey(LdbzAdminUser entity);
}
