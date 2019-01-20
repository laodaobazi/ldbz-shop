package cn.ldbz.portal.service;


import java.util.List;
import java.util.Map;

import cn.ldbz.pojo.LdbzIndexSlideAd;
import cn.ldbz.pojo.LdbzResult;

public interface IndexService {
	/**
     * 获取分类
     */
	LdbzResult getCategory();
	
	/**
	 * 获取首页轮播广告
	 */
	List<LdbzIndexSlideAd> getIndexSlideAd();
	
	/**
	 * 获取所有有效板块
	 */
	LdbzResult getSheetList();
    
	/**
	 * 根据板块获取分配的商品
	 */
	@SuppressWarnings("rawtypes")
	List<Map> getSheetAssignList(long sheetId);
}
