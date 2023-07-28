import P7.base.BasePageP7;
import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("Тестовый раздел")
@Feature("Тесты")
@Test
public class Tests extends BaseTest {

    @Story("Тест 1")
    @Link("google.com")
    @Test
    public void test() {
        basePageP7.open("https://sshprot.r7-office.ru/Products/Files/#140307");
        loginPageP7.enterLogin();
        loginPageP7.enterPassword();
        loginPageP7.enterButton();
        createDocP7.createDoc2();
        newDocP7.switchWindow();
        newDocP7.switchFrame();
        newDocP7.task2();
        newDocP7.task3();
        newDocP7.task4();
        newDocP7.task5();
        newDocP7.task6();
        newDocP7.task7();
        newDocP7.task8();
        newDocP7.task9();
        newDocP7.task10();
    }
}
