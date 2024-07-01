package stepdefinitions;

import context.GlobalContext;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import pages.LaunchPage;
import stepdefinitions.hooks.Hooks;

import java.io.IOException;

@Slf4j
public class LaunchPageSteps {
    GlobalContext globalContext;
    LaunchPage launchPage;
    Hooks hook;

    public LaunchPageSteps(GlobalContext globalContext) throws IOException {
        this.globalContext = globalContext;
        launchPage = globalContext.getPageManager().getLaunchPage();
        globalContext.getScenarioContext().setContext("name","Sachin");
    }

    @When("I click on SignIn link")
    public void launch() throws InterruptedException, IOException {

        log.info("Clicking SignIn link on Launch page");
        //Hooks.logWithScreenshot("Info", "This is test message");
        launchPage.clickSignInLink();

    }

}
