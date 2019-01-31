package cn.ldbz.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.admin.service.AdminSearchSlideAdService;
import cn.ldbz.advertisement.service.SearchSlideAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSearchSlideAd;

@Component
@Service(version = Const.LDBZ_SHOP_ADMIN_VERSION)
public class AdminSearchSlideAdServiceImpl implements AdminSearchSlideAdService {

	@Reference(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
	private SearchSlideAdService searchSlideAdService ;

	@Override
	public LdbzResult countSearchSlideAd(LdbzSearchSlideAd entity) {
		long total = searchSlideAdService.countByEntity(entity);
		return LdbzResult.ok(total);
	}

	@Override
	public LdbzResult getSearchSlideAdPage(LdbzSearchSlideAd entity, int pn, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = searchSlideAdService.countByEntity(entity);
		map.put("total", total) ;
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzSearchSlideAd> ret = searchSlideAdService.getSearchSlideAdPage(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(0, "", map);
	}

	@Override
	public LdbzResult selectByKey(Long id) {
		return LdbzResult.ok(searchSlideAdService.selectByKey(id));
	}

	@Override
	public LdbzResult deleteByKey(String id) {
		if(StringUtils.contains(id, ",")) {
			List<Long> ids = new ArrayList<Long>();
			for(String _id : StringUtils.split(id , ",")) {
				ids.add(Long.valueOf(_id));
			}
			return LdbzResult.ok(searchSlideAdService.deleteByKeys(ids));
		}else {
			return LdbzResult.ok(searchSlideAdService.deleteByKey(Long.valueOf(id)));
		}
	}

	@Override
	public LdbzResult insertByEntity(LdbzSearchSlideAd entity) {
		if(searchSlideAdService.insertByEntity(entity)>=1) {
			return LdbzResult.ok();
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}

	@Override
	public LdbzResult updateByKey(LdbzSearchSlideAd entity) {
		return LdbzResult.ok(searchSlideAdService.updateByKey(entity));
	}

}
