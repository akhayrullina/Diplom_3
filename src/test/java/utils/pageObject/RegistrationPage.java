package utils.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //Локаторы формы "Регистрация"
    private By registrationTitle = By.xpath(".//h2[text() = 'Регистрация']"); //Заголовок формы "Регистрация"
    private By inputName = By.xpath("//fieldset[1]/div/div/input"); //Поле ввода "Имя" //
    private By inputEmail = By.xpath("//fieldset[2]/div/div/input"); //Поле ввода "Email"
    private By inputPassword = By.xpath("//fieldset[3]/div/div/input"); //Поле ввода "Пароль"
    private By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']"); //Кнопка "Зарегистрироваться"
    private By warningErrorPassword = By.xpath("//p[text() = 'Некорректный пароль']"); //Предупреждение "Некорректный пароль"
    //Локатор кнопки "Уже зарегистрированы? Войти"
    private By loginButton = By.xpath(".//a[@class= 'Auth_link__1fOlj' and text() = 'Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Метод ожидает загрузки формы 'Регистрация'")
    public void waitForLoadRegistrationPage() {
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, 0);");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationTitle));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(registrationTitle));
    }

    @Step("Метод заполняет поле 'Имя'")
    public void setName(String name) {
        WebElement fieldName = driver.findElement(inputName);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", fieldName);
        wait.until(ExpectedConditions.elementToBeClickable(inputName));
        fieldName.sendKeys(name);
    }

    @Step("Метод заполняет поле 'Email'")
    public void setEmail(String email) {
        WebElement fieldEmail = driver.findElement(inputEmail);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", fieldEmail);
        wait.until(ExpectedConditions.elementToBeClickable(inputEmail));
        fieldEmail.sendKeys(email);
    }

    @Step("Метод заполняет поле 'Пароль'")
    public void setPassword(String password) {
        WebElement fieldPassword = driver.findElement(inputPassword);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", fieldPassword);
        wait.until(ExpectedConditions.elementToBeClickable(inputPassword));
        fieldPassword.sendKeys(password);
    }

    @Step("Метод кликает по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        WebElement button = driver.findElement(registerButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        button.click();
    }

    @Step("Метод кликает по кнопке 'Войти' поля 'Уже зарегистрированы?'")
    public void clickLoginButton() {
        WebElement button = driver.findElement(loginButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
    }

    public WebElement messageErrorPassword() {
        return driver.findElement(warningErrorPassword);
    }
}
