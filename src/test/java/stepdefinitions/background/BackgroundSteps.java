package stepdefinitions.background;

import context.GlobalContext;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class BackgroundSteps {
    GlobalContext globalContext;
    WebDriver driver;

    public BackgroundSteps(GlobalContext globalContext){
        this.globalContext = globalContext;
        this.driver = globalContext.getWebDriverManager().getDriver();
    }

    @Given("I launch Zoho application")
    public void launchApplication(){
        log.info("Launching the application...");

        //wait.until(ExpectedConditions.titleContains("test"));
        driver.get(globalContext.getFileManager().getConfigReader().getApplicationUrl());
        log.info("Application launched successfully");
    }

}
