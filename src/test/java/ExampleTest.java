import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class ExampleTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriver driver;

        File f = new File("sample.txt");
        FileInputStream fis = new FileInputStream(f);
        int c = 0;
        while ((c = fis.read()) != -1) {
            System.out.print((char) c);
        }



//        ConfigFileReader c = new ConfigFileReader();
//        BrowserManager browserManager = new BrowserManager();
//        System.out.println(c.getImplicitlyWait());
//        driver = browserManager.getDriver();
//        driver.get(c.getApplicationUrl());
//        System.out.println(driver.getTitle());
//        Thread.sleep(2000);
//        driver.close();

    }

}
