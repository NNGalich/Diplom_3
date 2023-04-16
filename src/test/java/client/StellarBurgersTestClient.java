package client;

import dto.UserAuthorizationRequestDto;
import dto.UserCreateRequestDto;
import dto.UserLogoutRequestDto;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class StellarBurgersTestClient {

    @Step("POST /api/auth/register")
    public ValidatableResponse createUser(String email, String password, String name) {
        UserCreateRequestDto requestDto = new UserCreateRequestDto(email, password, name);
        return given()
                .header("Content-type", "application/json" )
                .and()
                .body(requestDto)
                .log()
                .all()
                .when()
                .post("/api/auth/register")
                .then()
                .log()
                .all();
    }

    @Step("POST /api/auth/login")
    public ValidatableResponse userAuthorization(String email, String password) {
        UserAuthorizationRequestDto requestDto = new UserAuthorizationRequestDto(email, password);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(requestDto)
                .log()
                .all()
                .when()
                .post("/api/auth/login")
                .then()
                .log()
                .all();
    }

    @Step("POST /api/auth/logout")
    public ValidatableResponse userLogout(String refreshToken) {
        UserLogoutRequestDto requestDto = new UserLogoutRequestDto(refreshToken);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(requestDto)
                .log()
                .all()
                .when()
                .post("/api/auth/logout")
                .then()
                .log()
                .all();
    }

    @Step("DELETE /api/auth/user")
    public ValidatableResponse userDelete(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then()
                .log()
                .all();
    }
}
