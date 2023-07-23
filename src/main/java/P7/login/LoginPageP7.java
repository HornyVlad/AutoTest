package P7.login;

import P7.base.BasePageP7;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageP7 extends BasePageP7 {
    public LoginPageP7(WebDriver driver){
        super(driver);
    }
    private final By postWay = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/div/form/div/div[2]/div[1]/div[1]/button");
    private final By loginField = By.xpath("//*[@id=\"login\"]");
    private final By passField = By.xpath("//*[@id=\"pwd\"]");
    private final By enterBtn = By.xpath("//*[@id=\"loginButton\"]");

    public LoginPageP7 chooseLoginWay(){
        WebElement btnPost = driver.findElement(postWay);
        waitElement(btnPost).click();
        return this;
    }
    public LoginPageP7 enterLogin(){
        WebElement fieldLogin = driver.findElement(loginField);
        waitElement(fieldLogin).click();
        fieldLogin.sendKeys("vlad.kochergin.1996@mail.ru");
        return this;
    }
    public LoginPageP7 enterPassword(){
        WebElement fieldPass = driver.findElement(passField);
        waitElement(fieldPass).click();
        fieldPass.sendKeys("mnbvcxz2002");
        return this;
    }
    public LoginPageP7 enterButton(){
        WebElement btnEnter = driver.findElement(enterBtn);
        waitElement(btnEnter).click();
        return this;
    }
}
