package cn.ldbz.item.service;

import java.util.List;

import cn.ldbz.pojo.LdbzCategory;
import cn.ldbz.pojo.LdbzResult;

public interface CategoryService {
	
	LdbzResult countCategory(LdbzCategory entity);
	
	LdbzResult getCategoryPage(LdbzCategory entity , int pn , int limit) ;
	
	LdbzResult getCategoryList(LdbzCategory entity);

	LdbzResult selectByKey(Long id);
	
	LdbzResult deleteByKey(String id);
	
	LdbzResult insertByEntity(LdbzCategory entity);

	LdbzResult updateByKey(LdbzCategory entity);

	LdbzResult getCategoryTree(long fid);
	
	List<LdbzCategory> getCategoryTreeRedis(Object level , Object fid);

	LdbzResult updateSort(String ids);
	
}
