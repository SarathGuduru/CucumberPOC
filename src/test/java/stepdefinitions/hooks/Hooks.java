package stepdefinitions.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import context.GlobalContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FrameworkConstants;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

@Slf4j
public class Hooks {

    GlobalContext globalContext;
    static WebDriver driver;
    static ExtentTest extentTest;
    static ExtentReports extentReports;
    WebDriverWait wait;

    public Hooks(GlobalContext globalContext) {
        this.globalContext = globalContext;
        driver = globalContext.getWebDriverManager().getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.getExplicitWait()));
        extentReports = new ExtentReports();
    }

    @Before
    public void extentReportBuilder(Scenario scenario) {
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(new File(FrameworkConstants.getExtentReportFolderPath() + "/ExtentReport.html"));
        extentReports.attachReporter(extentHtmlReporter);
        extentReports.flush();
        extentTest = extentReports.createTest(Feature.class, scenario.getName());
        extentReports.flush();
    }

    public static void logMessage(String logType, String message) {
        switch (logType.toUpperCase()) {
            case "INFO":
                extentTest.log(Status.INFO, message);
                extentReports.flush();
                break;
            case "FAIL":
                extentTest.log(Status.FAIL, message);
                extentReports.flush();
                break;
            case "PASS":
                extentTest.log(Status.PASS, message);
                extentReports.flush();
                break;
        }
    }

    public static void logWithScreenshot(String logType, String message) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("target/ext-reports/screenshots" + "_" + new Date().getTime() + ".png");
        FileUtils.copyFile(srcFile, destFile);

        switch (logType) {
            case "Info":
                log.info("Capture screenshot");
                extentTest.log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromPath(destFile.getAbsolutePath()).build());
                extentReports.flush();
                break;
            case "Fail":
                extentTest.log(Status.FAIL, message, MediaEntityBuilder.createScreenCaptureFromPath(destFile.getAbsolutePath()).build());
                extentReports.flush();
                break;
            case "Pass":
                extentTest.log(Status.PASS, message, MediaEntityBuilder.createScreenCaptureFromPath(destFile.getAbsolutePath()).build());
                extentReports.flush();
                break;
        }
    }

    @After(order = 0)
    public void quitDriver() throws InterruptedException {
        Thread.sleep(2000);
        log.info("Quitting driver ...");
        driver.quit();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] byteData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(byteData, "image/png", screenshotName);
        }
    }
}
