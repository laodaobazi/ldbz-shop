package cn.ldbz.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import cn.ldbz.constant.Const;
import cn.ldbz.mapper.LdbzSolrItemMapper;
import cn.ldbz.pojo.LdbzResult;
import cn.ldbz.pojo.LdbzSolrItem;
import cn.ldbz.search.service.SearchService;

@Component
@Service(version = Const.LDBZ_SHOP_SEARCH_VERSION)
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private LdbzSolrItemMapper mapper ;
    
    private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

	@Override
	public LdbzResult getItemPage(LdbzSolrItem entity, int pn, int limit) {
		//{"id":148630831972868,"title":"无线蓝牙耳机 迷你超小运动Air双耳pods入耳式 白色","code":1546693041205,"price":36.00,"oldPrice":48.85,"image":"/uploadfiles/item_image/2019/01/05/1546692944934.jpg","category":54,"categoryName":"蓝牙耳机","created":"2018-12-31T23:59:59Z"}
		String key = String.format("title:%s AND category:%s" , 
				StringUtils.isNotEmpty(entity.getTitle())?entity.getTitle():"*" , 
						entity.getCategory()!=0?entity.getCategory():"*") ;
		if(entity.getPriceFrom()!=null && entity.getPriceTo()!=null 
				&& entity.getPriceTo()>=entity.getPriceFrom()) {
			key += " AND price:[" + entity.getPriceFrom() + " TO " + entity.getPriceTo() + "]" ;
		}
		
		SolrQuery query = new SolrQuery();
		query.set("collection", "items");
		query.setQuery(key);
		
        //设置分页
        query.setStart((pn - 1) * limit);
        query.setRows(limit);
        
        //自定义排序列
        if(StringUtils.isNotEmpty(entity.getField()) && StringUtils.isNotEmpty(entity.getOrder())) {
        	query.addSort(entity.getField(), "desc".equals(entity.getOrder())?SolrQuery.ORDER.desc:SolrQuery.ORDER.asc);
        }

        Map<String,Object> map = new HashMap<String,Object>();
        long total = 0 ;
        try {
			QueryResponse response = solrClient.query(query);
			SolrDocumentList results = response.getResults();
			total = results.getNumFound() ;
	        
			List<LdbzSolrItem> solrItems = new ArrayList<LdbzSolrItem>();
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			for (SolrDocument result : results) {
				LdbzSolrItem item = new LdbzSolrItem();
				
				item.setId(Long.valueOf(result.get("id").toString()));
				item.setCode(Long.valueOf(result.get("code").toString()));
				item.setTitle(result.get("title").toString());
				item.setImage(result.get("image").toString());
				item.setCategoryName(result.get("categoryName").toString());
				item.setPrice(Float.valueOf(result.get("price").toString()));
				item.setOldPrice(Float.valueOf(result.get("oldPrice").toString()));
				
				solrItems.add(item);
			}
			map.put("list", solrItems) ;
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
        
        map.put("total", total) ;
		return LdbzResult.ok(map);
	}

	@Override
	public LdbzSolrItem selectByCode(long code) {
		return mapper.selectByCode(code);
	}

	@Override
	public LdbzResult syncItemToSolrByCode(long code) {
		LdbzSolrItem item = mapper.selectByCode(code);
		
		try {
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", item.getId());
			document.addField("code", item.getCode());
			document.addField("created", item.getCreated());
			document.addField("title", item.getTitle());
			document.addField("detail", item.getDetail());
			document.addField("price", item.getPrice());
			document.addField("oldPrice", item.getOldPrice());
			document.addField("image", item.getImage());
			document.addField("category", item.getCategory());
			document.addField("categoryName", item.getCategoryName());
			
			solrClient.add("items" , document);
			solrClient.commit("items");
			
			return LdbzResult.ok();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
		return LdbzResult.build(500, "Create Index Error");
	}
	
	
    
}
