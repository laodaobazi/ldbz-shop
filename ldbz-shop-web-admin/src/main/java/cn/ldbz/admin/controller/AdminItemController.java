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

import cn.ldbz.admin.service.AdminItemService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzItem;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.utils.ConvertUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/admin/item")
public class AdminItemController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminItemController.class);

    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminItemService adminItemService;
    
    @ApiOperation(value="产品页面跳转", notes="跳转到产品页面")
    @RequestMapping(value="/indexItem" , method = RequestMethod.GET)
    public String adminAuthUser() {
    	logger.debug("go to indexItem");
    	return "indexItem";
    }
    
    @ApiOperation(value="新增页面跳转", notes="跳转到产品新增页面")
    @RequestMapping(value="/addItem" , method = RequestMethod.GET)
    public String addItem() {
    	logger.debug("go to indexItem_add ");
    	return "indexItem_add";
    }
    
    @ApiOperation(value="修改页面跳转", notes="跳转到产品修改页面")
    @ApiImplicitParam(name = "id", value = "产品id", required = true, dataType = "long",paramType = "path")
    @RequestMapping(value="/editItem/{id}" , method = RequestMethod.GET)
    public String editItem(@PathVariable("id")long id , Model model) {
    	logger.debug("go to indexItem_edit id : {}" , id);
    	LdbzResult ret = adminItemService.selectByKey(id);
    	model.addAttribute("item" , ret.getData());
    	return "indexItem_edit";
    }
    
    @ApiOperation(value="获取商品信息", notes="根据条件获取商品信息")
    @ApiImplicitParam(name = "entity", value = "LdbzItem实体", required = true, dataType = "LdbzItem")
    @ResponseBody
    @RequestMapping(value="/getItemList" , method = RequestMethod.POST)
    public LdbzResult getItemList(LdbzItem entity) {
    	logger.debug("go to getItemList  : {}" , entity);
    	entity.setTitle(ConvertUtils.getKey(entity.getTitle()));
    	entity.setCode(ConvertUtils.getKey(entity.getCode()));
    	return adminItemService.getItemList(entity);
    }
    
    @ApiOperation(value="分页获取产品", notes="根据实体LdbzItem分页获取商品")
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "entity", value = "LdbzItem实体", required = true, dataType = "LdbzItem"),
			@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
			@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int")
	})
    @ResponseBody
    @RequestMapping(value="/getItemPage" , method = RequestMethod.POST)
    public LdbzResult getItemPage(LdbzItem entity , int page , int limit) {
    	entity.setTitle(ConvertUtils.getKey(entity.getTitle()));
    	return adminItemService.getItemPage(entity, page, limit);
    }
    
    @ApiOperation(value="删除商品", notes="根据id物理删除一条商品")
    @ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "String",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/deleteByKey/{id}" , method = RequestMethod.POST)
    public LdbzResult deleteByKey(@PathVariable("id")String id) {
    	return adminItemService.deleteByKey(id);
    }
    
    @ApiOperation(value="新增商品", notes="创建一条新的商品")
    @ApiImplicitParam(name = "entity", value = "LdbzItem实体", required = true, dataType = "LdbzItem")
    @ResponseBody
    @RequestMapping(value="/insertByEntity" , method = RequestMethod.POST)
    public LdbzResult insertByEntity(LdbzItem entity) {
    	Date date = new Date() ;
    	entity.setCreated(date);
    	entity.setCode(String.valueOf(System.currentTimeMillis()));
    	return adminItemService.insertByEntity(entity);
    }
    
    @ApiOperation(value="修改商品", notes="根据Id修改一条商品")
    @ApiImplicitParam(name = "entity", value = "LdbzItem实体", required = true, dataType = "LdbzItem")
    @ResponseBody
    @RequestMapping(value="/updateByKey" , method = RequestMethod.POST)
    public LdbzResult updateByKey(LdbzItem entity) {
    	Date date = new Date() ;
    	entity.setUpdated(date);
    	return adminItemService.updateByKey(entity);
    }
    
}
