package factories;

import dataproviders.ConfigFileReader;
import enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private WebDriver driver;
    private static BrowserType browserType;
    ConfigFileReader configReader;

    public DriverFactory() {
        this.configReader = new ConfigFileReader();
    }

    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        browserType = configReader.getBrowserType();
        var downloadDir = Paths.get("target").toAbsolutePath().toString();
        switch (browserType) {
            case CHROME:
                DesiredCapabilities dc=new DesiredCapabilities();
                dc.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", downloadDir);
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("start-maximized","incognito");
                options.merge(dc);
                driver = WebDriverManager.chromedriver().capabilities(options).create();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                driver.manage().window().maximize();
                break;
            case FIREFOX:
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case SAFARI:
                driver = WebDriverManager.safaridriver().create();;
                break;
        }
        return driver;
    }

}
