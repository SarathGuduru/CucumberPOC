package factories;

import org.openqa.selenium.WebDriver;
import pages.LaunchPage;
import pages.SignInPage;

public class PageFactory {
    private WebDriver driver;
    private LaunchPage launchPage;
    private SignInPage signInPage;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public LaunchPage getLaunchPage(){
        return launchPage == null ? launchPage = new LaunchPage(driver) : launchPage;
    }

    public SignInPage getSignInPagePage(){
        return signInPage == null ? signInPage = new SignInPage(driver) : signInPage;
    }

}
