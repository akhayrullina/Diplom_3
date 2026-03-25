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

class PersonalAccountTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
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
        UserTestData.deleteUserAfterTest(user, userApi, checkResponse);
    }

    @Step("Проверка успешного входа в аккаунт через заполнение полей Email и Пароль в форме авторизации 'Вход' и клик по кнопке 'Войти'")
    public void loginSuccess() {
        credentials = new UserCredentials(user);

        homePage.waitForLoadHomePage();
        homePage.clickLogInToYourAccountButton();
        loginPage.waitForLoadLoginPage();
        loginPage.setEmail(credentials.getEmail());
        loginPage.setPassword(credentials.getPassword());
        loginPage.clickLoginButton();

        homePage.waitForLoadHomePage();
        assertTrue(driver.findElement(homePage.getBurgerIngredientsContainer()).isDisplayed());
    }

    @Test
    @DisplayName("Проверка перехода в 'Личный кабинет' после авторизации")
    void checkTheTransitionToThePersonalAccountAfterAuthorization() {
        loginSuccess();
        homePage.clickPersonalAccountButton();
        personalAccountPage.waitForLoadPersonalAccountPage();
        assertTrue(driver.findElement(personalAccountPage.getProfile()).isDisplayed());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета на Главную страницу по клику на вкладку 'Конструктор'")
    void clickOnTheConstructorFromPersonalAccount() {
        loginSuccess();
        homePage.clickPersonalAccountButton();
        personalAccountPage.waitForLoadPersonalAccountPage();
        personalAccountPage.clickConstructorButton();
        homePage.waitForLoadHomePage();
        assertTrue(driver.findElement(homePage.getBurgerIngredientsContainer()).isDisplayed());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета на Главную страницу по клику на логотип Stellar Burgers")
    void clickOnTheLogoStellarBurgersFromPersonalAccount() {
        loginSuccess();
        homePage.clickPersonalAccountButton();
        personalAccountPage.waitForLoadPersonalAccountPage();
        personalAccountPage.clickLogoStellarBurgers();
        homePage.waitForLoadHomePage();
        assertTrue(driver.findElement(homePage.getBurgerIngredientsContainer()).isDisplayed());
    }


    @Test
    @DisplayName("Проверка выхода по кнопке «Выйти» в личном кабинете")
    void logoutFromPersonalAccount() {
        loginSuccess();
        homePage.clickPersonalAccountButton();
        personalAccountPage.waitForLoadPersonalAccountPage();
        personalAccountPage.clickLogoutButton();
        loginPage.waitForLoadLoginPage();
        assertTrue(driver.findElement(loginPage.getEntranceTitle()).isDisplayed());
    }
}
