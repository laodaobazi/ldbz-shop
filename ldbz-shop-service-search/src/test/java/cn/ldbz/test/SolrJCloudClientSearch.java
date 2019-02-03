package cn.ldbz.test;
import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class SolrJCloudClientSearch {

    public static void main(String[] args) throws SolrServerException, IOException {
        String zkHost = "127.0.0.1:2181";
        CloudSolrClient server = new CloudSolrClient(zkHost);
        //server = new CloudSolrClient(zkHost);
        server.setParser(new XMLResponseParser());
        SolrQuery parameters = new SolrQuery();
        parameters.set("q", "*:*");
        parameters.set("qt", "/select");        
        parameters.set("collection", "items");
        QueryResponse response = server.query(parameters);
        SolrDocumentList list = response.getResults();
        System.out.println(list.size());    
    }
}