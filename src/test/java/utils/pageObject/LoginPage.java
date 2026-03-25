package utils.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //Локаторы формы "Вход"
    private By entranceTitle = By.xpath(".//h2[text() = 'Вход']"); //Заголовок формы "Вход"
    private By inputEmail = By.xpath(".//fieldset[1]/div/div/input"); //Поле ввода "Email"
    private By inputPassword = By.xpath(".//fieldset[2]/div/div/input"); //Поле ввода "Пароль"
    private By loginButton = By.xpath(".//button[text() = 'Войти']"); //Кнопка "Войти"
    //Локатор кнопки "Вы — новый пользователь? Зарегистрироваться"
    private By registerButton = By.xpath(".//a[@class= 'Auth_link__1fOlj' and text() = 'Зарегистрироваться']");
    //Локатор кнопки "Забыли пароль? Восстановить пароль"
    private By recoverPasswordButton = By.xpath(".//a[@class= 'Auth_link__1fOlj' and text() = 'Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Метод ожидает загрузки формы 'Вход'")
    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(entranceTitle));
    }

    @Step("Метод заполняет поле 'Email'")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(inputEmail));
        driver.findElement(inputEmail).click();
        driver.findElement(inputEmail).sendKeys(email);
    }

    @Step("Метод заполняет поле 'Пароль'")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(inputPassword));
        driver.findElement(inputPassword).click();
        driver.findElement(inputPassword).sendKeys(password);
    }

    @Step("Метод кликает по кнопке 'Войти'")
    public void clickLoginButton() {
        WebElement button = driver.findElement(loginButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
    }

    @Step("Метод кликает по кнопке 'Зарегистрироваться' поля 'Вы — новый пользователь?'")
    public void clickRegisterButton() {
        WebElement button = driver.findElement(registerButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        button.click();
    }

    @Step("Метод кликает по кнопке 'Восстановить пароль' поля 'Забыли пароль?'")
    public void clickRecoverPasswordButton() {
        WebElement button = driver.findElement(recoverPasswordButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        wait.until(ExpectedConditions.elementToBeClickable(recoverPasswordButton));
        button.click();
    }

    public By getEntranceTitle() {
        return entranceTitle;
    }
}
