package TestP7.files;

import TestP7.newDoc.NewDocumentPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BaseActions;

import java.time.Duration;

import static constant.Constant.TimeOut.EXPLICIT_WAIT;

public class FilesPage extends BaseActions {
    private final String createBtn = "//*[@id=\"menuCreateNewButton\"]/span[1]";
    private final String chooseDocumentCreate = "//*[@id=\"createDocument\"]";
    private final String confirmationCheckMark = "//*[@id=\"filesMainContent\"]/li[1]/div[9]/div[1]";
    public FilesPage(WebDriver driver){
        super(driver);
    }

    //Создаём новый документ
    @Step("Создание нового документа")
    public NewDocumentPage createDoc(){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(createBtn))));
        waitElementVisible(createBtn).click();
        waitElementVisible(chooseDocumentCreate).click();
        waitElementVisible(confirmationCheckMark).click();
        return new NewDocumentPage(driver);
    }
}
