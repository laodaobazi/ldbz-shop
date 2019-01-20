package cn.ldbz.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.admin.service.AdminCategoryService;
import cn.ldbz.constant.Const;
import cn.ldbz.item.service.CategoryService;
import cn.ldbz.pojo.LdbzCategory;
import cn.ldbz.pojo.LdbzResult;

@Component
@Service(version = Const.LDBZ_SHOP_ADMIN_VERSION)
public class AdminCategoryServiceImpl implements AdminCategoryService{

	private static final Logger logger = LoggerFactory.getLogger(AdminCategoryServiceImpl.class);

    @Reference(version = Const.LDBZ_SHOP_ITEM_VERSION, timeout=30000)
    private CategoryService categoryService;

    @Override
    public LdbzResult selectByKey(long id) {
    	logger.debug("selectByKey id : {}" , id);
		return categoryService.selectByKey(id);
    }
    
	@Override
	public LdbzResult editCategory(long id) {
    	logger.debug("editCategory id : {}" , id);
		return categoryService.selectByKey(id);
	}

	@Override
	public LdbzResult getCategoryList(LdbzCategory entity) {
    	logger.debug("getCategoryList  : {}" , entity);
		return categoryService.getCategoryList(entity);
	}

	@Override
	public LdbzResult getCategoryPage(LdbzCategory entity, int page, int limit) {
    	logger.debug("getCategoryPage entity : {} , page:{} , limit:{}" , entity , page , limit);
		return categoryService.getCategoryPage(entity, page, limit);
	}

	@Override
	public LdbzResult deleteByKey(String id) {
    	logger.debug("deleteByKey  id : {}" , id);
		return categoryService.deleteByKey(id);
	}

	@Override
	public LdbzResult insertByEntity(LdbzCategory entity) {
    	logger.debug("insertByEntity  entity : {}" , entity);
		return categoryService.insertByEntity(entity);
	}

	@Override
	public LdbzResult updateByKey(LdbzCategory entity) {
    	logger.debug("updateByKey  entity : {}" , entity);
		return categoryService.updateByKey(entity);
	}

	@Override
	public LdbzResult getCategoryTree(long fid) {
    	logger.debug("getCategoryTree  fid : {}" , fid);
		return categoryService.getCategoryTree(fid);
	}

	@Override
	public LdbzResult updateSort(String ids) {
    	logger.debug("updateSort  ids : {}" , ids);
		return categoryService.updateSort(ids);
	}

	@Override
	public LdbzResult countCategory(LdbzCategory entity) {
    	logger.debug("countCategory  entity : {}" , entity);
		return categoryService.countCategory(entity);
	}
    
}
