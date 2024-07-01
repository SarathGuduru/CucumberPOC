package dataproviders;

import enums.BrowserType;
import utils.FrameworkConstants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath= FrameworkConstants.getConfigFile();


    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties not found at " + propertyFilePath);
        }
    }

    public String getEnvironment(){
        String driverPath = properties.getProperty("environment");
        if(driverPath!= null) return driverPath;
        else throw new RuntimeException("Environment not specified in the config.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the config.properties file.");
    }

    public BrowserType getBrowserType() {
        String browserName = properties.getProperty("browser");
        if(browserName == null || browserName.toLowerCase().equals("chrome")) return BrowserType.CHROME;
        else if(browserName.toLowerCase().equalsIgnoreCase("firefox")) return BrowserType.FIREFOX;
        else if(browserName.toLowerCase().equals("safari")) return BrowserType.SAFARI;
        else throw new RuntimeException("Browser Name not found in config.properties : " + browserName);
    }

    public String getApplicationUrl() {
        String url = null;
        String env = properties.getProperty("environment").toLowerCase();

        switch (env){
            case "uat":
                url = properties.getProperty("uat.url");
                break;
            case "qa":
                url = properties.getProperty("qa.url");
                break;
            case "perf":
                url = properties.getProperty("perf.url");
                break;
        }
        if(url != null) return url;
        else throw new RuntimeException("url not specified in the config.properties file.");
    }

}