package cn.ldbz.admin.service;

import cn.ldbz.pojo.LdbzCategory;
import cn.ldbz.pojo.LdbzResult;

public interface AdminCategoryService {
	
	LdbzResult countCategory(LdbzCategory entity);
	
	LdbzResult selectByKey(long id) ;

	LdbzResult editCategory(long id) ;
	
	LdbzResult getCategoryList(LdbzCategory entity);
	
	LdbzResult getCategoryPage(LdbzCategory entity , int page , int limit) ;
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult insertByEntity(LdbzCategory entity) ;
	
	LdbzResult updateByKey(LdbzCategory entity) ;
	
	LdbzResult getCategoryTree(long fid) ;
	
	LdbzResult updateSort(String ids) ;
	
}
