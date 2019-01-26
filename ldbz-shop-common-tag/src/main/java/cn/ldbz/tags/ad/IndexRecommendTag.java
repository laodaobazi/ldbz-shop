package cn.ldbz.tags.ad;

import java.util.List;

import org.beetl.core.GeneralVarTagBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.ldbz.advertisement.service.IndexRecommendAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzIndexRecommendAd;
import cn.ldbz.tags.annotation.BeetlTagName;

@Service
@Scope("prototype")
@BeetlTagName("index_recommend_tag")
public class IndexRecommendTag extends GeneralVarTagBinding {

    private static Logger logger = LoggerFactory.getLogger(IndexRecommendTag.class);
	
	@Reference(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
	private IndexRecommendAdService indexRecommendAdService ;

	@Override
	public void render() {
		Object site = this.getAttributeValue("site");
		logger.debug("获取首页推荐广告");
		List<LdbzIndexRecommendAd> list = indexRecommendAdService.getIndexRecommendAdByRedis();
		for (LdbzIndexRecommendAd entity : list){
			if(entity.getAdKey().equals(site)) {
				this.binds(entity);
				this.doBodyRender();
			} 
        }
	}
    
}