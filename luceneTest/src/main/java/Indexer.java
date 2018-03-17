//import com.sun.xml.internal.bind.v2.TODO;
//import org.apache.lucene.analysis.core.StopAnalyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class Indexer {
//    void createIndex(int idx, int key){
//        try{
//            if(idx==1){
//                IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
//                config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
//                Directory dir = FSDirectory.open(Configuration.Index_Path);
//                IndexWriter writer = new IndexWriter(dir,config);
//                DocumentCollectionReader documentCollectionReader = new DocumentCollectionReader();
//                ArrayList<DocumentCollection> documentCollections = documentCollectionReader.readCollections();
//
//                Iterator<DocumentCollection> iter = documentCollections.iterator();
//                while(iter.hasNext()) {
//                    DocumentCollection o = iter.next();
//                    org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document();
//                    StringField docidfield = new StringField("docid", o.DocId, Field.Store.YES);
//                    TextField titlefield = new TextField("title", o.Title, Field.Store.YES);
//                    TextField docrelevantfield = new TextField("relevantinfo", o.DocumentRelevantInfo, Field.Store.YES);
//                    TextField contentfield = new TextField("content", o.Content, Field.Store.YES);
//                    doc.add(docidfield);
//                    doc.add(titlefield);
//                    doc.add(docrelevantfield);
//                    doc.add(contentfield);
//                    writer.addDocument(doc);
//                }
//                writer.close();
//            }else if(idx==2){
//                // If you want to try different model or method you can write at here
//                // TODO
//            }
//
//
//        }catch (NullPointerException e){
//            Logger.getGlobal().log(Level.SEVERE,"iteration failed");
//            e.printStackTrace();
//            System.exit(0);
//        }catch (Exception e){
//            Logger.getGlobal().log(Level.SEVERE,"build index failed");
//            e.printStackTrace();
//            System.exit(0);
//        }
//    }
//}
