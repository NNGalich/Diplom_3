package tests;

import constants.ServerUrl;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.ForgotPasswordPageObject;
import pageObject.IndexPageObject;
import pageObject.LoginPageObject;
import pageObject.RegisterPageObject;
import util.WebDriverUtil;

public class LoginPageCanBeReachedTest {
    private WebDriver driver;
    private IndexPageObject indexPageObject;
    private RegisterPageObject registerPageObject;
    private LoginPageObject loginPageObject;
    private ForgotPasswordPageObject forgotPasswordPageObject;

    @Before
    public void setUp() {
        driver = WebDriverUtil.createWebDriver();

        indexPageObject = new IndexPageObject(driver);
        loginPageObject = new LoginPageObject(driver);
        registerPageObject = new RegisterPageObject(driver);
        forgotPasswordPageObject = new ForgotPasswordPageObject(driver);
    }

    @Test
    @DisplayName("Login page is reachable from main page by clicking 'Enter into Account' button under order section")
    public void loginPageCanBeReachedFromIndexPageByClickingEnterIntoAccountButton() {
        driver.get(ServerUrl.MAIN_PAGE_URL);

        indexPageObject.waitWhilePageLoaded();
        indexPageObject.clickConstructedOrderAccountButton();

        loginPageObject.waitWhilePageLoaded();
    }

    @Test
    @DisplayName("Login page is reachable from main page by clicking 'Personal Account' button in page header")
    public void loginPageCanBeReachedFromIndexPageByClickingPersonalAccountButton() {
        driver.get(ServerUrl.MAIN_PAGE_URL);

        indexPageObject.waitWhilePageLoaded();
        indexPageObject.clickHeaderAccountButton();

        loginPageObject.waitWhilePageLoaded();
    }

    @Test
    @DisplayName("Login page is reachable from register page")
    public void loginPageCanBeReachedFromRegistrationPage() {
        driver.get(ServerUrl.REGISTER_PAGE_URL);

        registerPageObject.waitWhilePageLoaded();
        registerPageObject.clickLoginLink();

        loginPageObject.waitWhilePageLoaded();
    }

    @Test
    @DisplayName("Login page is reachable from forgot password page")
    public void loginPageCanBeReachedFromForgotPasswordPage() {
        driver.get(ServerUrl.FORGOT_PASSWORD_PAGE_URL);

        forgotPasswordPageObject.waitWhilePageLoaded();
        forgotPasswordPageObject.clickLoginLink();

        loginPageObject.waitWhilePageLoaded();
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
