package cn.ldbz.admin.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.admin.service.AdminAuthService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzAdminAuth;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.utils.ConvertUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/admin/adminAuth")
public class AdminAuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminAuthController.class);

    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminAuthService adminAuthService;
    
    @ApiOperation(value="用户页面跳转", notes="跳转到该角色下所有用户的页面")
    @RequestMapping(value="/user/{roleId}" , method = RequestMethod.GET)
    String adminAuthUser(@PathVariable("roleId") Long roleId , Model m) {
    	logger.debug("go to adminAuthUser {} " , roleId);
    	m.addAttribute("roleId", roleId);
    	return "adminAuthUser";
    }
    
    @ApiOperation(value="资源页面跳转", notes="跳转到该角色下所有资源的页面")
    @RequestMapping(value="/menu/{roleId}" , method = RequestMethod.GET)
    String adminAuthMenu(@PathVariable("roleId") Long roleId , Model m) {
    	logger.debug("go to adminAuthMenu {} " , roleId);
    	m.addAttribute("roleId", roleId);
    	return "adminAuthMenu";
    }
    
    @ApiOperation(value="获取用户", notes="根据该角色ID中有哪些用户")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "long"),
		@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
		@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int"),
    	@ApiImplicitParam(name = "realName", value = "用户的姓名", dataType = "string")
    })
    @ResponseBody
    @RequestMapping(value="/selectUserByRole" , method = RequestMethod.POST)
    LdbzResult selectUserByRole(Long roleId , int page , int limit , 
    		@RequestParam(value="realName", defaultValue="")String realName) {
    	return adminAuthService.selectUserByRole(roleId, page, limit , ConvertUtils.getKey(realName));
    }
    
    @ApiOperation(value="获取资源", notes="根据该角色ID中有哪些资源")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "long"),
		@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
		@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int"),
    	@ApiImplicitParam(name = "menuName", value = "资源的名字", required = true, dataType = "string")
    })
    @ResponseBody
    @RequestMapping(value="/selectMenuByRole" , method = RequestMethod.POST)
    LdbzResult selectMenuByRole(Long roleId , int page , int limit , 
    		@RequestParam(value="menuName", defaultValue="")String menuName) {
    	return adminAuthService.selectMenuByRole(roleId, page, limit , ConvertUtils.getKey(menuName));
    }

    @ApiOperation(value="删除角色下的用户", notes="物理删除一条改角色下的用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "int"),
		@ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "int")
	})
    @ResponseBody
    @RequestMapping(value="/deleteByUser" , method = RequestMethod.POST)
    LdbzResult deleteByUser(Long userId , Long roleId) {
    	return adminAuthService.deleteByUser(userId , roleId);
    }
    
    @ApiOperation(value="删除角色下的资源", notes="物理删除一条改角色下的资源")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "menuId", value = "资源id", required = true, dataType = "int"),
    	@ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "int")
    })
    @ResponseBody
    @RequestMapping(value="/deleteByMenu" , method = RequestMethod.POST)
    LdbzResult deleteByMenu(Long menuId , Long roleId) {
    	return adminAuthService.deleteByMenu(menuId , roleId);
    }
    
    @ApiOperation(value="角色分配用户", notes="给指点的角色分配用户")
    @ApiImplicitParam(name = "entity", value = "LdbzAdminAuth实体", required = true, dataType = "LdbzAdminAuth")
    @ResponseBody
    @RequestMapping(value="/insertByUser" , method = RequestMethod.POST)
    LdbzResult insertByUser(LdbzAdminAuth entity) {
    	Date date = new Date() ;
    	entity.setCreated(date);
    	return adminAuthService.insertByUser(entity);
    }
    
    @ApiOperation(value="角色分配资源", notes="给指点的角色分配资源")
    @ApiImplicitParam(name = "entity", value = "LdbzAdminAuth实体", required = true, dataType = "LdbzAdminAuth")
    @ResponseBody
    @RequestMapping(value="/insertByMenu" , method = RequestMethod.POST)
    LdbzResult insertByMenu(LdbzAdminAuth entity) {
    	Date date = new Date() ;
    	entity.setCreated(date);
    	return adminAuthService.insertByMenu(entity);
    }
    
    @ApiOperation(value="获取用户信息", notes="根据用户的账号或者名字获取账号信息")
    @ApiImplicitParam(name = "queryKey", value = "用户的账号或者名字", required = true, dataType = "string")
    @ResponseBody
    @RequestMapping(value="/getUserByNameOrAccount" , method = RequestMethod.POST)
    LdbzResult getUserByNameOrAccount(String queryKey) {
    	return adminAuthService.getUserByNameOrAccount(ConvertUtils.getKey(queryKey));
    }
    
    @ApiOperation(value="获取资源信息", notes="根据资源的名称或请求路径获取资源信息")
    @ApiImplicitParam(name = "queryKey", value = "资源的名称或请求路径", required = true, dataType = "string")
    @ResponseBody
    @RequestMapping(value="/getResByNameOrUrl" , method = RequestMethod.POST)
    LdbzResult getResByNameOrUrl(String queryKey) {
    	return adminAuthService.getResByNameOrUrl(ConvertUtils.getKey(queryKey));
    }
    
}
