package cn.ldbz.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.admin.service.AdminItemService;
import cn.ldbz.constant.Const;
import cn.ldbz.item.service.ItemService;
import cn.ldbz.pojo.LdbzItem;
import cn.ldbz.pojo.LdbzResult;

@Component
@Service(version = Const.LDBZ_SHOP_ADMIN_VERSION)
public class AdminItemServiceImpl implements AdminItemService {

	private static final Logger logger = LoggerFactory.getLogger(AdminItemServiceImpl.class);

	@Reference(version = Const.LDBZ_SHOP_ITEM_VERSION, timeout=30000)
    private ItemService itemService;

	@Override
	public LdbzResult selectByKey(long id) {
		logger.debug("selectByKey id : {}" , id);
		return itemService.selectByKey(id);
	}

	@Override
	public LdbzResult getItemList(LdbzItem entity) {
		logger.debug("getItemList entity : {}" , entity);
		return itemService.getItemList(entity);
	}

	@Override
	public LdbzResult getItemPage(LdbzItem entity, int page, int limit) {
		logger.debug("getItemPage entity : {} , page:{} , limit:{}" , entity , page , limit);
		return itemService.getItemPage(entity, page, limit);
	}

	@Override
	public LdbzResult deleteByKey(String id) {
		logger.debug("deleteByKey id : {}" , id);
		return itemService.deleteByKey(id);
	}

	@Override
	public LdbzResult insertByEntity(LdbzItem entity) {
		logger.debug("insertByEntity entity : {}" , entity);
		return itemService.insertByEntity(entity);
	}

	@Override
	public LdbzResult updateByKey(LdbzItem entity) {
		logger.debug("updateByKey entity : {}" , entity);
		return itemService.updateByKey(entity);
	}

	@Override
	public LdbzResult countItem() {
		return itemService.countItem(null);
	}
	
}
