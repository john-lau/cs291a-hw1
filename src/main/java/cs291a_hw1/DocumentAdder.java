package cs291a_hw1;

import static spark.Spark.*;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DocumentAdder {
//    public static void main(String[] args) {
//        get("/hello", (req, res) -> "Hello World");
//    }
	
	private static int flush = 0; //so we can periodially flush
	
	public static void main(String[] args) throws IOException, SolrServerException {
	    SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/trec45").build();
	    String file = "data/lines-trec45.txt";
	    addDocuments(client, file);
	    client.commit(); 
	  }

	private static void addDoc(SolrClient client, String docid, String title, String body) throws IOException, SolrServerException {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("docid", docid);
        doc.addField("title", title);
        doc.addField("body", body);
        flush++;
        client.add(doc);
        if(flush%100 == 0) client.commit();
    }
    
    private static void addDocuments(SolrClient client, String fileName) throws IOException {
    	Files.lines(Paths.get(fileName))
    		.forEach(line->{
    			String[] s = line.split("\\t", 3);
				try {
					addDoc(client, s[0], s[1], s[2]);
				} catch (IOException | SolrServerException e) {
					e.printStackTrace();
				}
			});
	}

}
