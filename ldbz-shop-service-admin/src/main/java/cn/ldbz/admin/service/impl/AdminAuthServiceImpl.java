package cn.ldbz.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.admin.service.AdminAuthService;
import cn.ldbz.constant.Const;
import cn.ldbz.mapper.LdbzAdminAuthMapper;
import cn.ldbz.pojo.LdbzAdminAuth;
import cn.ldbz.pojo.LdbzResult;

@Component
@Service(version = Const.LDBZ_SHOP_ADMIN_VERSION)
public class AdminAuthServiceImpl implements AdminAuthService{

	private static final Logger logger = LoggerFactory.getLogger(AdminAuthServiceImpl.class);

	@Autowired
	LdbzAdminAuthMapper mapper ;

	@Override
	public LdbzResult selectUserByRole(Long roleId , int pn , int limit , String realName) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = mapper.countUserByRole(roleId , realName);
		map.put("total", total) ;
		logger.debug("selectUserByRole count : {} " , total);
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzAdminAuth> ret = mapper.selectUserByRole(roleId, start, limit , realName);
			map.put("list", ret) ;
		}
		return LdbzResult.build(0, "", map);
	}
	
	@Override
	public LdbzResult selectMenuByRole(Long roleId , int pn , int limit , String menuName) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = mapper.countMenuByRole(roleId , menuName);
		map.put("total", total) ;
		logger.debug("selectMenuByRole count : {} " , total);
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzAdminAuth> ret = mapper.selectMenuByRole(roleId, start, limit , menuName);
			map.put("list", ret) ;
		}
		return LdbzResult.build(0, "", map);
	}

	public LdbzResult deleteByUser(Long userId , Long roleId) {
		logger.debug("deleteByUser userId : {} " , userId);
		return LdbzResult.ok(mapper.deleteByUser(userId , roleId));
	}
	
	public LdbzResult deleteByMenu(Long menuId , Long roleId) {
		logger.debug("deleteByUser menuId : {} " , menuId);
		return LdbzResult.ok(mapper.deleteByMenu(menuId , roleId));
	}
	
	public LdbzResult insertByUser(LdbzAdminAuth entity) {
		logger.debug("insertByUser entity : {} " , entity);
		if(mapper.insertByUser(entity)>=1) {
			return LdbzResult.ok();
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}
	
	public LdbzResult insertByMenu(LdbzAdminAuth entity) {
		logger.debug("insertByMenu entity : {} " , entity);
		if(mapper.insertByMenu(entity)>=1) {
			return LdbzResult.ok();
		}else {
			return LdbzResult.build(500, "添加失败");
		}
	}
	
	public LdbzResult getUserByNameOrAccount(String queryKey) {
		logger.debug("getUserByNameOrAccount : {} " , queryKey);
		List<LdbzAdminAuth> ret = mapper.getUserByNameOrAccount(queryKey);
		return LdbzResult.ok(ret);
	}
	
	public LdbzResult getResByNameOrUrl(String queryKey) {
		logger.debug("getResByNameOrUrl : {} " , queryKey);
		List<LdbzAdminAuth> ret = mapper.getResByNameOrUrl(queryKey);
		return LdbzResult.ok(ret);
	}

}
