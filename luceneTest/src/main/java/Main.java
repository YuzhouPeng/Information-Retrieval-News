
import java.io.File;

public class Main{
    public static void main(String[] args){
//        Indexer indexer = new Indexer();
//        indexer.createIndex(1,1);
//        System.out.println(1);
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

        fbisDocumentParser.readfile(do1.readCollections(fbisPath));
        fr94DocumentParser.readfile(do2.readCollections(fr94Path));
        ftDocumentParser.readfile(do3.readCollections(ftPath));
        latimesDocumentParser.readfile(do4.readCollections(latimesPath));

    }
}
