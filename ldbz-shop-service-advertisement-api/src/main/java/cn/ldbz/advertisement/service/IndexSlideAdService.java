package cn.ldbz.advertisement.service;

import java.util.List;

import cn.ldbz.pojo.LdbzIndexSlideAd;

public interface IndexSlideAdService {

	List<LdbzIndexSlideAd> getIndexSlideAdPage(LdbzIndexSlideAd entity , int start , int limit);
	
	long countByEntity(LdbzIndexSlideAd entity);
	
	LdbzIndexSlideAd selectByKey(Long id);
	
	int deleteByKey(Long id);
	
	int deleteByKeys(List<Long> ids);
	
	int insertByEntity(LdbzIndexSlideAd entity);
	
	int updateByKey(LdbzIndexSlideAd entity);

	List<LdbzIndexSlideAd> getIndexSlideAdByRedis();
	
}
