package cn.ldbz.tags.ad;

import java.util.List;

import org.beetl.core.GeneralVarTagBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.advertisement.service.IndexSlideAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzIndexSlideAd;
import cn.ldbz.tags.annotation.BeetlTagName;

@Service
@Scope("prototype")
@BeetlTagName("index_slide_tag")
public class IndexSlideTag extends GeneralVarTagBinding {

    private static Logger logger = LoggerFactory.getLogger(IndexSlideTag.class);
	
	@Reference(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
	private IndexSlideAdService indexSlideAdService ;

	@Override
	public void render() {
		logger.debug("获取首页轮播广告");
		List<LdbzIndexSlideAd> list = indexSlideAdService.getIndexSlideAdByRedis();
		for (LdbzIndexSlideAd c : list){
			this.binds(c);
			this.doBodyRender();
        }
	}

}
