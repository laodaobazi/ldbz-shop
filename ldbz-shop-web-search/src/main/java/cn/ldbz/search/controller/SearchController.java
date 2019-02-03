package cn.ldbz.search.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import cn.ldbz.constant.Const;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSolrItem;
import cn.ldbz.search.service.SearchService;


@Controller
@RequestMapping("/search")
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
    @Reference(version = Const.LDBZ_SHOP_SEARCH_VERSION , timeout=30000)
    private SearchService searchService;

    //图片的URL路径
    @Value("${nginxImage.url.key}")
    private String INDEX_NGINX_IMAGE_URL;
    //商品的URL路径
    @Value("${item.url.key}")
    private String NGINX_ITEM_URL;

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
				case "nginxImage.url.key" : 
					INDEX_NGINX_IMAGE_URL = change.getNewValue();
				case "item.url.key" : 
					NGINX_ITEM_URL = change.getNewValue();
			}
		}
	}

    @RequestMapping
    public String search(Model model) {
    	model.addAttribute("itemUrl", NGINX_ITEM_URL);
    	model.addAttribute("nginxImage", INDEX_NGINX_IMAGE_URL);
        return "search";
    }
    
    @ResponseBody
    @RequestMapping(value="/getItemPage" , method=RequestMethod.POST)
    public LdbzResult getItemPage(LdbzSolrItem entity , 
    		@RequestParam(defaultValue = "1")int pn , 
    		@RequestParam(defaultValue = "10")int limit) {
    	return searchService.getItemPage(entity, pn, limit);
    }

}