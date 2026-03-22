package utils.testdata;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.api.UserApi;
import utils.api.UserCheckResponse;

import java.time.LocalDateTime;

public class UserTestData {
    private String email;
    private String password;
    private String name;

    public UserTestData(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static UserTestData random() {
        var rnd = LocalDateTime.now().getNano();
        return new UserTestData("test" + rnd + "@gmail.ru", "testtest", "test" + rnd);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Step("Создание тестового пользователя")
    public static UserTestData createUserForTest(UserApi userApi, UserCheckResponse checkResponse) {
        UserTestData user = UserTestData.random();
        Response createResponse = userApi.createUser(user);
        checkResponse.createdUserReturn200(createResponse);
        return user;
    }

    @Step("Удаление тестового пользователя")
    public static void deleteUserAfterTest(UserTestData user, UserApi userApi, UserCheckResponse checkResponse) {
        UserCredentials credentials = new UserCredentials(user);
        if (credentials != null) {
            Response loginResponse = userApi.loginUser(credentials);
            String accessToken = checkResponse.saveUserAccessToken(loginResponse);
            if (accessToken != null) {
                userApi.deleteUser(accessToken);
            }
        }
    }
}
