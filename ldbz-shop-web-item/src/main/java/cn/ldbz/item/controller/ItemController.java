package cn.ldbz.item.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import cn.ldbz.constant.Const;
import cn.ldbz.item.service.ItemService;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.redis.service.JedisClient;


/**
 * 商品查询 Controller
 */
@Controller
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    
    @Reference(version = Const.LDBZ_SHOP_REDIS_VERSION)
    private JedisClient jedisClient;

    //首页广告图片的URL路径
    @Value("${redisKey.nginxImage.url.key}")
    private String INDEX_NGINX_IMAGE_URL;

    @Reference(version = Const.LDBZ_SHOP_ITEM_VERSION , timeout=30000)
    private ItemService itemService;

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
			}
		}
	}

    @RequestMapping("/item/{code}")
    public String  getItemByItemId(@PathVariable("code") Long itemCode, Model model) {
    	
    	model.addAttribute("nginxImage", INDEX_NGINX_IMAGE_URL);
    	model.addAttribute("itemCode", itemCode);
    	
    	//获取商品
    	LdbzResult ret2 = itemService.selectByCode(itemCode);
    	model.addAttribute("item", ret2.getData());
    	
        return "item";
    }

}
