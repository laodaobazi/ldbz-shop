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

import cn.ldbz.admin.service.AdminSearchSlideAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzSearchSlideAd;
import cn.ldbz.pojo.LdbzResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/admin/searchSlideAd")
public class AdminSearchSlideAdController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminSearchSlideAdController.class);

    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminSearchSlideAdService adminSearchSlideAdService;
    
    @ApiOperation(value="列表页面跳转", notes="跳转到检索页滑动广告列表页面")
    @RequestMapping(value="/searchSlideAd" , method = RequestMethod.GET)
    public String searchSlideAd() {
    	logger.debug("go to searchSlideAd ");
    	return "searchSlideAd";
    }
    
    @ApiOperation(value="新增页面跳转", notes="跳转到检索页滑动广告新增页面")
    @RequestMapping(value="/addSearchSlideAd" , method = RequestMethod.GET)
    public String addSearchSlideAd() {
    	logger.debug("go to searchSlideAd_add ");
    	return "searchSlideAd_add";
    }
    
    @ApiOperation(value="修改页面跳转", notes="跳转到检索页滑动广告修改页面")
    @ApiImplicitParam(name = "id", value = "广告id", required = true, dataType = "long",paramType = "path")
    @RequestMapping(value="/editSearchSlideAd/{id}" , method = RequestMethod.GET)
    public String editSearchSlideAd(@PathVariable("id")long id , Model model) {
    	logger.debug("go to searchSlideAd_edit id : {}" , id);
    	LdbzResult ret = adminSearchSlideAdService.selectByKey(id);
    	model.addAttribute("ad" , ret.getData());
    	return "searchSlideAd_edit";
    }
    
    @ApiOperation(value="分页获取检索页滑动广告", notes="根据实体LdbzSearchSlideAd分页获取检索页滑动广告")
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "entity", value = "LdbzSearchSlideAd实体", required = true, dataType = "LdbzSearchSlideAd"),
			@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
			@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int")
	})
    @ResponseBody
    @RequestMapping(value="/getSearchSlideAdPage" , method = RequestMethod.POST)
    public LdbzResult getSearchSlideAdPage(LdbzSearchSlideAd entity , int page , int limit) {
    	return adminSearchSlideAdService.getSearchSlideAdPage(entity, page, limit);
    }
    
    @ApiOperation(value="获取广告的详细信息", notes="根据id获取广告的详细信息")
    @ApiImplicitParam(name = "id", value = "广告id", required = true, dataType = "long",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/selectByKey/{id}" , method = RequestMethod.POST)
    public LdbzResult selectByKey(@PathVariable("id")long id) {
    	return adminSearchSlideAdService.selectByKey(id);
    }
    
    @ApiOperation(value="删除广告", notes="根据id物理删除一条广告")
    @ApiImplicitParam(name = "id", value = "广告id", required = true, dataType = "String",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/deleteByKey/{id}" , method = RequestMethod.POST)
    public LdbzResult deleteByKey(@PathVariable("id")String id) {
    	return adminSearchSlideAdService.deleteByKey(id);
    }
    
    @ApiOperation(value="新增广告", notes="创建一条新的广告记录")
    @ApiImplicitParam(name = "entity", value = "LdbzSearchSlideAd实体", required = true, dataType = "LdbzSearchSlideAd")
    @ResponseBody
    @RequestMapping(value="/insertByEntity" , method = RequestMethod.POST)
    public LdbzResult insertByEntity(LdbzSearchSlideAd entity) {
    	Date date = new Date() ;
    	entity.setCreated(date);
    	entity.setUpdated(date);
    	return adminSearchSlideAdService.insertByEntity(entity);
    }
    
    @ApiOperation(value="修改广告", notes="根据Id修改一条广告记录")
    @ApiImplicitParam(name = "entity", value = "LdbzSearchSlideAd实体", required = true, dataType = "LdbzSearchSlideAd")
    @ResponseBody
    @RequestMapping(value="/updateByKey" , method = RequestMethod.POST)
    public LdbzResult updateByKey(LdbzSearchSlideAd entity) {
    	Date date = new Date() ;
    	entity.setUpdated(date);
    	return adminSearchSlideAdService.updateByKey(entity);
    }
    
}
