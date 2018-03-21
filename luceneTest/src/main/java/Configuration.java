import java.nio.file.*;

public class Configuration {
    private static final Path File_Path = Paths.get("files/");
    public static final int Length_limit = 50;
    public static final Path INDEX_PATH = File_Path.resolve("index/");
    public static final Path RESULTS_PATH = File_Path.resolve("results.txt");
    public static final String Fb_File_Path= "/Users/pengyuzhou/Desktop/Assignment Two/fbis";
    public static final String Fr94_File_Path= "/Users/pengyuzhou/Desktop/Assignment Two/fr94";
    public static final String Ft_File_Path= "/Users/pengyuzhou/Desktop/Assignment Two/ft";
    public static final String Latimes_File_Path= "/Users/pengyuzhou/Desktop/Assignment Two/latimes";
    public static final String QUERIES_FILE_PATH = "/Users/pengyuzhou/Desktop/CS7IS3-Assignment2-Topics";
    public static final Path Result_Path= Paths.get("./resultdata");


}
