package cn.ldbz.tags.ad;

import java.util.List;

import org.beetl.core.GeneralVarTagBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.advertisement.service.SearchLeftAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzSearchLeftAd;
import cn.ldbz.tags.annotation.BeetlTagName;

@Service
@Scope("prototype")
@BeetlTagName("search_left_tag")
public class SearchLeftTag extends GeneralVarTagBinding {

    private static Logger logger = LoggerFactory.getLogger(SearchLeftTag.class);
	
	@Reference(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
	private SearchLeftAdService searchLeftAdService ;

	@Override
	public void render() {
		logger.debug("获取检索页左侧广告");
		List<LdbzSearchLeftAd> list = searchLeftAdService.getSearchLeftAdByRedis();
		for (LdbzSearchLeftAd c : list){
			this.binds(c);
			this.doBodyRender();
        }
	}

}
