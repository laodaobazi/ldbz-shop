package cn.ldbz.search.service;

import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSolrItem;

public interface SearchService {

	LdbzResult getItemPage(LdbzSolrItem entity , int pn , int limit) ;
	
	LdbzSolrItem selectByCode(long code);
	
	LdbzResult syncItemToSolrByCode(long code) ;
	
}
