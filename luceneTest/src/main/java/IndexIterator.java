import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

import java.util.ArrayList;
import java.util.Iterator;

public class IndexIterator {
    public void IteratorIndexAdder(ArrayList<DocumentCollection> DocAarray, IndexWriter writer){
        Iterator<DocumentCollection> iter = DocAarray.iterator();
        try{
            while(iter.hasNext()) {
                DocumentCollection o = iter.next();
                org.apache.lucene.document.Document doc1 = new org.apache.lucene.document.Document();
                TextField docidfield = new TextField("DocId", o.DocId, Field.Store.YES);
                TextField titlefield = new TextField("Title", o.Title, Field.Store.NO);
//                TextField docrelevantfield = new TextField("DocumentRelevantInfo", o.DocumentRelevantInfo, Field.Store.NO);
                TextField contentfield = new TextField("Content", o.Content, Field.Store.NO);
                doc1.add(docidfield);
                doc1.add(titlefield);
//                doc1.add(docrelevantfield);
                doc1.add(contentfield);
                writer.addDocument(doc1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
