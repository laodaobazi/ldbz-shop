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

import cn.ldbz.constant.Const;
import cn.ldbz.item.service.SheetService;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSheet;
import cn.ldbz.utils.ConvertUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/admin/sheet")
public class AdminSheetController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminSheetController.class);

    @Reference(version = Const.LDBZ_SHOP_SHEET_VERSION, timeout=30000)
    private SheetService sheetService;
    
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
    	LdbzResult ret = sheetService.selectByKey(id);
    	model.addAttribute("sheet" , ret.getData());
    	return "indexSheet_edit";
    }
    
    @ApiOperation(value="板块分配商品", notes="跳转到板块分配商品的页面")
    @ApiImplicitParam(name = "id", value = "产品id", required = true, dataType = "long",paramType = "path")
    @RequestMapping(value="/assignSheet/{id}" , method = RequestMethod.GET)
    public String assignSheet(@PathVariable("id")String id , Model model) {
    	logger.debug("go to indexSheet_assign id : {}" , id);
    	model.addAttribute("id" , id);
    	return "indexSheet_assign";
    }
    
    @ApiOperation(value="获取商品板块信息", notes="根据条件获取商品板块信息")
    @ApiImplicitParam(name = "entity", value = "LdbzSheet实体", required = true, dataType = "LdbzSheet")
    @ResponseBody
    @RequestMapping(value="/getSheetList" , method = RequestMethod.POST)
    public LdbzResult getSheetList(LdbzSheet entity) {
    	logger.debug("go to getSheetList  : {}" , entity);
    	return sheetService.getSheetList(entity);
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
    	return sheetService.getSheetPage(entity, page, limit);
    }
    
    @ApiOperation(value="修改商品板块", notes="根据Id修改一条商品板块")
    @ApiImplicitParam(name = "entity", value = "LdbzSheet实体", required = true, dataType = "LdbzSheet")
    @ResponseBody
    @RequestMapping(value="/updateByKey" , method = RequestMethod.POST)
    public LdbzResult updateByKey(LdbzSheet entity) {
    	Date date = new Date() ;
    	entity.setUpdated(date);
    	return sheetService.updateByKey(entity);
    }
    
}
