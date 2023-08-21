package common;

import constant.Constant;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class CommonActions {

    public static WebDriver createDriver() {
        WebDriver driver = null;

        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("Headless");
        options.addArguments("window-size=1920,1080");
        options.addArguments("--test-type");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        return driver;
    }
}
