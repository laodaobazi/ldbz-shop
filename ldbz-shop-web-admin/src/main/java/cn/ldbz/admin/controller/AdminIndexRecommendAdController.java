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

import cn.ldbz.admin.service.AdminIndexRecommendAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzIndexRecommendAd;
import cn.ldbz.pojo.LdbzResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/admin/indexRecommendAd")
public class AdminIndexRecommendAdController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminIndexRecommendAdController.class);

    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminIndexRecommendAdService adminIndexRecommendAdService;
    
    @ApiOperation(value="列表页面跳转", notes="跳转到首页推荐广告列表页面")
    @RequestMapping(value="/indexRecommendAd" , method = RequestMethod.GET)
    public String indexRecommendAd() {
    	logger.debug("go to indexRecommendAd ");
    	return "indexRecommendAd";
    }
    
    @ApiOperation(value="新增页面跳转", notes="跳转到首页推荐广告新增页面")
    @RequestMapping(value="/addIndexRecommendAd" , method = RequestMethod.GET)
    public String addIndexRecommendAd() {
    	logger.debug("go to indexRecommendAd_add ");
    	return "indexRecommendAd_add";
    }
    
    @ApiOperation(value="修改页面跳转", notes="跳转到首页推荐广告修改页面")
    @ApiImplicitParam(name = "id", value = "广告id", required = true, dataType = "long",paramType = "path")
    @RequestMapping(value="/editIndexRecommendAd/{id}" , method = RequestMethod.GET)
    public String editIndexRecommendAd(@PathVariable("id")long id , Model model) {
    	logger.debug("go to indexRecommendAd_edit id : {}" , id);
    	LdbzResult ret = adminIndexRecommendAdService.selectByKey(id);
    	model.addAttribute("ad" , ret.getData());
    	return "indexRecommendAd_edit";
    }
    
    @ApiOperation(value="分页获取首页推荐广告", notes="根据实体LdbzIndexRecommendAd分页获取首页推荐广告")
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "entity", value = "LdbzIndexRecommendAd实体", required = true, dataType = "LdbzIndexRecommendAd"),
			@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
			@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int")
	})
    @ResponseBody
    @RequestMapping(value="/getIndexRecommendAdPage" , method = RequestMethod.POST)
    public LdbzResult getIndexRecommendAdPage(LdbzIndexRecommendAd entity , int page , int limit) {
    	return adminIndexRecommendAdService.getIndexRecommendAdPage(entity, page, limit);
    }
    
    @ApiOperation(value="获取广告的详细信息", notes="根据id获取广告的详细信息")
    @ApiImplicitParam(name = "id", value = "广告id", required = true, dataType = "long",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/selectByKey/{id}" , method = RequestMethod.POST)
    public LdbzResult selectByKey(@PathVariable("id")long id) {
    	return adminIndexRecommendAdService.selectByKey(id);
    }
    
    @ApiOperation(value="修改广告", notes="根据Id修改一条广告记录")
    @ApiImplicitParam(name = "entity", value = "LdbzIndexRecommendAd实体", required = true, dataType = "LdbzIndexRecommendAd")
    @ResponseBody
    @RequestMapping(value="/updateByKey" , method = RequestMethod.POST)
    public LdbzResult updateByKey(LdbzIndexRecommendAd entity) {
    	Date date = new Date() ;
    	entity.setUpdated(date);
    	return adminIndexRecommendAdService.updateByKey(entity);
    }
    
}
