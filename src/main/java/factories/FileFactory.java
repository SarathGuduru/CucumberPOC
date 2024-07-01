package factories;

import dataproviders.ConfigFileReader;

public class FileFactory {
    private static FileFactory fileFactory = new FileFactory();
    private static ConfigFileReader configFileReader;

    private FileFactory(){
    }

    public static FileFactory getInstance(){
        return fileFactory;
    }

    public ConfigFileReader getConfigReader() {
        return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
    }
}
