package cn.ldbz.search.mapper;

import cn.ldbz.pojo.SolrItem;

import java.util.List;

/**
 * Solr操作Mapper
 *
 */

public interface SearchMapper {

    List<SolrItem> getSolrItemList();

    SolrItem getSolrItemByItemId(long itemid);

}
