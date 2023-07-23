package P7.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePageP7 {
    protected WebDriver driver;
    public BasePageP7(WebDriver driver)
    {
        this.driver = driver;
    }
    public void open(String url)
    {
        driver.get(url);
    }
    public WebElement waitElement(WebElement element)
    {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(element));
        return element;
    }
}
