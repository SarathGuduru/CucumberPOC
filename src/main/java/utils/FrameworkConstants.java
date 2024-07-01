package utils;

public class FrameworkConstants {

    private FrameworkConstants() {}

    private static final int EXPLICIT_WAIT = 20;
    private static final String RESOURCES_PATH = System.getProperty("user.dir")+"/src/test/resources";
    private static final String CONFIG_FILE = RESOURCES_PATH + "/config.properties";
    private static final String DATA_FILE = RESOURCES_PATH + "/excel/TestData.xlsx";
    private static final String EXTENT_REPORT_FOLDER_PATH = System.getProperty("user.dir")+"/extent-test-output/";

    public static String getResourcesPath(){
        return RESOURCES_PATH;
    }

    public static int getExplicitWait(){
        return EXPLICIT_WAIT;
    }

    public static String getConfigFile(){
        return CONFIG_FILE;
    }

    public static String getDataFile(){
        return DATA_FILE;
    }

    public static String getExtentReportFolderPath(){
        return EXTENT_REPORT_FOLDER_PATH;
    }

}
