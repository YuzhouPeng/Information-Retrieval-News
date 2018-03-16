

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FBISDocumentParser {
    public ArrayList<DocumentCollection> readfile(ArrayList<String> filePaths){
        ArrayList<DocumentCollection> DocAarray = new ArrayList<>();
        try{
            Iterator<String> iterator = filePaths.iterator();
            while (iterator.hasNext()){
                File file = new File(iterator.next());
                Document doc = Jsoup.parse(file,"ISO-8859-1");
                Elements elements = doc.getElementsByTag("DOC");
                for(Element el: elements){
                    DocumentCollection doc1 = new DocumentCollection();
                    Elements DOCID = el.getElementsByTag("DOCNO");
                    Elements DocumentRelevantInfo = el.getElementsByTag("DATE1");
                    Elements Title = el.getElementsByTag("TI");
                    Elements Text = el.getElementsByTag("TEXT");

                    doc1.DocId = DOCID.text();
                    doc1.DocumentRelevantInfo = DocumentRelevantInfo.text();
                    doc1.Title = Title.text();
                    doc1.Content = Text.text();
                    DocAarray.add(doc1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return DocAarray;
    }

}
