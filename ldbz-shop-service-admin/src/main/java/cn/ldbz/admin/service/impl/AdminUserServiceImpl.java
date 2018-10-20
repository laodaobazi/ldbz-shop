package cn.ldbz.admin.service.impl;

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

import cn.ldbz.admin.service.AdminUserService;
import cn.ldbz.constant.Const;
import cn.ldbz.mapper.LdbzAdminUserMapper;
import cn.ldbz.pojo.LdbzAdminUser;
import cn.ldbz.pojo.LdbzResult;

@Component
@Service(version = Const.LDBZ_SHOP_ADMIN_VERSION)
public class AdminUserServiceImpl implements AdminUserService{

	private static final Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);

	@Autowired
	LdbzAdminUserMapper mapper ;

	@Override
	public LdbzResult getAdminUserPage(LdbzAdminUser entity , int pn , int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = mapper.countByEntity(entity);
		map.put("total", total) ;
		logger.debug("getAdminUserPage count : {} " , total);
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzAdminUser> ret = mapper.selectByEntity(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(0, "", map);
	}

	public LdbzResult selectByKey(Long id) {
		logger.debug("selectByKey id : {} " , id);
		return LdbzResult.ok(mapper.selectByKey(id));
	}
	
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
	
	public LdbzResult insertByEntity(LdbzAdminUser entity) {
		logger.debug("insertByEntity entity : {} " , entity);
		if(mapper.insertByEntity(entity)>=1) {
			return LdbzResult.ok();
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}
	
	public LdbzResult updateByKey(LdbzAdminUser entity) {
		logger.debug("updateByKey entity : {} " , entity);
		return LdbzResult.ok(mapper.updateByKey(entity));
	}

}
