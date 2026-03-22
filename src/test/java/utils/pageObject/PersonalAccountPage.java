package utils.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By profile = By.xpath("//li[@class = 'Account_listItem__35dAP']/a[text() = 'Профиль']");
    private By orderHistory = By.xpath("//li[@class = 'Account_listItem__35dAP']/a[text() = 'История заказов']");
    private By logout = By.xpath("//li[@class = 'Account_listItem__35dAP']/button[text() = 'Выход']");
    private By constructor = By.xpath("//a/p[text() = 'Конструктор']");
    private By logoStellarBurgers = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']/a");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Метод ожидает загрузки Личного кабинета")
    public void waitForLoadPersonalAccountPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profile));
    }

    @Step("Метод кликает по кнопке 'Выход'")
    public void clickLogoutButton() {
        WebElement button = driver.findElement(logout);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        wait.until(ExpectedConditions.elementToBeClickable(logout));
        button.click();
    }

    @Step("Метод кликает по вкладке 'Конструктор'")
    public void clickConstructorButton() {
        WebElement button = driver.findElement(constructor);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        wait.until(ExpectedConditions.elementToBeClickable(constructor));
        button.click();
    }

    @Step("Метод кликает на логотип Stellar Burgers")
    public void clickLogoStellarBurgers() {
        WebElement button = driver.findElement(logoStellarBurgers);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        wait.until(ExpectedConditions.elementToBeClickable(logoStellarBurgers));
        button.click();
    }

    public By getProfile() {
        return profile;
    }
}
