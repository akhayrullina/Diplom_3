package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import utils.config.DriverExtension;
import utils.pageObject.HomePage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConstructorTest {
    private WebDriver driver;
    private HomePage homePage;

    @RegisterExtension
    private final DriverExtension extension = new DriverExtension();

    @BeforeEach
    void init() {
        driver = extension.getDriver();
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Булки'")
    void goToBunsTab() {
        homePage.waitForLoadHomePage();
        homePage.clickBunsTab();
        assertTrue(homePage.isTabActive("Булки"),
                "По умолчанию должна быть активна вкладка 'Булки'");
        assertEquals(homePage.getActiveTabText(), "Булки",
                "Текст активной вкладки должен быть 'Булки'");
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Соусы'")
    void goToSaucesTab() {
        homePage.waitForLoadHomePage();
        homePage.clickSaucesTab();
        assertTrue(homePage.isTabActive("Соусы"),
                "Должна быть активна вкладка 'Соусы'");
        assertEquals(homePage.getActiveTabText(), "Соусы",
                "Текст активной вкладки должен быть 'Булки'");

    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Начинки'")
    void goToFillingTab() {
        homePage.waitForLoadHomePage();
        homePage.clickFillingTab();
        assertTrue(homePage.isTabActive("Начинки"),
                "Должна быть активна вкладка 'Соусы'");
        assertEquals(homePage.getActiveTabText(), "Начинки",
                "Текст активной вкладки должен быть 'Начинки'");
    }
}
