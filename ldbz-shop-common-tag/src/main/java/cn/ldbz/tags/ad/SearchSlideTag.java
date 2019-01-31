package cn.ldbz.tags.ad;

import java.util.List;

import org.beetl.core.GeneralVarTagBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.advertisement.service.SearchSlideAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzSearchSlideAd;
import cn.ldbz.tags.annotation.BeetlTagName;

@Service
@Scope("prototype")
@BeetlTagName("search_slide_tag")
public class SearchSlideTag extends GeneralVarTagBinding {

    private static Logger logger = LoggerFactory.getLogger(SearchSlideTag.class);
	
	@Reference(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
	private SearchSlideAdService searchSlideAdService ;

	@Override
	public void render() {
		logger.debug("获取检索页轮播广告");
		List<LdbzSearchSlideAd> list = searchSlideAdService.getSearchSlideAdByRedis();
		for (LdbzSearchSlideAd c : list){
			this.binds(c);
			this.doBodyRender();
        }
	}

}
