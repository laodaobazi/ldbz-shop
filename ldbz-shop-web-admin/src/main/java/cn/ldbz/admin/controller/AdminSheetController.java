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

import cn.ldbz.admin.service.AdminSheetService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSheet;
import cn.ldbz.pojo.LdbzSheetAssign;
import cn.ldbz.utils.ConvertUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/admin/sheet")
public class AdminSheetController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminSheetController.class);

	@Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminSheetService adminSheetService;
    
    @ApiOperation(value="产品板块跳转", notes="跳转到产品板块页面")
    @RequestMapping(value="/indexSheet" , method = RequestMethod.GET)
    public String indexSheet() {
    	logger.debug("go to indexSheet");
    	return "indexSheet";
    }
    
    @ApiOperation(value="修改板块页面跳转", notes="跳转到板块修改页面")
    @ApiImplicitParam(name = "id", value = "产品id", required = true, dataType = "long",paramType = "path")
    @RequestMapping(value="/editSheet/{id}" , method = RequestMethod.GET)
    public String editSheet(@PathVariable("id")long id , Model model) {
    	logger.debug("go to indexSheet_edit id : {}" , id);
    	LdbzResult ret = adminSheetService.selectByKey(id);
    	model.addAttribute("sheet" , ret.getData());
    	return "indexSheet_edit";
    }
    
    @ApiOperation(value="获取商品板块信息", notes="根据条件获取商品板块信息")
    @ApiImplicitParam(name = "entity", value = "LdbzSheet实体", required = true, dataType = "LdbzSheet")
    @ResponseBody
    @RequestMapping(value="/getSheetList" , method = RequestMethod.POST)
    public LdbzResult getSheetList(LdbzSheet entity) {
    	logger.debug("go to getSheetList  : {}" , entity);
    	return adminSheetService.getSheetList(entity);
    }
    
    @ApiOperation(value="分页获取产品板块", notes="根据实体LdbzSheet分页获取商品板块")
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "entity", value = "LdbzSheet实体", required = true, dataType = "LdbzSheet"),
			@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
			@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int")
	})
    @ResponseBody
    @RequestMapping(value="/getSheetPage" , method = RequestMethod.POST)
    public LdbzResult getSheetPage(LdbzSheet entity , int page , int limit) {
    	entity.setSheetName(ConvertUtils.getKey(entity.getSheetName()));
    	return adminSheetService.getSheetPage(entity, page, limit);
    }
    
    @ApiOperation(value="修改商品板块", notes="根据Id修改一条商品板块")
    @ApiImplicitParam(name = "entity", value = "LdbzSheet实体", required = true, dataType = "LdbzSheet")
    @ResponseBody
    @RequestMapping(value="/updateByKey" , method = RequestMethod.POST)
    public LdbzResult updateByKey(LdbzSheet entity) {
    	Date date = new Date() ;
    	entity.setUpdated(date);
    	return adminSheetService.updateByKey(entity);
    }
    
    @ApiOperation(value="板块分配商品", notes="跳转到板块分配商品的页面")
    @ApiImplicitParam(name = "id", value = "产品id", required = true, dataType = "long",paramType = "path")
    @RequestMapping(value="/assignSheet/{id}" , method = RequestMethod.GET)
    public String assignSheet(@PathVariable("id")String id , Model model) {
    	logger.debug("go to indexSheet_assign id : {}" , id);
    	LdbzResult ret = adminSheetService.selectByKey(Long.valueOf(id));
    	model.addAttribute("sheet" , ret.getData());
    	return "indexSheet_assign";
    }
    
    @ApiOperation(value="获取板块分配商品的信息", notes="获取板块分配商品的信息")
    @ApiImplicitParam(name = "id", value = "板块id", required = true, dataType = "long",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/getSheetAssignList/{id}" , method = RequestMethod.POST)
    public LdbzResult getSheetAssignList(@PathVariable("id")long sheetId) {
    	logger.debug("go to getSheetAssignList  : {}" , sheetId);
    	return adminSheetService.getSheetAssignList(sheetId);
    }
    
    @ApiOperation(value="删除分配的商品", notes="根据id物理删除分配后的商品")
    @ApiImplicitParam(name = "id", value = "分配id", required = true, dataType = "String",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/deleteAssign/{id}" , method = RequestMethod.POST)
    public LdbzResult deleteAssign(@PathVariable("id")String id) {
    	return adminSheetService.deleteAssign(id);
    }
    
    @ApiOperation(value="板块分配商品", notes="为板块分配商品")
    @ApiImplicitParam(name = "entity", value = "LdbzSheetAssign实体", required = true, dataType = "LdbzSheetAssign")
    @ResponseBody
    @RequestMapping(value="/addAssign" , method = RequestMethod.POST)
    public LdbzResult addAssign(LdbzSheetAssign entity) {
    	Date date = new Date() ;
    	entity.setCreated(date);
    	return adminSheetService.addAssign(entity);
    }
    
}
