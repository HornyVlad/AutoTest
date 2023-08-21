package TestP7.authorization;

import TestP7.files.FilesPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.base.BaseActions;

public class AuthorizationPage extends BaseActions {
    public AuthorizationPage(WebDriver driver){
        super(driver);
    }
    private final String loginField = "//*[@id=\"login\"]";
    private final String passField = "//*[@id=\"pwd\"]";
    private final String enterBtn = "//*[@id=\"loginButton\"]";

    //Ввод логина на странице авторизации
    @Step("Ввод логина на странице авторизации")
    public AuthorizationPage enterLogin(String login){
        waitElementVisible(loginField).click();
        fillField(loginField, login);
        return this;

    }
    //Ввод пароля на странице авторизации
    @Step("Ввод пароля на странице авторизации")
    public AuthorizationPage enterPassword(String password){
        waitElementVisible(passField).click();
        fillField(passField, password);
        return this;
    }
    //Нажатие кнопки для входа
    @Step("Нажатие кнопки войти")
    public FilesPage enterButton(){
        waitElementVisible(enterBtn).click();
        return new FilesPage(driver);
    }

    //Весь процесс авторизации
    public FilesPage authorization(String login, String password){
        enterLogin(login);
        enterPassword(password);
        return enterButton();
    }
}
