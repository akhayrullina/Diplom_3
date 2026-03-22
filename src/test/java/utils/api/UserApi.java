package utils.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.config.BaseURL;
import utils.testdata.UserCredentials;
import utils.testdata.UserTestData;
import static io.restassured.RestAssured.given;

public class UserApi extends BaseURL {
    private final RequestSpecification spec;

    public UserApi() {
        this.spec = setUp();
    }

    @Step("Создание пользователя")
    public Response createUser(UserTestData user) {
        return given()
                .spec(spec)
                .body(user)
                .when()
                .post("/auth/register");
    }

    @Step("Авторизация пользователя")
    public Response loginUser(UserCredentials userCredentials) {
        return given()
                .spec(spec)
                .body(userCredentials)
                .when()
                .post("/auth/login");
    }

    @Step("Выход пользователя из системы")
    public Response logoutUser(String refreshToken) {
        String json = "{\"token\": " + refreshToken + "}";
        return given()
                .spec(spec)
                .body(json)
                .when()
                .post("/auth/logout");
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        return given()
                .spec(spec)
                .auth().oauth2(accessToken)
                .delete("/auth/user");
    }
}
