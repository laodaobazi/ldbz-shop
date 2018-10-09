package cn.ldbz.advertisement.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.advertisement.service.IndexSlideAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.mapper.LdbzIndexSlideAdMapper;
import cn.ldbz.pojo.LdbzIndexSlideAd;


@Component
@Service(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
public class IndexSlideAdServiceImpl implements IndexSlideAdService{

	private static final Logger logger = LoggerFactory.getLogger(IndexSlideAdServiceImpl.class);

	@Autowired
	LdbzIndexSlideAdMapper mapper ;
	
	@Override
	public List<LdbzIndexSlideAd> getIndexSlideAdPage(LdbzIndexSlideAd entity, int start, int limit) {
		logger.debug("getIndexSlideAdPage {} ，{} ，{}" , entity , start , limit);
		return mapper.selectByEntity(entity, start, limit);
	}

	@Override
	public long countByEntity(LdbzIndexSlideAd entity) {
		logger.debug("countByEntity {}" , entity);
		return mapper.countByEntity(entity);
	}
	
	public LdbzIndexSlideAd selectByKey(Long id) {
		logger.debug("selectByKey {}" , id);
		return mapper.selectByKey(id);
	}
	
	public int deleteByKey(Long id) {
		logger.debug("deleteByKey {}" , id);
		return mapper.deleteByKey(id);
	}
	
	public int deleteByKeys(List<Long> ids){
		logger.debug("deleteByKeys {}" , ids);
		return mapper.deleteByKeys(ids);
	}
	
	public int insertByEntity(LdbzIndexSlideAd entity) {
		logger.debug("insertByEntity {}" , entity);
		return mapper.insertByEntity(entity);
	}
	
	public int updateByKey(LdbzIndexSlideAd entity) {
		logger.debug("updateByEntity {}" , entity);
		return mapper.updateByKey(entity);
	}

}
