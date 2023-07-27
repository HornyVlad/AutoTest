package P7.docs;

import P7.base.BasePageP7;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateDocP7 extends BasePageP7 {
    private final By firstCreateBtn = By.xpath("//*[@id=\"menuCreateNewButton\"]/span[2]");
    private final By secondCreateBtn = By.xpath("//*[@id=\"createDocument\"]");
    private final By confirmCreateBtn = By.xpath("//*[@id=\"filesMainContent\"]/li[1]/div[9]/div[1]");
    public CreateDocP7(WebDriver driver){
        super(driver);
    }

    //Создаём новый документ
    public CreateDocP7 createDoc(){
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(firstCreateBtn));
        WebElement firstBtnCreate = driver.findElement(firstCreateBtn);
        waitElement(firstBtnCreate).click();
        WebElement secondBtnCreate = driver.findElement(secondCreateBtn);
        waitElement(secondBtnCreate).click();
        WebElement confirmBtnCreate = driver.findElement(confirmCreateBtn);
        waitElement(confirmBtnCreate).click();
        return this;
    }
}
