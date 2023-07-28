package P7.docs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BaseActions;

import java.time.Duration;

import static constant.Constant.TimeOut.EXPLICIT_WAIT;

public class CreateDocP7 extends BaseActions {
    private final String first = "//*[@id=\"menuCreateNewButton\"]/span[2]";
    private final String second = "//*[@id=\"createDocument\"]";
    private final String third = "//*[@id=\"filesMainContent\"]/li[1]/div[9]/div[1]";
    public CreateDocP7(WebDriver driver){
        super(driver);
    }

    //Создаём новый документ
    public CreateDocP7 createDoc2(){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(first))));
        waitElementVisible(first).click();
        waitElementVisible(second).click();
        waitElementVisible(third).click();
        return this;
    }
}
