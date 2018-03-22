import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.search.Query;

import org.apache.lucene.queryparser.classic.QueryParser;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class ResultGenerator {
    private org.apache.lucene.analysis.Analyzer analyzer = new StandardAnalyzer();
    private IndexSearcher searcher;

    private static final int NUM_TOP_HITS = 1000;

    public void generateResult() {
        try {
            Path resultsFilePath = Configuration.RESULTS_PATH;
            String[] fields = {"Content"};
            DirectoryReader reader = DirectoryReader.open(FSDirectory.open(Configuration.INDEX_PATH));

            searcher = new IndexSearcher(reader);
            searcher.setSimilarity(new BM25Similarity());

            TopicParser topicParser = new TopicParser();
            ArrayList<DocumentQuery> documentQueries = topicParser.readQueries(Configuration.QUERIES_FILE_PATH);

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultsFilePath.toFile()));

            for (DocumentQuery documentQuery: documentQueries) {
                QueryParser parser = new MultiFieldQueryParser(fields, analyzer);
                Query query = parser.parse(documentQuery.Title+" "+documentQuery.description);
                ScoreDoc[] hits = searcher.search(query, NUM_TOP_HITS).scoreDocs;

                for (ScoreDoc hit: hits) {
                    Document hitDoc = searcher.doc(hit.doc);
                    String line = String.format("%s 0 %s 1\n",
                            documentQuery.queryId, hitDoc.get("DocId"));
                    bufferedWriter.write(line);
                }
            }

            bufferedWriter.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
