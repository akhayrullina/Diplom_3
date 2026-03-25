package test;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import utils.api.UserApi;
import utils.api.UserCheckResponse;
import utils.config.DriverExtension;
import utils.pageObject.HomePage;
import utils.pageObject.LoginPage;
import utils.pageObject.RegistrationPage;
import utils.testdata.UserCredentials;
import utils.testdata.UserTestData;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrationTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private UserApi userApi;
    private UserCheckResponse checkResponse;
    private UserCredentials credentials;

    private static Stream<Arguments> usersTestDataForRegistration() {
        return Stream.of(
                Arguments.of(UserTestData.random()),
                Arguments.of(UserTestData.random())
        );
    }

    @RegisterExtension
    private final DriverExtension extension = new DriverExtension();

    @BeforeEach
    void init() {
        driver = extension.getDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        userApi = new UserApi();
        checkResponse = new UserCheckResponse();
    }

    @AfterEach
    void deleteUsers() {
        if(credentials != null) {
            Response loginResponse = userApi.loginUser(credentials);
            String accessToken = checkResponse.saveUserAccessToken(loginResponse);
            if(accessToken != null) {
                userApi.deleteUser(accessToken);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("usersTestDataForRegistration")
    @DisplayName("Проверка успешной регистрации пользователя")
    void successfulRegistration(UserTestData user) {
        credentials = new UserCredentials(user);

        homePage.waitForLoadHomePage();
        homePage.clickLogInToYourAccountButton();

        loginPage.waitForLoadLoginPage();
        loginPage.clickRegisterButton();

        registrationPage.waitForLoadRegistrationPage();
        registrationPage.setName(user.getName());
        registrationPage.setEmail(user.getEmail());
        registrationPage.setPassword(user.getPassword());
        registrationPage.clickRegisterButton();

        loginPage.waitForLoadLoginPage();

        assertTrue(driver.findElement(loginPage.getEntranceTitle()).isDisplayed());
    }

    @Test
    @DisplayName("Проверка ошибки для некорректного пароля, если он короче 6 символов.")
    void errorRegistrationtPasswordIsTooSmall() {
        UserTestData user = new UserTestData("KateTest@mail.ru", "Kate", "123");
        homePage.waitForLoadHomePage();
        homePage.clickLogInToYourAccountButton();

        loginPage.waitForLoadLoginPage();
        loginPage.clickRegisterButton();

        registrationPage.waitForLoadRegistrationPage();
        registrationPage.setName(user.getName());
        registrationPage.setEmail(user.getEmail());
        registrationPage.setPassword(user.getPassword());
        registrationPage.clickRegisterButton();
        registrationPage.waitForLoadRegistrationPage();

        assertTrue(registrationPage.messageErrorPassword().isDisplayed());
    }
}
