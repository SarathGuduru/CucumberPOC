package stepdefinitions;

import context.GlobalContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.SignInPage;

import java.io.IOException;


public class SignInPageSteps {
    GlobalContext globalContext;
    SignInPage signInPage;

    public SignInPageSteps(GlobalContext globalContext){
        this.globalContext = globalContext;
        signInPage = globalContext.getPageManager().getSignInPagePage();

    }

    @Then("verify that SignIn page is displayed")
    public void verifySignInPage(){
        if(signInPage.isEmailExists()){
            System.out.println("Verified that user is on SignIn page");
            System.out.println("Name :::: "+ globalContext.getScenarioContext().getContext("name"));
        }
    }

    @And("I enter {string} and click on Next button")
    public void enterEmailAndClickNext(String emailId) throws InterruptedException {
        signInPage.enterEmail(emailId);
        signInPage.clickNextBtn();
    }

    @And("I enter password and click on Next button")
    public void enterPassword(){
        signInPage.enterPassword();
    }

    @Then("I click on SignIn button")
    public void clickSignInBtn() throws InterruptedException, IOException {
        signInPage.clickSignInBtn();
        Thread.sleep(3000);


    }




}
