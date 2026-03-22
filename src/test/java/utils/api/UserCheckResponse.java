package utils.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserCheckResponse {
    @Step("Успешное создание пользователя возвращает код ответа 200 OK")
    public void createdUserReturn200(Response createResponse) {
        createResponse.then().assertThat().body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .and()
                .statusCode(200);
    }

    @Step("Сохранение accessToken пользователя")
    public String saveUserAccessToken(Response createResponse) {
        return createResponse.jsonPath().getString("accessToken").substring(7);
    }

    @Step("Успешная авторизация пользователя возвращает код ответа 200 OK")
    public void loginUserReturn200(Response loginResponse) {
        loginResponse.then().assertThat().body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .and()
                .statusCode(200);
    }
}
