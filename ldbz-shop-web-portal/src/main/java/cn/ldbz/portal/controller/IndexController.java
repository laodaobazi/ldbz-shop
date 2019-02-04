package cn.ldbz.portal.controller;

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

import cn.ldbz.constant.Const;
import cn.ldbz.portal.service.IndexService;
import cn.ldbz.redis.service.JedisClient;

@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Reference(version = Const.LDBZ_SHOP_PORTAL_VERSION, timeout=30000)
    private IndexService indexService;
    
    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    //图片的URL路径
    @Value("${redisKey.nginxImage.url.key}")
    private String INDEX_NGINX_IMAGE_URL;
    //商品的URL路径
    @Value("${redisKey.item.url.key}")
    private String NGINX_ITEM_URL;
    //检索访问的URL路径
    @Value("${search.web.url}")
    private String SEARCH_WEB_URL;

    /**
     * 监听配置项是否有修改
     */
    @ApolloConfigChangeListener
	public void onChange(ConfigChangeEvent changeEvent) {
		for (String key : changeEvent.changedKeys()) {
			ConfigChange change = changeEvent.getChange(key);
			logger.debug("Found change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
					change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType());
			switch(key) {
				case "redisKey.nginxImage.url.key" : 
					INDEX_NGINX_IMAGE_URL = change.getNewValue();
				case "redisKey.item.url.key" : 
					NGINX_ITEM_URL = change.getNewValue();
				case "search.web.url" : 
					SEARCH_WEB_URL = change.getNewValue();
			}
		}
	}

	@RequestMapping("/index")
    public String index(Model model) {
    	
    	model.addAttribute("itemUrl", NGINX_ITEM_URL);
    	model.addAttribute("searchUrl", SEARCH_WEB_URL);
    	model.addAttribute("nginxImage", INDEX_NGINX_IMAGE_URL);
    	
    	return "index";
    }
    
}
