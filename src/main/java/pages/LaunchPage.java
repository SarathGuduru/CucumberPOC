package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class LaunchPage extends  BasePage{

    WebDriver driver;
    By signIn_link = By.xpath("//a[@class='login' and text()='Sign in']");

    public LaunchPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void clickSignInLink(){
        if (isElementPresent(signIn_link)){
            log.info("Page Title :::: " + getPageTitle());
            driver.findElement(signIn_link).click();
        }
    }

}
