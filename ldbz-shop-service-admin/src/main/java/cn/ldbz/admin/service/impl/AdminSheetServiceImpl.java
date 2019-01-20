package cn.ldbz.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.admin.service.AdminSheetService;
import cn.ldbz.constant.Const;
import cn.ldbz.item.service.SheetService;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSheet;
import cn.ldbz.pojo.LdbzSheetAssign;

@Component
@Service(version = Const.LDBZ_SHOP_ADMIN_VERSION)
public class AdminSheetServiceImpl implements AdminSheetService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminSheetServiceImpl.class);
	
	@Reference(version = Const.LDBZ_SHOP_SHEET_VERSION, timeout=30000)
    private SheetService sheetService;

	@Override
	public LdbzResult selectByKey(long id) {
		logger.debug("selectByKey id : {}" , id);
		return sheetService.selectByKey(id);
	}

	@Override
	public LdbzResult getSheetList(LdbzSheet entity) {
		logger.debug("getSheetList entity : {}" , entity);
		return sheetService.getSheetList(entity);
	}

	@Override
	public LdbzResult getSheetPage(LdbzSheet entity, int page, int limit) {
		logger.debug("getSheetPage entity : {} , page:{} , limit:{}" , entity , page , limit);
		return sheetService.getSheetPage(entity, page, limit);
	}

	@Override
	public LdbzResult updateByKey(LdbzSheet entity) {
		logger.debug("updateByKey entity : {}" , entity);
		return sheetService.updateByKey(entity);
	}

	@Override
	public LdbzResult getSheetAssignList(long sheetId) {
		logger.debug("getSheetAssignList sheetId : {}" , sheetId);
		return sheetService.getSheetAssignList(sheetId);
	}

	@Override
	public LdbzResult deleteAssign(String ids) {
		logger.debug("deleteAssign ids : {}" , ids);
		return sheetService.deleteAssign(ids);
	}

	@Override
	public LdbzResult addAssign(LdbzSheetAssign entity) {
		logger.debug("addAssign entity : {}" , entity);
		return sheetService.addAssign(entity);
	}

	@Override
	public LdbzResult countSheet() {
		return sheetService.countSheet(null);
	}
	
}
