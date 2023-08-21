package TestP7.newDoc;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BaseActions;

import java.time.Duration;
import java.util.Set;

import static constant.Constant.TimeOut.EXPLICIT_WAIT;

public class NewDocumentPage extends BaseActions {
    protected final String saveBtn = "//*[@id=\"asc-gen584\"]";
    protected final String sizeFontBtn = "//*[@id=\"asc-gen436\"]/input";
    protected final String mainTabBtn = "//*[@id=\"toolbar\"]/div/div[1]/section/ul/li[2]";
    protected final String insertTabBtn = "//*[@id=\"toolbar\"]/div/div[1]/section/ul/li[3]";
    protected final String defaultAlignBtn = "//*[@id=\"id-toolbar-btn-align-left\"]";
    protected final String layoutTab = "//*[@id=\"toolbar\"]/div/div[1]/section/ul/li[4]";
    protected final String boldTypeFont = "//*[@id=\"id-toolbar-btn-bold\"]";
    protected final String italicTypeFont = "//*[@id=\"id-toolbar-btn-italic\"]";
    protected final String strikeoutTypeFont = "//*[@id=\"id-toolbar-btn-strikeout\"]";
    protected final String alignLeftType = "//*[@id=\"id-toolbar-btn-align-left\"]";
    protected final String alignRightType = "//*[@id=\"id-toolbar-btn-align-right\"]";
    protected final String alignWidthType = "//*[@id=\"id-toolbar-btn-align-just\"]";
    public NewDocumentPage(WebDriver driver){
        super(driver);
    }

    //У нас открылось новое окно и нужно на него переключиться
    @Step ("Переключение рабочего окна")
    public NewDocumentPage switchWindow(){
        String window1 = driver.getWindowHandle();
        Set <String> allWindows = driver.getWindowHandles();
        String window2 = null;
        for(String window: allWindows){
            if(!window.equals(window1)){
                window2 = window;
                break;
            }
        }
        driver.switchTo().window(window2);
        return this;
    }

    //В новом окне присутствуют 2 фрейма и нужно переключиться на необходимый
    @Step ("Переключение рабочего фрейма")
    public NewDocumentPage switchFrame(){
        WebElement iframe = driver.findElement(By.cssSelector("#wrap > iframe"));
        waitElementVisible("//*[@id=\"wrap\"]/iframe");
        driver.switchTo().frame(iframe);
        waitElementDom("//div[@class = 'asc-loadmask']");
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//div[@class = 'asc-loadmask']"))));
        return this;
    }

