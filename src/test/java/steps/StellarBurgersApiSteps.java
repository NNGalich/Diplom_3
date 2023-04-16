package steps;

import client.StellarBurgersTestClient;
import dto.UserLoginResponseDto;
import io.qameta.allure.Step;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;

public class StellarBurgersApiSteps {

    private final StellarBurgersTestClient client;

    public StellarBurgersApiSteps(StellarBurgersTestClient client) {
        this.client = client;
    }

    @Step("Create user")
    public UserLoginResponseDto createUser(String email, String password, String name) {
        return client.createUser(email, password, name)
                .statusCode(200)
                .extract()
                .as(UserLoginResponseDto.class);
    }

    @Step("Login user")
    public UserLoginResponseDto loginUser(String email, String password) {
        return client.userAuthorization(email, password)
                .statusCode(200)
                .extract()
                .as(UserLoginResponseDto.class);
    }

    @Step("Logout user")
    public void logoutUser(String refreshToken) {
        client.userLogout(refreshToken)
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Step("Delete user")
    public void deleteUser(String accessToken) {
        client.userDelete(accessToken)
                .statusCode(anyOf(equalTo(200), equalTo(202)));
    }

    @Step("Delete user if exists")
    public void deleteUserIfExists(String email, String password) {
        try {
            UserLoginResponseDto response = loginUser(email, password);
            deleteUser(response.getAccessToken());
        } catch (AssertionError e) {
        }
    }
}
