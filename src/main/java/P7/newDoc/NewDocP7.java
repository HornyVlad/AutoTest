package P7.newDoc;

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

public class NewDocP7 extends BaseActions {
    protected final String saveBtn = "//*[@id=\"asc-gen584\"]";
    protected final String sizeFontBtn = "//*[@id=\"asc-gen436\"]/input";
    protected final String mainTabBtn = "//*[@id=\"toolbar\"]/div/div[1]/section/ul/li[2]";
    protected final String insertTabBtn = "//*[@id=\"toolbar\"]/div/div[1]/section/ul/li[3]";
    protected final String defaultAlignBtn = "//*[@id=\"id-toolbar-btn-align-left\"]";
    public NewDocP7(WebDriver driver){
        super(driver);
    }

    //У нас открылось новое окно и нужно на него переключиться
    public NewDocP7 switchWindow(){
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
    public NewDocP7 switchFrame(){
        WebElement iframe = driver.findElement(By.cssSelector("#wrap > iframe"));
        waitElementVisible("//*[@id=\"wrap\"]/iframe");
        driver.switchTo().frame(iframe);
        return this;
    }
    //на случай если вдруг мы хотим проверить taskX отдельно
    public void start(String tab){
        waitElementVisible("//*[@id=\"id_viewer_overlay\"]");
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(tab))));
        waitElementVisible(tab);
        click(tab);
    }
    //2) Ариал 12 жирный
    public NewDocP7 task2(){
        String boldTypeFont = "//*[@id=\"id-toolbar-btn-bold\"]";

        start(mainTabBtn);

        waitElementVisible(sizeFontBtn).click();
        fillField(sizeFontBtn, "12");
        waitElementVisible(boldTypeFont).click();
        new Actions(driver).sendKeys("ариал 12 жирный\n").perform();
        click(boldTypeFont);
        waitElementVisible(saveBtn).click();
        return this;
    }
    //3) Ариал 13 наклонный
    public NewDocP7 task3(){
        String italicTypeFont = "//*[@id=\"id-toolbar-btn-italic\"]";

        start(mainTabBtn);

        waitElementVisible(sizeFontBtn).click();
        fillField(sizeFontBtn, "13");
        waitElementVisible(italicTypeFont).click();
        new Actions(driver).sendKeys("ариал 13 наклонный\n").perform();
        click(italicTypeFont);
        waitElementVisible(saveBtn).click();
        return this;
    }
    //4) Ариал 14 наклонный
    public NewDocP7 task4(){
        String strikeoutTypeFont = "//*[@id=\"id-toolbar-btn-strikeout\"]";

        start(mainTabBtn);

        waitElementVisible(mainTabBtn).click();
        waitElementVisible(sizeFontBtn).click();
        fillField(sizeFontBtn, "14");
        waitElementVisible(strikeoutTypeFont).click();
        new Actions(driver).sendKeys("ариал 14 зачёркнутый\n").perform();
        click(strikeoutTypeFont);
        waitElementVisible(saveBtn).click();
        return this;
    }
    //5) 4 столбца
    public NewDocP7 task5(){
        int cols = 4;
        //переключение на вкладку макет
        String layoutTab = "//*[@id=\"toolbar\"]/div/div[1]/section/ul/li[4]";

        start(layoutTab);

        //Создание разрыва
        String gapBtn = "//*[@id=\"id-toolbar-toolbar__icon btn-pagebreak1\"]/button[2]";
        String choiceGapBtn = "//*[@id=\"id-toolbar-toolbar__icon btn-pagebreak1\"]/ul/li[3]";
        String choiceTypeGapBtn = "(//a[starts-with(text(), \"На текущей странице\")])[2]";
        waitElementVisible(gapBtn).click();
        new Actions(driver).moveToElement(driver.findElement(By.xpath(choiceGapBtn))).perform();
        waitElementVisible(choiceTypeGapBtn);
        new Actions(driver).moveToElement(driver.findElement(By.xpath(choiceTypeGapBtn))).click().perform();

        //Делаем текст в 4 столбца
        String columnBtn = "//*[@id=\"tlbtn-columns\"]/button";
        String columnParamsBtn = "//*[@id=\"asc-gen325\"]";
        String inputNumCols = "//*[@id=\"custom-columns-spin-num\"]/input";
        String confirmNumColsBtn = "//button[@class = 'btn normal dlg-btn primary']";
        waitElementVisible(columnBtn).click();
        waitElementVisible(columnParamsBtn).click();
        waitElementVisible(inputNumCols).click();
        driver.findElement(By.xpath(inputNumCols)).sendKeys(Keys.DELETE);
        fillField(inputNumCols, Integer.toString(cols));
        waitElementVisible(confirmNumColsBtn).click();

        //Заполняем 4 столбца
        choiceGapBtn = "(//a[starts-with(text(), \"Вставить разрыв колонки\")])[2]";
        new Actions(driver).sendKeys("1 столбец").perform();
        for(int i = 1; i < cols; i++){
            waitElementVisible(gapBtn).click();
            waitElementVisible(choiceGapBtn).click();
            new Actions(driver).sendKeys(i + 1 + " столбец").perform();
        }

        //Создаём разрыв
        waitElementVisible(gapBtn).click();
        choiceGapBtn = "//*[@id=\"id-toolbar-toolbar__icon btn-pagebreak1\"]/ul/li[3]";
        waitElementVisible(choiceGapBtn).click();
        new Actions(driver).moveToElement(driver.findElement(By.xpath(choiceGapBtn))).perform();
        waitElementVisible(choiceTypeGapBtn);
        new Actions(driver).moveToElement(driver.findElement(By.xpath(choiceTypeGapBtn))).click().perform();

        //Меняем количество столбцов на 1
        waitElementVisible(columnBtn).click();
        columnParamsBtn = "//*[@id=\"asc-gen313\"]";
        waitElementVisible(columnParamsBtn).click();
        waitElementVisible(mainTabBtn).click();
        waitElementDom(saveBtn).click();
        return this;
    }
    //6) Выравнивание по левому краю
    public NewDocP7 task6(){

        start(mainTabBtn);

        String alignLeftBtn = defaultAlignBtn;
        waitElementVisible(alignLeftBtn).click();
        new Actions(driver).sendKeys("выравнивание по левому краю\n").perform();
        waitElementDom(saveBtn).click();
        return this;
    }
    //7) Выравнивание по правому краю
    public NewDocP7 task7(){

        start(mainTabBtn);

        String alignRightBtn = "//*[@id=\"id-toolbar-btn-align-right\"]";
        waitElementVisible(alignRightBtn).click();
        new Actions(driver).sendKeys("выравнивание по правому краю\n").perform();
        waitElementDom(saveBtn).click();
        return this;
    }
    //8) Выравнивание по ширине
    public NewDocP7 task8(){

        start(mainTabBtn);

        String alignWidthBtn = "//*[@id=\"id-toolbar-btn-align-just\"]";
        waitElementVisible(alignWidthBtn).click();
        new Actions(driver).sendKeys("выравнивание по ширине\n").perform();
        waitElementDom(saveBtn).click();
        return this;
    }
    //9) Создание таблицы в 5 строк и колонок
    public NewDocP7 task9(){
        int cols = 5, rows = 5;

        //Открытие вкладки "Вставка"
        start(insertTabBtn);

        //Нажатие кнопки настройки размеров таблицы
        String insertTable = "//*[@id=\"tlbtn-inserttable\"]/button";
        waitElementVisible(insertTable).click();
        String paramsTable = "//*[@id=\"asc-gen223\"]";
        waitElementVisible(paramsTable).click();

        //Установка размеров таблицы
        String colsTable = "//div[@class = 'columns-val spinner']//input[@class = 'form-control']";
        waitElementVisible(colsTable).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(colsTable)).sendKeys(Integer.toString(cols));
        String rowsTable = "//div[@class = 'rows-val spinner']//input[@class = 'form-control']";
        waitElementVisible(rowsTable).click();
        driver.findElement(By.xpath(rowsTable)).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(rowsTable)).sendKeys(Integer.toString(rows));
        String confirmParamsTable = "//button[@class = 'btn normal dlg-btn primary']";
        waitElementVisible(confirmParamsTable).click();

        //Заполнение таблицы
        for(int i = 0; i < rows * cols; i++){
            new Actions(driver).sendKeys(i + 1 +" ячейка").perform();
            new Actions(driver).keyDown(Keys.ARROW_RIGHT).perform();
        }
        new Actions(driver).sendKeys("\n").perform();
        waitElementVisible(saveBtn).click();
        return this;
    }
    //10) Вставка 5 картинок
    public NewDocP7 task10(){

        start(insertTabBtn);

        String insertImageBtn = "//*[@id=\"tlbtn-insertimage\"]/button";
        String insertURLImageBtn = "//*[@id=\"asc-gen238\"]";
        String insertURL = "//*[@id=\"id-dlg-url\"]/div/input";
        for(int i = 0; i < 5; i++){
            waitElementVisible(insertImageBtn).click();
            waitElementVisible(insertURLImageBtn).click();
            waitElementVisible(insertURL).click();
            driver.findElement(By.xpath(insertURL)).sendKeys("https://sun9-36.userapi.com/impg/tj9Uld2M1EiT-ipJ7SWAh9rWvQq9TnjHffr0bA/XeC3sZWs5gU.jpg?size=960x1280&quality=96&sign=d42390ae2d3b08bcab1ecfae38b7201b&type=album");
            new Actions(driver).sendKeys("\n").perform();
            new Actions(driver).keyDown(Keys.ARROW_RIGHT).perform();
        }

        waitElementVisible(saveBtn).click();
        return this;
    }
}
