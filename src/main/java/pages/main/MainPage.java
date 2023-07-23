package pages.main;

import common.Server;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class MainPage extends BasePage {

    private static final String URL = "/";

    private final String contentBody = "//div[@class='content-holder']";
    private final String sideBar = "//div[@class='layout__sider']";
    private final String lbWorkspace = "//*[@label='Рабочий стол']";

    //sideBar
    private final String btnSideBarReports = "//div[text()='Отчеты']";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainPage open() {
        driver.get(Server.getServerURL() + URL);
        return this;
    }

    @Step(value = "Проверка главной страницы")
    public MainPage checkElements() {
        waitElementVisible(contentBody + lbWorkspace);
        waitElementVisible(sideBar);
        return this;
    }
}
