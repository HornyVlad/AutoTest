package pages.base;

import constant.Constant;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.function.Function;


/**
 * Базовые действия, которые может совершить пользователь для тестирования
 */
public class BaseActions {

    protected WebDriver driver;

    public BaseActions (WebDriver driver) {
        this.driver = driver;
    }

    private By stringToBy(String selector) {
        if (selector.charAt(0) == '#') {
            return By.id(selector.substring(1));
        }
        return By.xpath(selector);
    }

    protected String grabValue(String selector, String attribute) {
        By by = stringToBy(selector);
        return driver.findElement(by).getAttribute(attribute);
    }

    @Step(value = "Сравнение значений {0} = {1}")
    protected void assertText(String val, String expected) {
        Assert.assertEquals(val, expected);
    }

    @Step(value = "Ожидание элемента в окне браузера {0}")
    protected Boolean isVisibleInViewport(String selector) {
        By by = stringToBy(selector);
        return (Boolean)((JavascriptExecutor)driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , driver.findElement(by));
    }

    @Step(value = "Ожидание элемента {0}")
    protected void waitElementVisible(String selector) {
        By by = stringToBy(selector);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(Constant.TimeOut.SHORT))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException timeoutEx) {
            Assert.fail("Элемент " + selector + " не найден за " + Constant.TimeOut.SHORT + " секунд");
        }

    }

    @Step(value = "Ожидание элемента {0}")
    protected void waitElementVisible(String selector, long timeOut) {
        By by = stringToBy(selector);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeOut))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException timeoutEx) {
            Assert.fail("Элемент " + selector + " не найден за " + timeOut + " секунд");
        }
    }

    @Step(value = "Ожидание элемента в DOM {0}")
    protected void waitElementDom(String selector, long timeOut) {
        By by = stringToBy(selector);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeOut))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException timeoutEx) {
            Assert.fail("Элемент " + selector + " не найден в DOM за " + timeOut + " секунд");
        }
    }

    @Step(value = "Ожидание элемента в DOM {0}")
    protected void waitElementDom(String selector) {
        By by = stringToBy(selector);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(Constant.TimeOut.SHORT))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException timeoutEx) {
            Assert.fail("Элемент " + selector + " не найден в DOM за " + Constant.TimeOut.SHORT + " секунд");
        }
    }

    @Step(value = "Ожидание элемента {0} или ошибки {1}")
    protected void waitElementVisibleOrError(String selector, String errSelector, long timeOut) {
        By by = stringToBy(selector);
        By errBy = stringToBy(errSelector);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(1))
                .pollingEvery(Duration.ofMillis(500));
        long start = System.nanoTime();
        while (System.nanoTime() - start < timeOut * 1000 * 1000 * 1000) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                return;
            } catch (TimeoutException timeoutExBy) {
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(errBy));
                    Assert.fail("Найдена ошибка " + errSelector);
                    return;
                } catch (TimeoutException ignored) {
                }
            }
        }
        throw new TimeoutException("Превышено время ожидания " + timeOut + " секунд");
    }

    @Step(value = "Заполнение поля {0}")
    protected void fillField(String selector, String text) {
        By by = stringToBy(selector);
        driver.findElement(by).sendKeys(text);
    }

    @Step(value = "Нажатие на кнопку {0}")
    protected void click(String selector) {
        By by = stringToBy(selector);
        driver.findElement(by).click();
    }

    /**
     * Скролл через Actions
     * вызывает исключение при невозможности скроллинга к элементу
     * @param selector
     */
    @Step(value = "Скролл к элементу {0}")
    protected void scrollTo(String selector) {
        By by = stringToBy(selector);
        WebElement element = driver.findElement(by);
        new Actions(driver)
                .scrollToElement(element)
                .perform();
    }

    /**
     * Скролл через JS Executor, используется где невозможно применить скролл через Actions
     * не вызывает исключений при невозможности скроллинга к элементу
     * @param selector
     */
    @Step(value = "Скролл к элементу {0}")
    protected void scrollToJSExecutor(String selector) {
        By by = stringToBy(selector);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
    }
}
