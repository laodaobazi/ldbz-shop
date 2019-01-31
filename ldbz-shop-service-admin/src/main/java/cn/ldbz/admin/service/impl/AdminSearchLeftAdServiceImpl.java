package cn.ldbz.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.admin.service.AdminSearchLeftAdService;
import cn.ldbz.advertisement.service.SearchLeftAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSearchLeftAd;

@Component
@Service(version = Const.LDBZ_SHOP_ADMIN_VERSION)
public class AdminSearchLeftAdServiceImpl implements AdminSearchLeftAdService {

	@Reference(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
	private SearchLeftAdService searchLeftAdService ;

	@Override
	public LdbzResult countSearchLeftAd(LdbzSearchLeftAd entity) {
		long total = searchLeftAdService.countByEntity(entity);
		return LdbzResult.ok(total);
	}

	@Override
	public LdbzResult getSearchLeftAdPage(LdbzSearchLeftAd entity, int pn, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = searchLeftAdService.countByEntity(entity);
		map.put("total", total) ;
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzSearchLeftAd> ret = searchLeftAdService.getSearchLeftAdPage(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(0, "", map);
	}

	@Override
	public LdbzResult selectByKey(Long id) {
		return LdbzResult.ok(searchLeftAdService.selectByKey(id));
	}

	@Override
	public LdbzResult deleteByKey(String id) {
		if(StringUtils.contains(id, ",")) {
			List<Long> ids = new ArrayList<Long>();
			for(String _id : StringUtils.split(id , ",")) {
				ids.add(Long.valueOf(_id));
			}
			return LdbzResult.ok(searchLeftAdService.deleteByKeys(ids));
		}else {
			return LdbzResult.ok(searchLeftAdService.deleteByKey(Long.valueOf(id)));
		}
	}

	@Override
	public LdbzResult insertByEntity(LdbzSearchLeftAd entity) {
		if(searchLeftAdService.insertByEntity(entity)>=1) {
			return LdbzResult.ok();
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}

	@Override
	public LdbzResult updateByKey(LdbzSearchLeftAd entity) {
		return LdbzResult.ok(searchLeftAdService.updateByKey(entity));
	}

}
