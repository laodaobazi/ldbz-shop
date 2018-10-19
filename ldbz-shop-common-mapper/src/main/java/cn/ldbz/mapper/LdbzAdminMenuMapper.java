package cn.ldbz.mapper;

import java.util.List;

import cn.ldbz.pojo.LdbzAdminMenu;

public interface LdbzAdminMenuMapper extends LdbzBaseMapper<LdbzAdminMenu> {

	List<LdbzAdminMenu> getMenuTree();
	
}