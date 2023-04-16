package tests;

import client.StellarBurgersTestClient;
import constants.ServerUrl;
import constants.Users;
import dto.UserLoginResponseDto;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import steps.StellarBurgerFrontLoginSteps;
import steps.StellarBurgersApiSteps;
import util.WebDriverUtil;

public class UserLoginTest {
    private WebDriver driver;
    private StellarBurgersTestClient client;
    private StellarBurgersApiSteps apiSteps;
    private StellarBurgerFrontLoginSteps frontSteps;
    private UserLoginResponseDto user;

    @Before
    public void setUp() {
        RestAssured.baseURI = ServerUrl.API_URL;
        client = new StellarBurgersTestClient();
        apiSteps = new StellarBurgersApiSteps(client);

        driver = WebDriverUtil.createWebDriver();
        driver.get(ServerUrl.LOGIN_PAGE_URL);

        frontSteps = new StellarBurgerFrontLoginSteps(driver);

        apiSteps.deleteUserIfExists(Users.EMAIL, Users.PASSWORD);
        apiSteps.deleteUserIfExists(Users.ANOTHER_EMAIL, Users.ANOTHER_PASSWORD);
        user = apiSteps.createUser(Users.EMAIL, Users.PASSWORD, Users.NAME);

        frontSteps.getLoginPageObject().waitWhilePageLoaded();
    }

    @Test
    @DisplayName("User can login")
    public void loginIntoExistingUser() {
        frontSteps.loginIntoUserFromLoginPage(Users.EMAIL, Users.PASSWORD);
    }

    @Test
    @DisplayName("User can't login with invalid password")
    public void userCantLoginWithInvalidPassword() {
        frontSteps.tryLoginIntoUserAndCheckNotLoggedIn(Users.EMAIL, Users.ANOTHER_PASSWORD);
    }

    @Test
    @DisplayName("Login try in non-existing account leads to the same error as invalid password")
    public void loginIntoNonExistingUserGivesSameErrorMessageAsWithInvalidPassword() {
        frontSteps.tryLoginIntoUserAndCheckNotLoggedIn(Users.ANOTHER_EMAIL, Users.ANOTHER_PASSWORD);
    }

    @After
    public void tearDown() {
        driver.close();

        apiSteps.deleteUser(user.getAccessToken());
    }
}
