package context;

import factories.DriverFactory;
import factories.FileFactory;
import factories.PageFactory;

public class GlobalContext {
    private ScenarioContext scenarioContext;
    private DriverFactory driverFactory;
    private PageFactory pageFactory;
    private FileFactory fileFactory;

    public GlobalContext(){
        driverFactory = new DriverFactory();
        pageFactory = new PageFactory(driverFactory.getDriver());
        scenarioContext = new ScenarioContext();
    }

    public DriverFactory getWebDriverManager() {
        return driverFactory;
    }

    public FileFactory getFileManager() {
        return FileFactory.getInstance();
    }

    public PageFactory getPageManager() {
        return pageFactory;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

}
