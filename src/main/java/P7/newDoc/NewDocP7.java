package P7.newDoc;

import P7.base.BasePageP7;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static constant.Constant.TimeOut.EXPLICIT_WAIT;

public class NewDocP7 extends BasePageP7 {
    protected final By saveBtn = By.xpath("//*[@id=\"asc-gen584\"]");
    protected final By changeSizeFontBtn = By.xpath("//*[@id=\"asc-gen436\"]/input");
    protected final By mainTabBtn = By.xpath("//*[@id=\"toolbar\"]/div/div[1]/section/ul/li[2]");
    protected final By insertTabBtn = By.xpath("//*[@id=\"toolbar\"]/div/div[1]/section/ul/li[3]");
    protected final By defaultAlignBtn = By.xpath("//*[@id=\"id-toolbar-btn-align-left\"]");
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
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wrap\"]/iframe")));
        WebElement iframe = driver.findElement(By.cssSelector("#wrap > iframe"));
        driver.switchTo().frame(iframe);
        return this;
    }
    //2) Ариал 12 жирный
    public NewDocP7 task2(){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"id_viewer_overlay\"]")));
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(mainTabBtn));
        WebElement mainTab = driver.findElement(mainTabBtn);
        waitElement(mainTab).click();
        WebElement sizeFontBtn = driver.findElement(changeSizeFontBtn);
        waitElement(sizeFontBtn).click();
        sizeFontBtn.sendKeys("12");
        WebElement boldTypeFont = driver.findElement(By.xpath("//*[@id=\"id-toolbar-btn-bold\"]"));
        waitElement(boldTypeFont).click();
        new Actions(driver).sendKeys("ариал 12 жирный\n").perform();
        waitElement(boldTypeFont).click();
        WebElement save = driver.findElement(saveBtn);
        waitElement(save).click();
        return this;
    }
    //3) Ариал 13 наклонный
    public NewDocP7 task3(){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"id_viewer_overlay\"]")));
        WebElement mainTab = driver.findElement(mainTabBtn);
        waitElement(mainTab).click();
        WebElement sizeFontBtn = driver.findElement(changeSizeFontBtn);
        waitElement(sizeFontBtn).click();
        sizeFontBtn.sendKeys("13");
        WebElement italicTypeFont = driver.findElement(By.xpath("//*[@id=\"id-toolbar-btn-italic\"]"));
        waitElement(italicTypeFont).click();
        new Actions(driver).sendKeys("ариал 13 наклонный\n").perform();
        waitElement(italicTypeFont).click();
        WebElement save = driver.findElement(saveBtn);
        waitElement(save).click();
        return this;
    }
    //4) Ариал 14 наклонный
    public NewDocP7 task4(){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"id_viewer_overlay\"]")));
        WebElement mainTab = driver.findElement(mainTabBtn);
        waitElement(mainTab).click();
        WebElement sizeFontBtn = driver.findElement(changeSizeFontBtn);
        waitElement(sizeFontBtn).click();
        sizeFontBtn.sendKeys("14");
        WebElement strikeoutTypeFont = driver.findElement(By.xpath("//*[@id=\"id-toolbar-btn-strikeout\"]"));
        waitElement(strikeoutTypeFont).click();
        new Actions(driver).sendKeys("ариал 14 наклонный\n").perform();
        waitElement(strikeoutTypeFont).click();
        WebElement save = driver.findElement(saveBtn);
        waitElement(save).click();
        return this;
    }
    //5) 4 столбца
    public NewDocP7 task5(){
        int cols = 4;
        //переключение на вкладку макет
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"id_viewer_overlay\"]")));
        WebElement layoutTab = driver.findElement(By.xpath("//*[@id=\"toolbar\"]/div/div[1]/section/ul/li[4]"));
        waitElement(layoutTab).click();

        //Создание разрыва
        WebElement gapBtn = driver.findElement(By.xpath("//*[@id=\"id-toolbar-toolbar__icon btn-pagebreak1\"]/button[2]"));
        waitElement(gapBtn).click();
        WebElement choiceGapBtn = driver.findElement(By.xpath("//*[@id=\"id-toolbar-toolbar__icon btn-pagebreak1\"]/ul/li[3]"));
        WebElement choiceTypeGapBtn = driver.findElement(By.xpath("(//a[starts-with(text(), \"На текущей странице\")])[2]"));
        new Actions(driver).moveToElement(choiceGapBtn).perform();
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(choiceTypeGapBtn));
        new Actions(driver).moveToElement(choiceTypeGapBtn).click().perform();

        //Делаем текст в 4 столбца
        WebElement columnBtn = driver.findElement(By.xpath("//*[@id=\"tlbtn-columns\"]/button"));
        waitElement(columnBtn).click();
        WebElement columnParamsBtn = driver.findElement(By.xpath("//*[@id=\"asc-gen325\"]"));
        waitElement(columnParamsBtn).click();
        WebElement inputNumCols = driver.findElement(By.xpath("//*[@id=\"custom-columns-spin-num\"]/input"));
        inputNumCols.sendKeys(Keys.BACK_SPACE);
        inputNumCols.sendKeys(Integer.toString(cols));
        WebElement confirmNumColsBtn = driver.findElement(By.xpath("//button[@class = 'btn normal dlg-btn primary']"));
        waitElement(confirmNumColsBtn).click();

        //Заполняем 4 столбца
        choiceGapBtn = driver.findElement(By.xpath("(//a[starts-with(text(), \"Вставить разрыв колонки\")])[2]"));
        new Actions(driver).sendKeys("1 столбец").perform();
        for(int i = 1; i < cols; i++){
            waitElement(gapBtn).click();
            waitElement(choiceGapBtn).click();
            new Actions(driver).sendKeys(i + 1 + " столбец").perform();
        }

        //Создаём разрыв
        waitElement(gapBtn).click();
        choiceGapBtn = driver.findElement(By.xpath("//*[@id=\"id-toolbar-toolbar__icon btn-pagebreak1\"]/ul/li[3]"));
        waitElement(choiceGapBtn).click();
        new Actions(driver).moveToElement(choiceGapBtn).perform();
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(choiceTypeGapBtn));
        new Actions(driver).moveToElement(choiceTypeGapBtn).click().perform();

        //Меняем количество столбцов на 1
        waitElement(columnBtn).click();
        columnParamsBtn = driver.findElement(By.xpath("//*[@id=\"asc-gen313\"]"));
        waitElement(columnParamsBtn).click();
        WebElement save = driver.findElement(saveBtn);
        waitElement(save).click();
        return this;
    }
    //6) Выравнивание по левому краю
    public NewDocP7 task6(){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"id_viewer_overlay\"]")));
        WebElement mainTab = driver.findElement(mainTabBtn);
        waitElement(mainTab).click();
        WebElement alignLeftBtn = driver.findElement(defaultAlignBtn);
        waitElement(alignLeftBtn).click();
        new Actions(driver).sendKeys("выравнивание по левому краю\n").perform();
        WebElement save = driver.findElement(saveBtn);
        waitElement(save).click();
        return this;
    }
    //7) Выравнивание по правому краю
    public NewDocP7 task7(){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"id_viewer_overlay\"]")));
        WebElement mainTab = driver.findElement(mainTabBtn);
        waitElement(mainTab).click();
        WebElement alignRightBtn = driver.findElement(By.xpath("//*[@id=\"id-toolbar-btn-align-right\"]"));
        waitElement(alignRightBtn).click();
        new Actions(driver).sendKeys("выравнивание по правому краю\n").perform();
        WebElement alignLeftBtn = driver.findElement(defaultAlignBtn);
        waitElement(alignLeftBtn).click();
        WebElement save = driver.findElement(saveBtn);
        waitElement(save).click();
        return this;
    }
    //8) Выравнивание по ширине
    public NewDocP7 task8(){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"id_viewer_overlay\"]")));
        WebElement mainTab = driver.findElement(mainTabBtn);
        waitElement(mainTab).click();
        WebElement alignWidthBtn = driver.findElement(By.xpath("//*[@id=\"id-toolbar-btn-align-just\"]"));
        waitElement(alignWidthBtn).click();
        new Actions(driver).sendKeys("выравнивание по ширине\n").perform();
        WebElement alignLeftBtn = driver.findElement(defaultAlignBtn);
        waitElement(alignLeftBtn).click();
        WebElement save = driver.findElement(saveBtn);
        waitElement(save).click();
        return this;
    }
    //9) Создание таблицы в 5 строк и колонок
    public NewDocP7 task9(){
        int cols = 5, rows = 5;

        //Открытие вкладки "Вставка"
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"id_viewer_overlay\"]")));
        WebElement insertTab = driver.findElement(insertTabBtn);
        waitElement(insertTab).click();

        //Нажатие кнопки настройки размеров таблицы
        WebElement insertTable = driver.findElement(By.xpath("//*[@id=\"tlbtn-inserttable\"]/button"));
        waitElement(insertTable).click();
        WebElement paramsTable = driver.findElement(By.xpath("//*[@id=\"asc-gen223\"]"));
        waitElement(paramsTable).click();

        //Установка размеров таблицы
        WebElement colsTable = driver.findElement(By.xpath("//div[@class = 'columns-val spinner']//input[@class = 'form-control']"));
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(colsTable));
        colsTable.sendKeys(Keys.BACK_SPACE);
        colsTable.sendKeys(Integer.toString(cols));
        WebElement rowsTable = driver.findElement(By.xpath("//div[@class = 'rows-val spinner']//input[@class = 'form-control']"));
        waitElement(rowsTable).click();
        rowsTable.sendKeys(Keys.BACK_SPACE);
        rowsTable.sendKeys(Integer.toString(rows));
        WebElement confirmParamsTable = driver.findElement(By.xpath("//button[@class = 'btn normal dlg-btn primary']"));
        waitElement(confirmParamsTable).click();

        //Заполнение таблицы
        for(int i = 0; i < rows * cols; i++){
            new Actions(driver).sendKeys(i + 1 +" ячейка").perform();
            new Actions(driver).keyDown(Keys.ARROW_RIGHT).perform();
        }
        new Actions(driver).sendKeys("\n").perform();
        WebElement save = driver.findElement(saveBtn);
        waitElement(save).click();
        return this;
    }
    //10) Вставка 5 картинок
    public NewDocP7 task10(){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"id_viewer_overlay\"]")));
        WebElement insertTab = driver.findElement(insertTabBtn);
        waitElement(insertTab).click();

        WebElement insertImageBtn = driver.findElement(By.xpath("//*[@id=\"tlbtn-insertimage\"]/button"));
        for(int i = 0; i < 5; i++){
            waitElement(insertImageBtn).click();
            WebElement insertURLImageBtn = driver.findElement(By.xpath("//*[@id=\"asc-gen238\"]"));
            waitElement(insertURLImageBtn).click();
            WebElement insertURL = driver.findElement(By.xpath("//*[@id=\"id-dlg-url\"]/div/input"));
            waitElement(insertURL).click();
            insertURL.sendKeys("https://sun9-36.userapi.com/impg/tj9Uld2M1EiT-ipJ7SWAh9rWvQq9TnjHffr0bA/XeC3sZWs5gU.jpg?size=960x1280&quality=96&sign=d42390ae2d3b08bcab1ecfae38b7201b&type=album");
            new Actions(driver).sendKeys("\n").perform();
            new Actions(driver).keyDown(Keys.ARROW_RIGHT).perform();
        }

        WebElement save = driver.findElement(saveBtn);
        waitElement(save).click();
        return this;
    }
}
