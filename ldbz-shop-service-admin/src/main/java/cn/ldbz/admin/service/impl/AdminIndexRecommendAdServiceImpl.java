package cn.ldbz.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.admin.service.AdminIndexRecommendAdService;
import cn.ldbz.advertisement.service.IndexRecommendAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzIndexRecommendAd;
import cn.ldbz.pojo.LdbzResult;

@Component
@Service(version = Const.LDBZ_SHOP_ADMIN_VERSION)
public class AdminIndexRecommendAdServiceImpl implements AdminIndexRecommendAdService{

	@Reference(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
	private IndexRecommendAdService indexRecommendAdService ;
	
	@Override
	public LdbzResult countIndexRecommendAd(LdbzIndexRecommendAd entity) {
		long total = indexRecommendAdService.countByEntity(entity);
		return LdbzResult.ok(total);
	}
	
	@Override
	public LdbzResult getIndexRecommendAdPage(LdbzIndexRecommendAd entity , int pn , int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = indexRecommendAdService.countByEntity(entity);
		map.put("total", total) ;
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzIndexRecommendAd> ret = indexRecommendAdService.getIndexRecommendAdPage(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(0, "", map);
	}

	public LdbzResult selectByKey(Long id) {
		return LdbzResult.ok(indexRecommendAdService.selectByKey(id));
	}
	
	public LdbzResult updateByKey(LdbzIndexRecommendAd entity) {
		return LdbzResult.ok(indexRecommendAdService.updateByKey(entity));
	}

}
