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

import cn.ldbz.admin.service.AdminSearchLeftAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSearchLeftAd;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/admin/searchLeftAd")
public class AdminSearchLeftAdController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminSearchLeftAdController.class);

    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminSearchLeftAdService adminSearchLeftAdService;
    
    @ApiOperation(value="列表页面跳转", notes="跳转到检索页滑动广告列表页面")
    @RequestMapping(value="/searchLeftAd" , method = RequestMethod.GET)
    public String searchLeftAd() {
    	logger.debug("go to searchLeftAd ");
    	return "searchLeftAd";
    }
    
    @ApiOperation(value="新增页面跳转", notes="跳转到检索页滑动广告新增页面")
    @RequestMapping(value="/addSearchLeftAd" , method = RequestMethod.GET)
    public String addSearchLeftAd() {
    	logger.debug("go to searchLeftAd_add ");
    	return "searchLeftAd_add";
    }
    
    @ApiOperation(value="修改页面跳转", notes="跳转到检索页滑动广告修改页面")
    @ApiImplicitParam(name = "id", value = "广告id", required = true, dataType = "long",paramType = "path")
    @RequestMapping(value="/editSearchLeftAd/{id}" , method = RequestMethod.GET)
    public String editSearchLeftAd(@PathVariable("id")long id , Model model) {
    	logger.debug("go to searchLeftAd_edit id : {}" , id);
    	LdbzResult ret = adminSearchLeftAdService.selectByKey(id);
    	model.addAttribute("ad" , ret.getData());
    	return "searchLeftAd_edit";
    }
    
    @ApiOperation(value="分页获取检索页滑动广告", notes="根据实体LdbzSearchLeftAd分页获取检索页滑动广告")
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "entity", value = "LdbzSearchLeftAd实体", required = true, dataType = "LdbzSearchLeftAd"),
			@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
			@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int")
	})
    @ResponseBody
    @RequestMapping(value="/getSearchLeftAdPage" , method = RequestMethod.POST)
    public LdbzResult getSearchLeftAdPage(LdbzSearchLeftAd entity , int page , int limit) {
    	return adminSearchLeftAdService.getSearchLeftAdPage(entity, page, limit);
    }
    
    @ApiOperation(value="获取广告的详细信息", notes="根据id获取广告的详细信息")
    @ApiImplicitParam(name = "id", value = "广告id", required = true, dataType = "long",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/selectByKey/{id}" , method = RequestMethod.POST)
    public LdbzResult selectByKey(@PathVariable("id")long id) {
    	return adminSearchLeftAdService.selectByKey(id);
    }
    
    @ApiOperation(value="删除广告", notes="根据id物理删除一条广告")
    @ApiImplicitParam(name = "id", value = "广告id", required = true, dataType = "String",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/deleteByKey/{id}" , method = RequestMethod.POST)
    public LdbzResult deleteByKey(@PathVariable("id")String id) {
    	return adminSearchLeftAdService.deleteByKey(id);
    }
    
    @ApiOperation(value="新增广告", notes="创建一条新的广告记录")
    @ApiImplicitParam(name = "entity", value = "LdbzSearchLeftAd实体", required = true, dataType = "LdbzSearchLeftAd")
    @ResponseBody
    @RequestMapping(value="/insertByEntity" , method = RequestMethod.POST)
    public LdbzResult insertByEntity(LdbzSearchLeftAd entity) {
    	Date date = new Date() ;
    	entity.setCreated(date);
    	entity.setUpdated(date);
    	return adminSearchLeftAdService.insertByEntity(entity);
    }
    
    @ApiOperation(value="修改广告", notes="根据Id修改一条广告记录")
    @ApiImplicitParam(name = "entity", value = "LdbzSearchLeftAd实体", required = true, dataType = "LdbzSearchLeftAd")
    @ResponseBody
    @RequestMapping(value="/updateByKey" , method = RequestMethod.POST)
    public LdbzResult updateByKey(LdbzSearchLeftAd entity) {
    	Date date = new Date() ;
    	entity.setUpdated(date);
    	return adminSearchLeftAdService.updateByKey(entity);
    }
    
}
