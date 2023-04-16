package tests;

import client.StellarBurgersTestClient;
import constants.ServerUrl;
import constants.Users;
import dto.UserLoginResponseDto;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.AccountPageObject;
import steps.StellarBurgerFrontLoginSteps;
import steps.StellarBurgersApiSteps;
import util.WebDriverUtil;

public class PersonalAccountNavigationTest {
    private StellarBurgersTestClient client;
    private StellarBurgersApiSteps apiSteps;
    private StellarBurgerFrontLoginSteps frontSteps;
    private WebDriver driver;
    private AccountPageObject accountPageObject;
    private UserLoginResponseDto user;

    @Before
    public void setUp() {
        RestAssured.baseURI = ServerUrl.API_URL;
        client = new StellarBurgersTestClient();
        apiSteps = new StellarBurgersApiSteps(client);

        driver = WebDriverUtil.createWebDriver();
        driver.get(ServerUrl.LOGIN_PAGE_URL);

        frontSteps = new StellarBurgerFrontLoginSteps(driver);
        accountPageObject = new AccountPageObject(driver);

        apiSteps.deleteUserIfExists(Users.EMAIL, Users.PASSWORD);
        user = apiSteps.createUser(Users.EMAIL, Users.PASSWORD, Users.NAME);

        frontSteps.loginIntoUserFromLoginPage(Users.EMAIL, Users.PASSWORD);
    }

    @Test
    @DisplayName("Logged in user can enter personal account from main page")
    public void loggedInUserCanEnterPersonalAccountFromMainPage() {
        enterToAccountPageThroughMainPage();
    }

    @Test
    @DisplayName("Logged in user can enter constructor from personal account page using stellar burger header logo")
    public void loggedInUserCanNavigateToConstructorFromAccountPageByClickingOnStellarBurgerHeaderLogo() {
        enterToAccountPageThroughMainPage();

        accountPageObject.clickHeaderLogo();
        frontSteps.getIndexPageObject().waitWhilePageLoaded();
    }

    @Test
    @DisplayName("Logged in user can enter constructor from personal account page using constructor header button")
    public void loggedInUserCanNavigateToConstructorFromAccountPageByClickingOnConstructorHeaderButton() {
        enterToAccountPageThroughMainPage();

        accountPageObject.clickConstructorButton();
        frontSteps.getIndexPageObject().waitWhilePageLoaded();
    }

    @Test
    @DisplayName("Logged in user can log out")
    public void loggedInUserCanLogout() {
        enterToAccountPageThroughMainPage();

        accountPageObject.clickLogoutButton();
        frontSteps.getLoginPageObject().waitWhilePageLoaded();
    }

    // Тест падает из-за какой-то ошибки в javascript
    @Test
    @DisplayName("User can enter to account page directly")
    public void loggedInUserCanGoDirectlyToAccountPage() {
        driver.get(ServerUrl.ACCOUNT_PAGE_URL);
        accountPageObject.waitWhilePageLoaded();
    }

    @Step("Enter to account page through main page")
    private void enterToAccountPageThroughMainPage() {
        driver.get(ServerUrl.MAIN_PAGE_URL);
        frontSteps.getIndexPageObject().waitWhilePageLoaded();

        frontSteps.getIndexPageObject().clickHeaderAccountButton();
        accountPageObject.waitWhilePageLoaded();
    }

    @After
    public void tearDown() {
        driver.close();
        apiSteps.deleteUser(user.getAccessToken());
    }
}
