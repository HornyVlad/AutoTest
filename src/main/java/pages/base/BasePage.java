package pages.base;

import org.openqa.selenium.WebDriver;

abstract public class BasePage extends BaseActions {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    abstract public Object open();
}