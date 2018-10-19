package cn.ldbz.admin.service;

import cn.ldbz.pojo.LdbzAdminMenu;
import cn.ldbz.pojo.LdbzResult;

public interface AdminMenuService {

	LdbzResult getAdminMenuPage(LdbzAdminMenu entity , int pn , int limit) ;
	
	LdbzResult getMenuTree();

	LdbzResult selectByKey(Long id);
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult insertByEntity(LdbzAdminMenu entity);

	LdbzResult updateByKey(LdbzAdminMenu entity);
}
