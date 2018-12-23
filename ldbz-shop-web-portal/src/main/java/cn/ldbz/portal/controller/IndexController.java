package cn.ldbz.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import cn.ldbz.advertisement.service.IndexSlideAdService;
import cn.ldbz.constant.Const;
import cn.ldbz.item.service.CategoryService;
import cn.ldbz.pojo.LdbzIndexSlideAd;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.portal.service.PortalContentService;
import cn.ldbz.redis.service.JedisClient;

@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Reference(version = Const.LDBZ_SHOP_PORTAL_VERSION, timeout=30000)
    private PortalContentService portalContentService;
    
    @Reference(version = Const.LDBZ_SHOP_ITEM_VERSION , timeout=30000)
    private CategoryService categoryService;
    
	@Reference(version = Const.LDBZ_SHOP_ADVERTISEMENT_VERSION)
	private IndexSlideAdService indexSlideAdService ;

    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    @Value("${redisKey.indexSlide.url.key}")
    private String INDEX_SLIDE_URL;
    
    /**
     * 监听配置项是否有修改
     */
    @ApolloConfigChangeListener
	public void onChange(ConfigChangeEvent changeEvent) {
		for (String key : changeEvent.changedKeys()) {
			ConfigChange change = changeEvent.getChange(key);
			logger.debug(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s",
					change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
			switch(key) {
			case "redisKey.indexSlide.url.key" : 
				INDEX_SLIDE_URL = change.getNewValue();
			}
		}
	}
    
    
    

    @RequestMapping("/index")
    public String index(Model model) {
    	//获取分类
    	LdbzResult ret = categoryService.getCategoryTreeRedis(Const.CATEGORY_TREE_ROOT) ;
    	model.addAttribute("categorys", ret.getData());
    	//获取首页轮播广告
    	model.addAttribute("indexSlideUrl", INDEX_SLIDE_URL);
    	List<LdbzIndexSlideAd> ret2 = indexSlideAdService.getIndexSlideAdByRedis();
    	model.addAttribute("indexSlides", ret2);
    	return "index";
    }
    
}
