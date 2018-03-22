import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class LATIMESDocumentParser {
    public void readfile(ArrayList<String> filePaths, IndexWriter writer){
    //public ArrayList<DocumentCollection> readfile(){
        try{
            int sum = 0;
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
                    Elements DocumentRelevantInfo = el.getElementsByTag("DATE");
                    Elements Title = el.getElementsByTag("HEADLINE");
                    Elements Text = el.getElementsByTag("TEXT");

                    doc1.DocId = DOCID.text();
                    doc1.DocumentRelevantInfo = DocumentRelevantInfo.text();
                    doc1.Title = Title.text();
                    doc1.Content = Text.text();
                    DocAarray.add(doc1);
                }

                Iterator<DocumentCollection> iter = DocAarray.iterator();
                while(iter.hasNext()) {
                    DocumentCollection o = iter.next();
                    org.apache.lucene.document.Document doc1 = new org.apache.lucene.document.Document();
                    TextField docidfield = new TextField("DocId", o.DocId, Field.Store.YES);
                    TextField titlefield = new TextField("Title", o.Title, Field.Store.NO);
                    TextField docrelevantfield = new TextField("DocumentRelevantInfo", o.DocumentRelevantInfo, Field.Store.NO);
                    TextField contentfield = new TextField("Content", o.Content, Field.Store.NO);
                    doc1.add(docidfield);
                    doc1.add(titlefield);
                    doc1.add(docrelevantfield);
                    doc1.add(contentfield);
                    writer.addDocument(doc1);
                }
                sum+=counter;
            }
            System.out.println("Total processed LATIMES doc number: "+ sum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ;
    }
}
