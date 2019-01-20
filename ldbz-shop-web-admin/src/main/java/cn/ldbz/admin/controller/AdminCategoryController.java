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

import cn.ldbz.admin.service.AdminCategoryService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzCategory;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.utils.ConvertUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminCategoryController.class);

    @Reference(version = Const.LDBZ_SHOP_ADMIN_VERSION, timeout=30000)
    private AdminCategoryService adminCategoryService;
    
    @ApiOperation(value="产品分类页面跳转", notes="跳转到产品分类页面")
    @RequestMapping(value="/indexCategory" , method = RequestMethod.GET)
    public String indexCategory() {
    	logger.debug("go to indexCategory");
    	return "indexCategory";
    }
    
    @ApiOperation(value="新增页面跳转", notes="跳转到产品分类新增页面")
    @RequestMapping(value="/addCategory" , method = RequestMethod.GET)
    public String addCategory() {
    	logger.debug("go to indexCategory_add ");
    	return "indexCategory_add";
    }
    
    @ApiOperation(value="修改页面跳转", notes="跳转到产品分类修改页面")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "long",paramType = "path")
    @RequestMapping(value="/editCategory/{id}" , method = RequestMethod.GET)
    public String editCategory(@PathVariable("id")long id , Model model) {
    	logger.debug("go to indexCategory_edit id : {}" , id);
    	LdbzResult ret = adminCategoryService.selectByKey(id);
    	model.addAttribute("category" , ret.getData());
    	return "indexCategory_edit";
    }
    
    @ApiOperation(value="获取分类信息", notes="根据条件获取分类信息")
    @ApiImplicitParam(name = "entity", value = "LdbzCategory实体", required = true, dataType = "LdbzCategory")
    @ResponseBody
    @RequestMapping(value="/getCategoryList" , method = RequestMethod.POST)
    public LdbzResult getCategoryList(LdbzCategory entity) {
    	entity.setCategoryName(ConvertUtils.getKey(entity.getCategoryName()));
    	logger.debug("go to getCategoryList  : {}" , entity);
    	return adminCategoryService.getCategoryList(entity);
    }
    
    @ApiOperation(value="分页获取产品分类", notes="根据实体LdbzCategory分页获取产品分类")
    @ApiImplicitParams({
	    	@ApiImplicitParam(name = "entity", value = "LdbzCategory实体", required = true, dataType = "LdbzCategory"),
			@ApiImplicitParam(name = "page", value = "查询的页数", required = true, dataType = "int"),
			@ApiImplicitParam(name = "limit", value = "每页显示的条数", required = true, dataType = "int")
	})
    @ResponseBody
    @RequestMapping(value="/getCategoryPage" , method = RequestMethod.POST)
    public LdbzResult getCategoryPage(LdbzCategory entity , int page , int limit) {
    	entity.setCategoryName(ConvertUtils.getKey(entity.getCategoryName()));
    	return adminCategoryService.getCategoryPage(entity, page, limit);
    }
    
    @ApiOperation(value="删除产品分类", notes="根据id物理删除一条产品分类")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "String",paramType = "path")
    @ResponseBody
    @RequestMapping(value="/deleteByKey/{id}" , method = RequestMethod.POST)
    public LdbzResult deleteByKey(@PathVariable("id")String id) {
    	return adminCategoryService.deleteByKey(id);
    }
    
    @ApiOperation(value="新增产品分类", notes="创建一条新的产品分类")
    @ApiImplicitParam(name = "entity", value = "LdbzCategory实体", required = true, dataType = "LdbzCategory")
    @ResponseBody
    @RequestMapping(value="/insertByEntity" , method = RequestMethod.POST)
    public LdbzResult insertByEntity(LdbzCategory entity) {
    	Date date = new Date() ;
    	entity.setCreated(date);
    	entity.setUpdated(date);
    	return adminCategoryService.insertByEntity(entity);
    }
    
    @ApiOperation(value="修改产品分类", notes="根据Id修改一条产品分类")
    @ApiImplicitParam(name = "entity", value = "LdbzCategory实体", required = true, dataType = "LdbzCategory")
    @ResponseBody
    @RequestMapping(value="/updateByKey" , method = RequestMethod.POST)
    public LdbzResult updateByKey(LdbzCategory entity) {
    	Date date = new Date() ;
    	entity.setUpdated(date);
    	return adminCategoryService.updateByKey(entity);
    }
    
    @ApiOperation(value="根据父节点获取其子元素", notes="根据父节点ID获取其所有子元素")
    @ApiImplicitParam(name = "fid", value = "父节点ID", required = true, dataType = "long", paramType = "path")
    @ResponseBody
    @RequestMapping(value="/getCategoryTree/{fid}" , method = RequestMethod.POST)
    public LdbzResult getCategoryTree(@PathVariable("fid")long fid) {
    	return adminCategoryService.getCategoryTree(fid);
    }
    
    @ApiOperation(value="更新分类的排序", notes="更新分类的排序")
    @ApiImplicitParam(name = "ids", value = "排序之后节点ID，逗号隔开", required = true, dataType = "String")
    @ResponseBody
    @RequestMapping(value="/updateSort" , method = RequestMethod.POST)
    public LdbzResult updateSort(String ids) {
    	return adminCategoryService.updateSort(ids);
    }
    
}
