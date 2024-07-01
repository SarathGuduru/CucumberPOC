package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class SignInPage extends BasePage{

    WebDriver driver;
    By emailId_edit = By.xpath("//input[@id='login_id']");
    By next_button = By.xpath("//button[@id='nextbtn']/span[text()='Next']");
    By password_edit = By.xpath("//input[@id='password']");
    By signIn_btn = By.xpath("//button[@id='nextbtn']/span[text()='Sign in']");


    public SignInPage (WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public Boolean isEmailExists(){

        if (driver.findElements(emailId_edit).size() >0){
            log.info("Email field exists");
            return true;
        }else
            return false;

    }

    public void enterEmail(String emailId){
        driver.findElement(emailId_edit).sendKeys(emailId);
    }

    public void clickNextBtn() throws InterruptedException {
        driver.findElement(next_button).click();
        Thread.sleep(1000);
    }

    public void enterPassword(){
        driver.findElement(password_edit).sendKeys("Greatpk12#");
    }

    public void clickSignInBtn(){
        driver.findElement(signIn_btn).click();
    }


}
