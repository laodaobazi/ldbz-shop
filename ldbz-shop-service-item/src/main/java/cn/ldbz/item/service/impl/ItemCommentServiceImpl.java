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
import cn.ldbz.item.service.ItemCommentService;
import cn.ldbz.mapper.LdbzItemCommentMapper;
import cn.ldbz.pojo.LdbzItemComment;
import cn.ldbz.pojo.LdbzResult;

@Component
@Service(version = Const.LDBZ_SHOP_ITEM_VERSION)
public class ItemCommentServiceImpl implements ItemCommentService {

    private static final Logger logger = LoggerFactory.getLogger(ItemCommentServiceImpl.class);

    @Autowired
    private LdbzItemCommentMapper mapper ;

    @Override
    public LdbzResult countItemComment(LdbzItemComment entity) {
    	long total = mapper.countByEntity(entity);
		return LdbzResult.ok(total);
    }
    
	@Override
	public LdbzResult getItemCommentPage(LdbzItemComment entity, int pn, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		long total = mapper.countByEntity(entity);
		map.put("total", total) ;
		logger.debug("getItemCommentPage count : {} " , total);
		if(total>0 && pn>0) {
			int start = (pn-1)*limit ;
			List<LdbzItemComment> ret = mapper.selectByEntity(entity, start, limit);
			map.put("list", ret) ;
		}
		return LdbzResult.build(200, "", map);
	}
	
}
