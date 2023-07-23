package pages.login;

import common.Server;
import io.qameta.allure.Step;
import models.UserModel;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import pages.main.MainPage;

public class LoginPage extends BasePage {

    public static final String URL = "/Security/Login";

    private final String username = "#login";
    private final String password = "#password";
    private final String btnLogin = "//*[@class='button' and @onclick]";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage open() {
        driver.get(Server.getServerURL() + URL);
        return this;
    }

    @Step(value = "Проверка элементов страницы авторизации")
    public LoginPage checkElements() {
        waitElementVisible(username);
        waitElementVisible(password);
        waitElementVisible(btnLogin);
        return this;
    }

    @Step(value = "Авторизация")
    public MainPage login(UserModel user) {
        fillField(username, user.username);
        fillField(password, user.password);
        click(btnLogin);
        return new MainPage(driver);
    }
}
