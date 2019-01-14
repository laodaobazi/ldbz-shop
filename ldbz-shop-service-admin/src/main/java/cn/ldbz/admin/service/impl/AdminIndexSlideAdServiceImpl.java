package cn.ldbz.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.admin.service.AdminIndexSlideAdService;
import cn.ldbz.advertisement.service.IndexSlideAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzIndexSlideAd;
import cn.ldbz.pojo.LdbzResult;


@Component
@Service(version = Const.LDBZ_SHOP_ADMIN_VERSION)
public class AdminIndexSlideAdServiceImpl implements AdminIndexSlideAdService{

	@Reference(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
	private IndexSlideAdService indexSlideAdService ;
	
	@Override
	public LdbzResult countIndexSlideAd(LdbzIndexSlideAd entity) {
		long total = indexSlideAdService.countByEntity(entity);
		return LdbzResult.ok(total);
	}
	
	@Override
	public LdbzResult getIndexSlideAdPage(LdbzIndexSlideAd entity , int pn , int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = indexSlideAdService.countByEntity(entity);
		map.put("total", total) ;
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzIndexSlideAd> ret = indexSlideAdService.getIndexSlideAdPage(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(0, "", map);
	}

	public LdbzResult selectByKey(Long id) {
		return LdbzResult.ok(indexSlideAdService.selectByKey(id));
	}
	
	public LdbzResult deleteByKey(String id) {
		if(StringUtils.contains(id, ",")) {
			List<Long> ids = new ArrayList<Long>();
			for(String _id : StringUtils.split(id , ",")) {
				ids.add(Long.valueOf(_id));
			}
			return LdbzResult.ok(indexSlideAdService.deleteByKeys(ids));
		}else {
			return LdbzResult.ok(indexSlideAdService.deleteByKey(Long.valueOf(id)));
		}
	}
	
	public LdbzResult insertByEntity(LdbzIndexSlideAd entity) {
		if(indexSlideAdService.insertByEntity(entity)>=1) {
			return LdbzResult.ok();
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}
	
	public LdbzResult updateByKey(LdbzIndexSlideAd entity) {
		return LdbzResult.ok(indexSlideAdService.updateByKey(entity));
	}

}
