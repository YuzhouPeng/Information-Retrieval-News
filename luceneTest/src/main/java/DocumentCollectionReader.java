
import java.io.File;
import java.util.ArrayList;


public class DocumentCollectionReader {
    public ArrayList<String> readCollections(final File folder, int key){
        ArrayList<String> filePaths = new ArrayList<>();
        try{
            for (final File fileEntry : folder.listFiles()){
                if(fileEntry.isDirectory()){
                    readCollections(fileEntry, key);
                }else{
                    if (fileEntry.getName().startsWith("fb") && key==1)
                        filePaths.add(fileEntry.getAbsolutePath());
                    if (fileEntry.getName().startsWith("fr") && key==2)
                        filePaths.add(fileEntry.getAbsolutePath());
                    if (fileEntry.getName().startsWith("ft") && key==3)
                        filePaths.add(fileEntry.getAbsolutePath());
                    if (fileEntry.getName().startsWith("la") && key==4)
                        filePaths.add(fileEntry.getAbsolutePath());

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return filePaths;
    }
    public ArrayList<DocumentCollection> processdata(){
        ArrayList<DocumentCollection> TotalDocuments = new ArrayList<>();
        try{
            final File folder = new File(Configuration.Fb_File_Path);
            FBISDocumentParser fbisDocumentParser = new FBISDocumentParser();
            TotalDocuments.addAll(fbisDocumentParser.readfile(readCollections(folder,1)));
            //System.out.println(1);
            final File folder1 = new File(Configuration.Fr94_File_Path);
            FR94DocumentParser fr94DocumentParser = new FR94DocumentParser();
            TotalDocuments.addAll(fr94DocumentParser.readfile(readCollections(folder1,2)));
            final File folder2 = new File(Configuration.Ft_File_Path);
            FTDocumentParser ftDocumentParser = new FTDocumentParser();
            TotalDocuments.addAll(ftDocumentParser.readfile(readCollections(folder2,3)));
            final File folder3 = new File(Configuration.Latimes_File_Path);
            LATIMESDocumentParser latimesDocumentParser = new LATIMESDocumentParser();
            TotalDocuments.addAll(latimesDocumentParser.readfile(readCollections(folder3,4)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return TotalDocuments;
    }
}
