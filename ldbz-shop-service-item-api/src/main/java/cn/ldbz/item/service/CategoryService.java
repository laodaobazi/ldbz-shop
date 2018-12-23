package cn.ldbz.item.service;

import cn.ldbz.pojo.LdbzCategory;
import cn.ldbz.pojo.LdbzResult;

public interface CategoryService {
	
	LdbzResult getCategoryPage(LdbzCategory entity , int pn , int limit) ;
	
	LdbzResult getCategoryList(LdbzCategory entity);

	LdbzResult selectByKey(Long id);
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult insertByEntity(LdbzCategory entity);

	LdbzResult updateByKey(LdbzCategory entity);

	LdbzResult getCategoryTree(long fid);
	
	LdbzResult getCategoryTreeRedis(long fid);

	LdbzResult updateSort(String ids);
	
}
