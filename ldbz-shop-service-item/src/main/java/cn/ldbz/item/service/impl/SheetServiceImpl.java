package cn.ldbz.item.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.constant.Const;
import cn.ldbz.item.service.SheetService;
import cn.ldbz.mapper.LdbzSheetMapper;
import cn.ldbz.pojo.LdbzSheet;
import cn.ldbz.pojo.LdbzResult;

@Component
@Service(version = Const.LDBZ_SHOP_SHEET_VERSION)
public class SheetServiceImpl implements SheetService {

    private static final Logger logger = LoggerFactory.getLogger(SheetServiceImpl.class);

    @Autowired
    private LdbzSheetMapper mapper ;

	@Override
	public LdbzResult getSheetPage(LdbzSheet entity, int pn, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = mapper.countByEntity(entity);
		map.put("total", total) ;
		logger.debug("getSheetPage count : {} " , total);
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzSheet> ret = mapper.selectByEntity(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(0, "", map);
	}

	@Override
	public LdbzResult getSheetList(LdbzSheet entity) {
		logger.debug("getSheetList : {} " , entity);
		List<LdbzSheet> ret = mapper.selectByEntity(entity, 0, Integer.MAX_VALUE);
		return LdbzResult.build(0, "", ret);
	}
    
	@Override
	public LdbzResult selectByKey(Long itemId) {
		    logger.info("根据商品板块ID"+itemId+"查询商品板块！");
		    LdbzSheet item = mapper.selectByKey(itemId);
		    return LdbzResult.ok(item);
	}

	@Override
	public LdbzResult updateByKey(LdbzSheet entity) {
		logger.debug("updateByKey entity : {} " , entity);
		return LdbzResult.ok(mapper.updateByKey(entity));
	}

}