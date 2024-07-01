package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FrameworkConstants;

import java.time.Duration;

@Slf4j
public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(FrameworkConstants.getExplicitWait()));
    }

    public Boolean isElementPresent(By element) {
        return driver.findElement(element).isDisplayed();
    }

    public void clickElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
            element.click();
            log.info("Clicked on element");
        } catch (NoSuchElementException e) {
            log.error("Element not visible.Please verify !!!");
        }
    }

    public String getElementText(By locator) {
        String txt = null;
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
            String displayedText = driver.findElement(locator).getText();
            if (displayedText.isEmpty())
                txt = driver.findElement(locator).getAttribute("value");
            else
                return displayedText;

        } catch (NoSuchElementException e) {
            log.error("Element not found. Please verify !!!");
        }
        return txt;
    }

    public String getPageTitle() {
        String title = null;
        try {
            new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.getExplicitWait())).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            title = driver.getTitle();
        } catch (Exception e) {
            log.error("Unable to get page title. Please verify !!!");
        }
        return title;
    }

    public void dragAndDropByOffset(By locator, int x, int y) {
        try {
            Actions actions = new Actions(driver);
            WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
            actions.dragAndDropBy(element, x, y).perform();
        } catch (Exception e) {
            log.error("Unable to drag and drop" + e.getMessage());
        }
    }

    public void dismissPopup() {
        driver.switchTo().alert().dismiss();
    }

    public void acceptPopup() {
        driver.switchTo().alert().accept();
    }

    public void setAlertText(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    public void hoverOverElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
        } catch (NoSuchElementException e) {
            log.error("Element not visible: " + e.getMessage());
        }
    }

    public void scrollElementIntoView(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView();", element);
        } catch (NoSuchElementException e) {
            log.error("Element not visible: " + e.getMessage());
        }
    }

}
