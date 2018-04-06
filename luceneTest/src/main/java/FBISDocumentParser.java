

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FBISDocumentParser {
    public void readfile(ArrayList<String> filePaths, IndexWriter writer){
        try{
            int sum=0;
            Iterator<String> iterator = filePaths.iterator();
            
            while (iterator.hasNext()){
                ArrayList<DocumentCollection> DocAarray = new ArrayList<>();
                int counter = 0;
                File file = new File(iterator.next());
                Document doc = Jsoup.parse(file,"ISO-8859-1");
                Elements elements = doc.getElementsByTag("DOC");
                for(Element el: elements){
                    counter++;
                    DocumentCollection doc1 = new DocumentCollection();
                    Elements DOCID = el.getElementsByTag("DOCNO");
//                    Elements DocumentRelevantInfo = el.getElementsByTag("DATE1");
                    Elements Title = el.getElementsByTag("TI");
                    Elements Text = el.getElementsByTag("TEXT");

                    doc1.DocId = DOCID.text();
//                    doc1.DocumentRelevantInfo = DocumentRelevantInfo.text();
//                    doc1.DocumentRelevantInfo = "";
                    doc1.Title = Title.text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc1.Content = Text.text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    DocAarray.add(doc1);
                }
                IndexIterator indexIterator = new IndexIterator();
                indexIterator.IteratorIndexAdder(DocAarray,writer);
                sum+=counter;
            }
            System.out.println("Total processed FBIS doc number: "+ sum);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ;
    }

}
