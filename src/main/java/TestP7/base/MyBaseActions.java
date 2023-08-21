package TestP7.base;

import org.openqa.selenium.WebDriver;


public class MyBaseActions {
    protected WebDriver driver;
    public MyBaseActions(WebDriver driver)
    {
        this.driver = driver;
    }
    public void open(String url)
    {
        driver.get(url);
    }
}
