package pathManager;


import java.io.File;

public class PathManager {

    private static String runFolderPath;
    private static ThreadLocal<String> testFolderPath = new ThreadLocal<>();

    // RUN LEVEL
    public static void setRunFolderPath(String path) {
        runFolderPath = path;
    }

    public static String getRunFolderPath() {
        return runFolderPath;
    }

    // TEST LEVEL
    public static void setTestFolderPath(String path) {
        testFolderPath.set(path);
    }

    public static String getTestFolderPath() {
        return testFolderPath.get();
    }

    public static void clearTestFolder() {
        testFolderPath.remove();
    }
}