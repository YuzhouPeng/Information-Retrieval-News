import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.FSDirectory;
import java.util.ArrayList;


public class Searcher {
    // you can try different analyzer in here
    private org.apache.lucene.analysis.Analyzer analyzer = new StopAnalyzer();
    private IndexSearcher searcher;

    public void readIndex(int index){
        try{
            DirectoryReader reader =  DirectoryReader.open(FSDirectory.open(Configuration.INDEX_PATH));
            searcher = new IndexSearcher(reader);
            if (index == 1){
                searcher.setSimilarity(new BM25Similarity());
            }else if(index == 2){
                searcher.setSimilarity(new ClassicSimilarity());
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    public ArrayList<Integer> search(String queryStr, int num){
        String[] a = {"queryId","Title","description","Narrative"};
        QueryParser parser = new MultiFieldQueryParser(a,analyzer);
        ArrayList<Integer> ids = new ArrayList<>();
        try{

            Query query = parser.parse(queryStr);
            ScoreDoc[] hits = searcher.search(query, num).scoreDocs;
            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = searcher.doc(hits[i].doc);
                int idStr = Integer.parseInt(hitDoc.get("id").trim());
                ids.add(idStr);
            }
            //System.out.println(ids);

        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return ids;

    }
}
