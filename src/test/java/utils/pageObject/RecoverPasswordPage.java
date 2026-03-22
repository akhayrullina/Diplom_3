package utils.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoverPasswordPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //Локаторы формы "Восстановление пароля"
    private By recoverPasswordTitle = By.xpath(".//h2[text() = 'Восстановление пароля']"); //Заголовок формы "Восстановление пароля"
    //Локатор кнопки "Вспомнили пароль? Войти"
    private By loginButton = By.xpath(".//a[@class= 'Auth_link__1fOlj' and text() = 'Войти']");

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Метод ожидает загрузки формы 'Восстановление пароля'")
    public void waitForLoadRecoverPasswordPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(recoverPasswordTitle));
    }

    @Step("Метод кликает по кнопке 'Войти' поля 'Вспомнили пароль?'")
    public void clickLoginButton() {
        WebElement button = driver.findElement(loginButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
    }
}
