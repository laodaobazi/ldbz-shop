package cn.ldbz.item.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.constant.Const;
import cn.ldbz.item.service.CategoryService;
import cn.ldbz.mapper.LdbzCategoryMapper;
import cn.ldbz.pojo.LdbzCategory;
import cn.ldbz.pojo.LdbzResult;

@Component
@Service(version = Const.LDBZ_SHOP_ITEM_VERSION)
public class CategoryServiceImpl implements CategoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	LdbzCategoryMapper mapper ;
	
	@Override
	public LdbzResult getCategoryPage(LdbzCategory entity, int pn, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = mapper.countByEntity(entity);
		map.put("total", total) ;
		logger.debug("getCategoryPage count : {} " , total);
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzCategory> ret = mapper.selectByEntity(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(0, "", map);
	}
	
	@Override
	public LdbzResult getCategoryList(LdbzCategory entity) {
		List<LdbzCategory> ret = mapper.selectByEntity(entity, 0, Integer.MAX_VALUE);
		return LdbzResult.build(0, "", ret);
	}

	@Override
	public LdbzResult selectByKey(Long id) {
		logger.debug("selectByKey id : {} " , id);
		return LdbzResult.ok(mapper.selectByKey(id));
	}

	@Override
	public LdbzResult deleteByKey(String id) {
		logger.debug("deleteByKey id : {} " , id);
		if(StringUtils.contains(id, ",")) {
			List<Long> ids = new ArrayList<Long>();
			for(String _id : StringUtils.split(id , ",")) {
				ids.add(Long.valueOf(_id));
			}
			return LdbzResult.ok(mapper.deleteByKeys(ids));
		}else {
			return LdbzResult.ok(mapper.deleteByKey(Long.valueOf(id)));
		}
	}

	@Override
	public LdbzResult insertByEntity(LdbzCategory entity) {
		logger.debug("insertByEntity entity : {} " , entity);
		if(mapper.insertByEntity(entity)>=1) {
			return LdbzResult.ok();
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}

	@Override
	public LdbzResult updateByKey(LdbzCategory entity) {
		logger.debug("updateByKey entity : {} " , entity);
		return LdbzResult.ok(mapper.updateByKey(entity));
	}

}
