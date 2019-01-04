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

import cn.ldbz.admin.service.AdminMenuService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzAdminMenu;
import cn.ldbz.pojo.LdbzResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/admin/adminMenu")
public class AdminMenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminMenuController.class);

    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminMenuService adminMenuService;
    
    @ApiOperation(value="列表页面跳转", notes="跳转到后台管理菜单的列表页面")
    @RequestMapping(value="/adminMenu" , method = RequestMethod.GET)
    public String adminRole() {
    	logger.debug("go to adminMenu ");
    	return "adminMenu";
    }
    
    @ApiOperation(value="新增页面跳转", notes="跳转到后台管理菜单新增页面")
    @RequestMapping(value="/addAdminMenu" , method = RequestMethod.GET)
    public String addAdminMenu() {
    	logger.debug("go to adminMenu_add ");
    	return "adminMenu_add";
    }
    
    @ApiOperation(value="修改页面跳转", notes="跳转到后台管理菜单的修改页面")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "long",paramType = "path")
    @RequestMapping(value="/editAdminMenu/{id}" , method = RequestMethod.GET)
    public String editAdminMenu(@PathVariable("id")long id , Model model) {
    	logger.debug("go to adminMenu_edit id : {}" , id);
    	LdbzResult ret = adminMenuService.selectByKey(id);
    	model.addAttribute("menu" , ret.getData());
    	return "adminMenu_edit";
    }
    
    @ApiOperation(value="分页获取后台管理菜单", notes="根据实体LdbzAdminMenu分页获取后台管理菜单")
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "entity", value = "LdbzAdminMenu实体", required = true, dataType = "LdbzAdminMenu"),
			@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
			@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int")
	})
    @ResponseBody
    @RequestMapping(value="/getAdminMenuPage" , method = RequestMethod.POST)
    public LdbzResult getAdminMenuPage(LdbzAdminMenu entity , int page , int limit) {
    	return adminMenuService.getAdminMenuPage(entity, page, limit);
    }
    
    @ApiOperation(value="获取树形菜单", notes="获取树形菜单")
    @ResponseBody
    @RequestMapping(value="/getMenuTree" , method = RequestMethod.POST)
    public LdbzResult getMenuTree() {
    	return adminMenuService.getMenuTree();
    }
    
    @ApiOperation(value="获取后台管理菜单的详细信息", notes="根据id获取后台管理菜单的详细信息")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "long",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/selectByKey/{id}" , method = RequestMethod.POST)
    public LdbzResult selectByKey(@PathVariable("id")long id) {
    	return adminMenuService.selectByKey(id);
    }
    
    @ApiOperation(value="删除后台管理菜单", notes="根据id物理删除一条后台管理菜单")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "String",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/deleteByKey/{id}" , method = RequestMethod.POST)
    public LdbzResult deleteByKey(@PathVariable("id")String id) {
    	return adminMenuService.deleteByKey(id);
    }
    
    @ApiOperation(value="新增菜单", notes="创建一条新的菜单记录")
    @ApiImplicitParam(name = "entity", value = "LdbzAdminMenu实体", required = true, dataType = "LdbzAdminMenu")
    @ResponseBody
    @RequestMapping(value="/insertByEntity" , method = RequestMethod.POST)
    public LdbzResult insertByEntity(LdbzAdminMenu entity) {
    	Date date = new Date() ;
    	entity.setCreated(date);
    	entity.setUpdated(date);
    	return adminMenuService.insertByEntity(entity);
    }
    
    @ApiOperation(value="修改菜单", notes="根据Id修改一条菜单记录")
    @ApiImplicitParam(name = "entity", value = "LdbzAdminMenu实体", required = true, dataType = "LdbzAdminMenu")
    @ResponseBody
    @RequestMapping(value="/updateByKey" , method = RequestMethod.POST)
    public LdbzResult updateByKey(LdbzAdminMenu entity) {
    	Date date = new Date() ;
    	entity.setUpdated(date);
    	return adminMenuService.updateByKey(entity);
    }
    
}
