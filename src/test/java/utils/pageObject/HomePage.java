package utils.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы контейнера "Соберите бургер" с ингредиентами
    private By burgerIngredientsContainer = By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo"); //Контейнер "Соберите бургер" с ингредиентами
    private By bunsTab = By.xpath("//span[text()='Булки']/parent::div"); //Вкладка "Булки"
    private By saucesTab = By.xpath("//span[text()='Соусы']/parent::div"); //Вкладка "Соусы"
    private By fillingTab = By.xpath("//span[text()='Начинки']/parent::div"); //Вкладка "Начинки"

    // Локатор для активной вкладки
    private By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");

    private By logInToYourAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']"); //Кнопка "Войти в аккаунт"
    private By personalAccountButton = By.xpath(".//p[text() = 'Личный Кабинет']"); //Кнопка "Личный Кабинет"

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Метод ждет загрузки Главной страницы")
    public void waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(burgerIngredientsContainer));
    }

    @Step("Клик на вкладку по локатору")
    public void clickTab(By tab) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(tab));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    @Step("Клик на вкладку 'Булки'")
    public void clickBunsTab() {
        clickTab(bunsTab);
    }

    @Step("Клик на вкладку 'Соусы'")
    public void clickSaucesTab() {
        clickTab(saucesTab);
    }

    @Step("Клик на вкладку 'Начинки'")
    public void clickFillingTab() {
        clickTab(fillingTab);
    }

    @Step("Проверка, активна ли вкладка '{tabName}'")
    public boolean isTabActive(String tabName) {
        By activeTabForName = By.xpath(
                String.format("//span[text()='%s']/parent::div[contains(@class, 'tab_tab_type_current')]", tabName)
        );
        return driver.findElements(activeTabForName).size() > 0;
    }

    @Step("Получение текста активной вкладки")
    public String getActiveTabText() {
        WebElement activeTabElement = driver.findElement(activeTab);
        return activeTabElement.findElement(By.tagName("span")).getText();
    }


    @Step("Метод нажимает на кнопку 'Войти в аккаунт'")
    public void clickLogInToYourAccountButton() {
        clickTab(logInToYourAccountButton);
    }

    @Step("Метод нажимает на кнопку 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        clickTab(personalAccountButton);
    }

    public By getBurgerIngredientsContainer() {
        return burgerIngredientsContainer;
    }
}
