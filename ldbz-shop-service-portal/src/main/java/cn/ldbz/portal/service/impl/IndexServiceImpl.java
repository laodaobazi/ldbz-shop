package cn.ldbz.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.advertisement.service.IndexRecommendAdService;
import cn.ldbz.advertisement.service.IndexSlideAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.item.service.CategoryService;
import cn.ldbz.item.service.SheetService;
import cn.ldbz.pojo.LdbzIndexRecommendAd;
import cn.ldbz.pojo.LdbzIndexSlideAd;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSheet;
import cn.ldbz.portal.service.IndexService;

@Component
@Service(version = Const.LDBZ_SHOP_PORTAL_VERSION)
public class IndexServiceImpl implements IndexService {

    private static Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);

    @Reference(version = Const.LDBZ_SHOP_ITEM_VERSION , timeout=30000)
    private CategoryService categoryService;
    
	@Reference(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
	private IndexSlideAdService indexSlideAdService ;
	
	@Reference(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
	private IndexRecommendAdService indexRecommendAdService ;

    @Reference(version = Const.LDBZ_SHOP_SHEET_VERSION, timeout=30000)
    private SheetService sheetService;

	@Override
	public LdbzResult getCategory() {
		logger.debug("根据 {} 获取获取分类" , Const.CATEGORY_TREE_ROOT);
		return categoryService.getCategoryTreeRedis(Const.CATEGORY_TREE_ROOT) ;
	}

	@Override
	public List<LdbzIndexSlideAd> getIndexSlideAd() {
		logger.debug("获取首页轮播广告");
		return indexSlideAdService.getIndexSlideAdByRedis();
	}

	@Override
	public List<LdbzIndexRecommendAd> getIndexRecommendAd() {
		logger.debug("获取首页推荐广告");
		return indexRecommendAdService.getIndexRecommendAdByRedis();
	}

	@Override
	public LdbzResult getSheetList() {
		logger.debug("获取所有有效板块");
		LdbzSheet sheetEntity = new LdbzSheet();
    	sheetEntity.setStatus(1);
    	return sheetService.getSheetList(sheetEntity);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Map> getSheetAssignList(long sheetId){
		logger.debug("根据板块ID {} 获取分配的商品" , sheetId);
		return sheetService.getSheetAssignListByRedis(sheetId);
	}

}
