
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.util.ArrayList;


public class DocumentCollectionReader {
    ArrayList<String> filePaths = new ArrayList<>();
    public ArrayList<String> readCollections(final File folder){
        try{
            for (final File fileEntry : folder.listFiles()){
                if(fileEntry.isDirectory()){
                    readCollections(fileEntry);
                }else{
                    if (fileEntry.getName().startsWith("fb"))
                        filePaths.add(fileEntry.getAbsolutePath());
                    if (fileEntry.getName().startsWith("fr"))
                        filePaths.add(fileEntry.getAbsolutePath());
                    if (fileEntry.getName().startsWith("ft"))
                        filePaths.add(fileEntry.getAbsolutePath());
                    if (fileEntry.getName().startsWith("la"))
                        filePaths.add(fileEntry.getAbsolutePath());

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return filePaths;
    }
    public void addIndex(){
        try{
            IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            Directory dir = FSDirectory.open(Configuration.INDEX_PATH);
            IndexWriter writer = new IndexWriter(dir,config);

            final File fbisPath = new File(Configuration.Fb_File_Path);
            final File fr94Path = new File(Configuration.Fr94_File_Path);
            final File ftPath = new File(Configuration.Ft_File_Path);
            final File latimesPath = new File(Configuration.Latimes_File_Path);

            FBISDocumentParser fbisDocumentParser = new FBISDocumentParser();
            FR94DocumentParser fr94DocumentParser = new FR94DocumentParser();
            FTDocumentParser ftDocumentParser = new FTDocumentParser();
            LATIMESDocumentParser latimesDocumentParser = new LATIMESDocumentParser();

            DocumentCollectionReader do1 = new DocumentCollectionReader();
            DocumentCollectionReader do2 = new DocumentCollectionReader();
            DocumentCollectionReader do3 = new DocumentCollectionReader();
            DocumentCollectionReader do4 = new DocumentCollectionReader();

            fbisDocumentParser.readfile(do1.readCollections(fbisPath),writer);
            fr94DocumentParser.readfile(do2.readCollections(fr94Path),writer);
            ftDocumentParser.readfile(do3.readCollections(ftPath),writer);
            latimesDocumentParser.readfile(do4.readCollections(latimesPath),writer);

            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
