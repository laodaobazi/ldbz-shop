package cn.ldbz.search.controller;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import cn.ldbz.constant.Const;
import cn.ldbz.pojo.SearchResult;
import cn.ldbz.search.service.SearchService;


@Controller
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
    @Reference(version = Const.LDBZ_SHOP_SEARCH_VERSION)
    private SearchService searchService;

    //图片的URL路径
    @Value("${nginxImage.url.key}")
    private String INDEX_NGINX_IMAGE_URL;
    //商品的URL路径
    @Value("${item.url.key}")
    private String NGINX_ITEM_URL;

    @Value("${search_result_rows}")
    private Integer SEARCH_RESULT_ROWS;

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
				case "search_result_rows" : 
					SEARCH_RESULT_ROWS = Integer.valueOf(change.getNewValue());
			}
		}
	}

    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString,
                         @RequestParam(defaultValue = "1") Integer page, Model model) {
    	
    	model.addAttribute("itemUrl", NGINX_ITEM_URL);
    	model.addAttribute("nginxImage", INDEX_NGINX_IMAGE_URL);

//        if (queryString != null) {
//
//            String string = null;
//            try {
//                string = new String(queryString.getBytes("iso8859-1"), "utf-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//            SearchResult search  = searchService.search(string, page, rows);
//
//            // 异常测试
//            //int i = 1 / 0;
//
//            model.addAttribute("query", string);
//            model.addAttribute("totalPages", search.getPageCount());
//            model.addAttribute("itemList", search.getItemList());
//            model.addAttribute("page", search.getCurPage());
//
//        }

        return "search";
    }

    @RequestMapping("/search22")
    public String search22(@RequestParam("q") String queryString,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "0") Integer rows, Model model) throws Exception {
        if (rows == 0) {
            rows = SEARCH_RESULT_ROWS;
        }

        if (queryString != null) {

            String string = null;
            try {
                string = new String(queryString.getBytes("iso8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            SearchResult search  = searchService.search(queryString, page, rows);

            // 异常测试
            //int i = 1 / 0;

            model.addAttribute("query", string);
            model.addAttribute("totalPages", search.getPageCount());
            model.addAttribute("itemList", search.getItemList());
            model.addAttribute("page", search.getCurPage());
        }
        return "search";
    }
    
}
