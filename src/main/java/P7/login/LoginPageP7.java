package P7.login;

import org.openqa.selenium.WebDriver;
import pages.base.BaseActions;

public class LoginPageP7 extends BaseActions {
    public LoginPageP7(WebDriver driver){
        super(driver);
    }
    private final String loginField = "//*[@id=\"login\"]";
    private final String passField = "//*[@id=\"pwd\"]";
    private final String enterBtn = "//*[@id=\"loginButton\"]";

    //Ввод логина на странице авторизации
    public LoginPageP7 enterLogin(){
        waitElementVisible(loginField).click();
        fillField(loginField, "vlad.kochergin.1996@mail.ru");
        return this;

    }
    //Ввод пароля на странице авторизации
    public LoginPageP7 enterPassword(){
        waitElementVisible(passField).click();
        fillField(passField, "mnbvcxz2002");
        return this;
    }
    //Нажатие кнопки для входа
    public LoginPageP7 enterButton(){
        waitElementVisible(enterBtn).click();
        return this;
    }
}
