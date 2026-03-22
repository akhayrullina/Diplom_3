package test;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import utils.api.UserApi;
import utils.api.UserCheckResponse;
import utils.config.DriverExtension;
import utils.pageObject.*;
import utils.testdata.UserCredentials;
import utils.testdata.UserTestData;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private RecoverPasswordPage recoverPasswordPage;
    private PersonalAccountPage personalAccountPage;
    private UserApi userApi;
    private UserCheckResponse checkResponse;
    private UserCredentials credentials;
    private UserTestData user;

    @RegisterExtension
    private DriverExtension extension = new DriverExtension();

    private void initializePage() {
        driver = extension.getDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        recoverPasswordPage = new RecoverPasswordPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
    }

    private void initializeAPI(){
        userApi = new UserApi();
        checkResponse = new UserCheckResponse();
        user = UserTestData.createUserForTest(userApi, checkResponse);
    }

    @BeforeEach
    void setUp() {
        initializePage();
        initializeAPI();
    }

    @AfterEach
    void deleteUsers() {
        user.deleteUserAfterTest(user, userApi, checkResponse);
    }

    @Step("Проверка успешного входа в аккаунт через заполнение полей Email и Пароль в форме авторизации 'Вход' и клик по кнопке 'Войти'")
    public void loginSuccess() {
        loginPage.waitForLoadLoginPage();
        loginPage.setEmail(credentials.getEmail());
        loginPage.setPassword(credentials.getPassword());
        loginPage.clickLoginButton();

        homePage.waitForLoadHomePage();
        assertTrue(driver.findElement(homePage.getBurgerIngredientsContainer()).isDisplayed());
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    void loginUsingTheLogInToYourAccountButtonOnTheMainPage() {
        credentials = new UserCredentials(user);

        homePage.waitForLoadHomePage();
        homePage.clickLogInToYourAccountButton();

        loginSuccess();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    void loginUsingThePersonalAccountButtonOnTheMainPage() {
        credentials = new UserCredentials(user);

        homePage.waitForLoadHomePage();
        homePage.clickPersonalAccountButton();

        loginSuccess();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    void loginUsingTheButtonOnTheRegistrationPage() {
        credentials = new UserCredentials(user);

        homePage.waitForLoadHomePage();
        homePage.clickLogInToYourAccountButton();

        loginPage.waitForLoadLoginPage();
        loginPage.clickRegisterButton();

        registrationPage.waitForLoadRegistrationPage();
        registrationPage.clickLoginButton();

        loginSuccess();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    void loginUsingTheRecoverPasswordButtonOnTheLoginPage() {
        credentials = new UserCredentials(user);

        homePage.waitForLoadHomePage();
        homePage.clickPersonalAccountButton();

        loginPage.waitForLoadLoginPage();
        loginPage.clickRecoverPasswordButton();

        recoverPasswordPage.waitForLoadRecoverPasswordPage();
        recoverPasswordPage.clickLoginButton();

        loginSuccess();
    }
}
