package tests;

import client.StellarBurgersTestClient;
import constants.ServerUrl;
import constants.Users;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.LoginPageObject;
import pageObject.RegisterPageObject;
import steps.StellarBurgersApiSteps;
import util.WebDriverUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThrows;

public class UserRegistrationTest {
    private StellarBurgersTestClient client;
    private StellarBurgersApiSteps apiSteps;
    private WebDriver driver;
    private RegisterPageObject registerPageObject;
    private LoginPageObject loginPageObject;

    @Before
    public void setUp() {
        RestAssured.baseURI = ServerUrl.API_URL;
        client = new StellarBurgersTestClient();
        apiSteps = new StellarBurgersApiSteps(client);

        driver = WebDriverUtil.createWebDriver();
        driver.get(ServerUrl.REGISTER_PAGE_URL);

        registerPageObject = new RegisterPageObject(driver);
        loginPageObject = new LoginPageObject(driver);

        apiSteps.deleteUserIfExists(Users.EMAIL, Users.PASSWORD);
        apiSteps.deleteUserIfExists(Users.SHORT_EMAIL, Users.SHORT_PASSWORD);
    }

    @Test
    @DisplayName("Register a new user")
    public void newUserCanBeRegistered() {
        registerPageObject.waitWhilePageLoaded();

        registerPageObject.fillNameField(Users.NAME);
        registerPageObject.fillEmailField(Users.EMAIL);
        registerPageObject.fillPasswordField(Users.PASSWORD);
        registerPageObject.clickRegisterButton();

        loginPageObject.waitWhilePageLoaded();

        apiSteps.loginUser(Users.EMAIL, Users.PASSWORD);
    }

    @Test
    @DisplayName("User with too short password cannot be registered")
    public void userWithTooShortPasswordCannotBeRegistered() {
        registerPageObject.waitWhilePageLoaded();

        registerPageObject.fillNameField(Users.SHORT_NAME);
        registerPageObject.fillEmailField(Users.SHORT_EMAIL);
        registerPageObject.fillPasswordField(Users.SHORT_PASSWORD);
        registerPageObject.clickRegisterButton();

        assertThat(registerPageObject.waitErrorMessageAppearedAndGet(), equalTo("Некорректный пароль"));
        assertThrows(AssertionError.class, () -> apiSteps.loginUser(Users.EMAIL, Users.PASSWORD));
    }

    @Test
    @DisplayName("User with too short password cannot be registered")
    public void duplicatedUserCannotBeRegistered() {
        apiSteps.createUser(Users.EMAIL, Users.PASSWORD, Users.NAME);

        registerPageObject.waitWhilePageLoaded();

        registerPageObject.fillNameField(Users.NAME);
        registerPageObject.fillEmailField(Users.EMAIL);
        registerPageObject.fillPasswordField(Users.ANOTHER_PASSWORD);
        registerPageObject.clickRegisterButton();

        assertThat(registerPageObject.waitErrorMessageAppearedAndGet(), equalTo("Такой пользователь уже существует"));

        apiSteps.loginUser(Users.EMAIL, Users.PASSWORD); // Можем залогиниться
        // А с новым паролем не можем:
        assertThrows(AssertionError.class, () -> apiSteps.loginUser(Users.EMAIL, Users.ANOTHER_PASSWORD));
    }

    @After
    public void tearDown() {
        driver.close();

        apiSteps.deleteUserIfExists(Users.EMAIL, Users.PASSWORD);
        apiSteps.deleteUserIfExists(Users.SHORT_EMAIL, Users.SHORT_PASSWORD);
    }
}
