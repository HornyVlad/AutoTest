package base;

import P7.base.BasePageP7;
import P7.docs.CreateDocP7;
import P7.login.LoginPageP7;
import P7.newDoc.NewDocP7;
import common.CommonActions;
import common.Config;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver = CommonActions.createDriver();
    private Boolean isClosing = false;
    protected BasePageP7 basePageP7 = new BasePageP7(driver);
    protected LoginPageP7 loginPageP7 = new LoginPageP7(driver);
    protected CreateDocP7 createDocP7 = new CreateDocP7(driver);
    protected NewDocP7 newDocP7 = new NewDocP7(driver);

    @AfterTest
    public void afterTestClearCookies() {
        if (Config.CLEAR_COOKIES) {
            if (!isClosing) {
                driver.manage().deleteAllCookies();
            }
        }
    }

//    @BeforeClass
//    public void create() {
//        driver = CommonActions.createDriver();
//    }



    @AfterClass(alwaysRun = true)
    public void close() {
        if (Config.CLOSE_BROWSER) {
            driver.quit();
            isClosing = true;
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] saveFailureScreenShot(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod
    public void afterTest(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            saveFailureScreenShot(driver);
        }
    }
}
