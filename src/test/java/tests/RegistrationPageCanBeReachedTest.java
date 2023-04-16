package tests;

import constants.ServerUrl;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.IndexPageObject;
import pageObject.LoginPageObject;
import pageObject.RegisterPageObject;
import util.WebDriverUtil;

public class RegistrationPageCanBeReachedTest {
    private WebDriver driver;
    private IndexPageObject indexPageObject;
    private RegisterPageObject registerPageObject;
    private LoginPageObject loginPageObject;

    @Before
    public void setUp() {
        driver = WebDriverUtil.createWebDriver();
        driver.get(ServerUrl.MAIN_PAGE_URL);

        indexPageObject = new IndexPageObject(driver);
        loginPageObject = new LoginPageObject(driver);
        registerPageObject = new RegisterPageObject(driver);
    }

    @Test
    @DisplayName("Register page is reachable from main page through login page")
    public void registerPageCanBeReachedFromMainPage() {
        indexPageObject.waitWhilePageLoaded();
        indexPageObject.clickHeaderAccountButton();

        loginPageObject.waitWhilePageLoaded();
        loginPageObject.clickRegisterLink();

        registerPageObject.waitWhilePageLoaded();
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
