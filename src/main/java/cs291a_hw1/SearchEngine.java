package cs291a_hw1;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

public class SearchEngine {
	
	SolrClient client;
	
	public SearchEngine() {
		this.client = new HttpSolrClient.Builder("http://localhost:8983/solr/trec45").build();
	}
	
//	public static void main(String[] args) throws IOException, SolrServerException {
//        SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/trec45").build();
//
//        SolrQuery query = new SolrQuery();
//        
//        query.setQuery("title:feb");
////        query.addFilterQuery("cat:electronics","store:amazon.com"); this is the filter
//        query.setFields("title"); //change title to argument
//        query.setStart(0);
////        
////        query.set("title", "title"); this is setting the check list boxes
//        
//        
//        //TODO: highlight query matches in our result
//        QueryResponse response = client.query(query);
//        SolrDocumentList results = response.getResults();
//        for (int i = 0; i < results.size(); ++i) {
//            String result = results.get(i).toString();
//            
//        }
//        System.out.print(results.size());;
//    }
	
	public QueryResponse searchQuery(SolrClient client, String userquery, String fields, int start) throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery();
		query.setQuery(userquery);
		query.setFields(fields);
		query.setStart(start);
		return client.query(query);
	}
	

}
