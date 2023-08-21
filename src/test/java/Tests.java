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
        myBaseActions.open("https://sshprot2.r7-office.ru/Products/Files/#144368");
        authorizationPage.authorization("vladiko.gladiko@vk.com","mnbvcxz2002");

        filesPage.createDoc();

        newDocumentPage.switchWindow();
        newDocumentPage.switchFrame();
        newDocumentPage.textAndBoldFontParams("ариал 12 жирный", 12);
        newDocumentPage.textAndItalicFontParams("ариал 13 наклонный", 13);
        newDocumentPage.textAndStrikeoutFontParams("ариал 14 зачёркнутый", 14);
        newDocumentPage.splitIntoColumns(4);
        newDocumentPage.textAlignmentLeft("выравнивание по левому краю");
        newDocumentPage.textAlignmentRight("выравнивание по правому краю");
        newDocumentPage.textAlignmentWidth("выравнивание по ширине");
        newDocumentPage.creatingTable(5,5);
        newDocumentPage.insertingImages(4 ,"https://sun9-36.userapi.com/impg/tj9Uld2M1EiT-ipJ7SWAh9rWvQq9TnjHffr0bA/XeC3sZWs5gU.jpg?size=960x1280&quality=96&sign=d42390ae2d3b08bcab1ecfae38b7201b&type=album");
    }
}
