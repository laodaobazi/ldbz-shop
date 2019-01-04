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

import cn.ldbz.admin.service.AdminUserService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzAdminUser;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.utils.ConvertUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/admin/adminUser")
public class AdminUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminUserService adminUserService;
    
    @ApiOperation(value="列表页面跳转", notes="跳转到后台管理用户的列表页面")
    @RequestMapping(value="/adminUser" , method = RequestMethod.GET)
    public String adminRole() {
    	logger.debug("go to adminUser ");
    	return "adminUser";
    }
    
    @ApiOperation(value="新增页面跳转", notes="跳转到后台管理用户新增页面")
    @RequestMapping(value="/addAdminUser" , method = RequestMethod.GET)
    public String addAdminUser() {
    	logger.debug("go to adminUser_add ");
    	return "adminUser_add";
    }
    
    @ApiOperation(value="修改页面跳转", notes="跳转到后台管理用户的修改页面")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "long",paramType = "path")
    @RequestMapping(value="/editAdminUser/{id}" , method = RequestMethod.GET)
    public String editAdminUser(@PathVariable("id")long id , Model model) {
    	logger.debug("go to adminUser_edit id : {}" , id);
    	LdbzResult ret = adminUserService.selectByKey(id);
    	model.addAttribute("user" , ret.getData());
    	return "adminUser_edit";
    }
    
    @ApiOperation(value="分页获取后台管理用户", notes="根据实体LdbzAdminUser分页获取后台管理用户")
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "entity", value = "LdbzAdminUser实体", required = true, dataType = "LdbzAdminUser"),
			@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
			@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int")
	})
    @ResponseBody
    @RequestMapping(value="/getAdminUserPage" , method = RequestMethod.POST)
    public LdbzResult getAdminUserPage(LdbzAdminUser entity , int page , int limit) {
    	String _rn = ConvertUtils.getKey(entity.getRealName());
    	entity.setRealName(_rn);
    	return adminUserService.getAdminUserPage(entity, page, limit);
    }
    
    @ApiOperation(value="获取后台管理用户的详细信息", notes="根据id获取后台管理用户的详细信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "long",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/selectByKey/{id}" , method = RequestMethod.POST)
    public LdbzResult selectByKey(@PathVariable("id")long id) {
    	return adminUserService.selectByKey(id);
    }
    
    @ApiOperation(value="删除后台管理用户", notes="根据id物理删除一条后台管理用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/deleteByKey/{id}" , method = RequestMethod.POST)
    public LdbzResult deleteByKey(@PathVariable("id")String id) {
    	return adminUserService.deleteByKey(id);
    }
    
    @ApiOperation(value="新增用户", notes="创建一条新的用户记录")
    @ApiImplicitParam(name = "entity", value = "LdbzAdminUser实体", required = true, dataType = "LdbzAdminUser")
    @ResponseBody
    @RequestMapping(value="/insertByEntity" , method = RequestMethod.POST)
    public LdbzResult insertByEntity(LdbzAdminUser entity) {
    	Date date = new Date() ;
    	entity.setCreated(date);
    	entity.setUpdated(date);
    	return adminUserService.insertByEntity(entity);
    }
    
    @ApiOperation(value="修改用户", notes="根据Id修改一条用户记录")
    @ApiImplicitParam(name = "entity", value = "LdbzAdminUser实体", required = true, dataType = "LdbzAdminUser")
    @ResponseBody
    @RequestMapping(value="/updateByKey" , method = RequestMethod.POST)
    public LdbzResult updateByKey(LdbzAdminUser entity) {
    	Date date = new Date() ;
    	entity.setUpdated(date);
    	return adminUserService.updateByKey(entity);
    }
    
}
