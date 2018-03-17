
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

}