    //на случай если вдруг мы хотим проверить taskX отдельно
    public void start(String tab){
        waitElementVisible("//*[@id=\"id_viewer_overlay\"]");
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(tab))));
        waitElementVisible(tab);
        click(tab);
    }

    @Step("Сделать текст жирным")
    public NewDocumentPage textAndBoldFontParams(String text, int fontSize){
        return textAndFontParams(text, fontSize, boldTypeFont);
    }
    @Step("Сделать текст наклонным")
    public NewDocumentPage textAndItalicFontParams(String text, int fontSize){
        return textAndFontParams(text, fontSize, italicTypeFont);
    }
    @Step("Сделать текст зачёркнутым")
    public NewDocumentPage textAndStrikeoutFontParams(String text, int fontSize){
        return textAndFontParams(text, fontSize, strikeoutTypeFont);
    }
    private NewDocumentPage textAndFontParams(String text, int fontSize, String type){
        start(mainTabBtn);
        waitElementVisible(sizeFontBtn).click();
        fillField(sizeFontBtn, Integer.toString(fontSize));
        waitElementVisible(type).click();
        new Actions(driver).sendKeys(text + "\n").perform();
        click(type);
        waitElementVisible(saveBtn).click();
        return this;
    }

    private void gapCreate(String choiceTypeGap, String choiceParamsGap){
        String gapBtn = "//*[@id=\"id-toolbar-toolbar__icon btn-pagebreak1\"]/button[2]";

        waitElementVisible(gapBtn).click();
        //waitElementVisible(choiceTypeGap).click();
        new Actions(driver).moveToElement(driver.findElement(By.xpath(choiceTypeGap))).perform();
        waitElementVisible(choiceParamsGap);
        new Actions(driver).moveToElement(driver.findElement(By.xpath(choiceParamsGap))).click().perform();
    }
    private void gapCreate(String choiceTypeGap){
        String gapBtn = "//*[@id=\"id-toolbar-toolbar__icon btn-pagebreak1\"]/button[2]";

        waitElementVisible(gapBtn).click();
        waitElementVisible(choiceTypeGap).click();
    }
    private void columnsCreate(int columnsNum){
        String columnBtn = "//*[@id=\"tlbtn-columns\"]/button";
        String columnParamsBtn = "//*[@id=\"asc-gen325\"]";
        String inputNumCols = "//*[@id=\"custom-columns-spin-num\"]/input";
        String confirmNumColsBtn = "//button[@class = 'btn normal dlg-btn primary']";

        waitElementVisible(columnBtn).click();
        waitElementVisible(columnParamsBtn).click();
        waitElementVisible(inputNumCols).click();
        driver.findElement(By.xpath(inputNumCols)).sendKeys(Keys.DELETE);
        fillField(inputNumCols, Integer.toString(columnsNum));
        waitElementVisible(confirmNumColsBtn).click();
    }
    @Step("Разбить текст на столбцы")
    public NewDocumentPage splitIntoColumns(int columnsNum){
        //переключение на вкладку макет
        start(layoutTab);

        //Создание разрыва
         gapCreate("//*[@id=\"id-toolbar-toolbar__icon btn-pagebreak1\"]/ul/li[3]", "(//a[starts-with(text(), \"На текущей странице\")])[2]");

        //Делаем текст в 4 столбца
        columnsCreate(columnsNum);

        //Заполняем 4 столбца
        new Actions(driver).sendKeys("1 столбец").perform();
        for(int i = 1; i < columnsNum; i++){
            gapCreate("(//a[starts-with(text(), \"Вставить разрыв колонки\")])[2]");
            new Actions(driver).sendKeys(i + 1 + " столбец").perform();
        }

        //Создаём разрыв
        gapCreate("//*[@id=\"id-toolbar-toolbar__icon btn-pagebreak1\"]/ul/li[3]", "(//a[starts-with(text(), \"На текущей странице\")])[2]");

        //Меняем количество столбцов на 1
        columnsCreate(1);

        waitElementDom(saveBtn).click();
        return this;
    }


    @Step("Выровнять текст по левому краю")
    public NewDocumentPage textAlignmentLeft(String text){
        return textAlignment(text, alignLeftType);
    }

    @Step("Выровнять текст по правому краю")
    public NewDocumentPage textAlignmentRight(String text){
        return textAlignment(text, alignRightType);
    }

    @Step("Выровнять текст по ширине")
    public NewDocumentPage textAlignmentWidth(String text){
        return textAlignment(text, alignWidthType);
    }
    private NewDocumentPage textAlignment(String text, String alignType){
        start(mainTabBtn);

        waitElementVisible(alignType).click();
        new Actions(driver).sendKeys(text +"\n").perform();
        waitElementVisible(defaultAlignBtn).click();
        waitElementDom(saveBtn).click();
        return this;
    }

    private void creatingTableWithParams(int columns, int rows){
        //Нажатие кнопки настройки размеров таблицы
        String insertTable = "//*[@id=\"tlbtn-inserttable\"]/button";
        waitElementVisible(insertTable).click();
        String paramsTable = "//*[@id=\"asc-gen223\"]";
        waitElementVisible(paramsTable).click();

        //Установка размеров таблицы
        String colsTableField = "//div[@class = 'columns-val spinner']//input[@class = 'form-control']";
        waitElementVisible(colsTableField).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(colsTableField)).sendKeys(Integer.toString(columns));
        String rowsTableField = "//div[@class = 'rows-val spinner']//input[@class = 'form-control']";
        waitElementVisible(rowsTableField).click();
        driver.findElement(By.xpath(rowsTableField)).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(rowsTableField)).sendKeys(Integer.toString(rows));
        String confirmParamsTable = "//button[@class = 'btn normal dlg-btn primary']";
        waitElementVisible(confirmParamsTable).click();
    }
    @Step("Создать табличку определённого размера ")
    public NewDocumentPage creatingTable(int columns, int rows){

        //Открытие вкладки "Вставка"
        start(insertTabBtn);

        //Создание таблицы в соответствии с параметрами
        creatingTableWithParams(columns, rows);

        //Заполнение таблицы
        for(int i = 0; i < rows * columns; i++){
            new Actions(driver).sendKeys(i + 1 +" ячейка").perform();
            new Actions(driver).keyDown(Keys.ARROW_RIGHT).perform();
        }
        new Actions(driver).sendKeys("\n").perform();
        waitElementVisible(saveBtn).click();
        return this;
    }

    @Step("Добавить N-ое количество картинок")
    public NewDocumentPage insertingImages(int numberOfImages, String url){
        start(insertTabBtn);

        String insertImageBtn = "//*[@id=\"tlbtn-insertimage\"]/button";
        String insertURLImageBtn = "//*[@id=\"asc-gen238\"]";
        String insertURL = "//*[@id=\"id-dlg-url\"]/div/input";
        for(int i = 0; i < numberOfImages + 1; i++){
            new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(insertImageBtn))));
            waitElementVisible(insertImageBtn).click();
            waitElementVisible(insertURLImageBtn).click();
            waitElementVisible(insertURL).click();
            driver.findElement(By.xpath(insertURL)).sendKeys(url);
            new Actions(driver).sendKeys("\n").perform();
            new Actions(driver).keyDown(Keys.ARROW_RIGHT).perform();
        }

        waitElementVisible(saveBtn).click();
        return this;
    }
}
