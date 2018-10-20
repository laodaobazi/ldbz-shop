package cn.ldbz.admin.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.admin.service.AdminRoleService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzAdminRole;
import cn.ldbz.pojo.LdbzResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/admin/adminRole")
public class AdminRoleController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminRoleController.class);

    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminRoleService adminRoleService;
    
    @ApiOperation(value="列表页面跳转", notes="跳转到后台角色的列表页面")
    @RequestMapping(value="/adminRole" , method = RequestMethod.GET)
    String adminRole() {
    	logger.debug("go to adminRole ");
    	return "adminRole";
    }
    
    @ApiOperation(value="新增页面跳转", notes="跳转到后台角色新增页面")
    @RequestMapping(value="/addAdminRole" , method = RequestMethod.GET)
    String addAdminRole() {
    	logger.debug("go to addAdminRole_add ");
    	return "adminRole_add";
    }
    
    @ApiOperation(value="修改页面跳转", notes="跳转到后台角色的修改页面")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "long",paramType = "path")
    @RequestMapping(value="/editAdminRole/{id}" , method = RequestMethod.GET)
    String editAdminRole(@PathVariable("id")long id , Model model) {
    	logger.debug("go to adminRole_edit id : {}" , id);
    	LdbzResult ret = adminRoleService.selectByKey(id);
    	model.addAttribute("role" , ret.getData());
    	return "adminRole_edit";
    }
    
    @ApiOperation(value="分页获取系统角色", notes="根据实体LdbzAdminRole分页获取后台角色列表")
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "entity", value = "LdbzAdminRole实体", required = true, dataType = "LdbzAdminRole"),
			@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
			@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int")
	})
    @ResponseBody
    @RequestMapping(value="/getAdminRolePage" , method = RequestMethod.POST)
    LdbzResult getAdminRolePage(LdbzAdminRole entity , int page , int limit) {
    	return adminRoleService.getAdminRolePage(entity, page, limit);
    }
    
    @ApiOperation(value="获取后台角色的详细信息", notes="根据id获取后台角色的详细信息")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "long",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/selectByKey/{id}" , method = RequestMethod.POST)
    LdbzResult selectByKey(@PathVariable("id")long id) {
    	return adminRoleService.selectByKey(id);
    }
    
    @ApiOperation(value="删除后台角色", notes="根据id物理删除一条后台角色")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "String",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/deleteByKey/{id}" , method = RequestMethod.POST)
    LdbzResult deleteByKey(@PathVariable("id")String id) {
    	return adminRoleService.deleteByKey(id);
    }
    
    @ApiOperation(value="新增角色", notes="创建一条新的角色记录")
    @ApiImplicitParam(name = "entity", value = "LdbzAdminRole实体", required = true, dataType = "LdbzAdminRole")
    @ResponseBody
    @RequestMapping(value="/insertByEntity" , method = RequestMethod.POST)
    LdbzResult insertByEntity(LdbzAdminRole entity) {
    	Date date = new Date() ;
    	entity.setCreated(date);
    	entity.setUpdated(date);
    	return adminRoleService.insertByEntity(entity);
    }
    
    @ApiOperation(value="修改角色", notes="根据Id修改一条角色记录")
    @ApiImplicitParam(name = "entity", value = "LdbzAdminRole实体", required = true, dataType = "LdbzAdminRole")
    @ResponseBody
    @RequestMapping(value="/updateByKey" , method = RequestMethod.POST)
    LdbzResult updateByKey(LdbzAdminRole entity) {
    	Date date = new Date() ;
    	entity.setUpdated(date);
    	return adminRoleService.updateByKey(entity);
    }
    
}
