package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObject.IndexPageObject;
import pageObject.LoginPageObject;

public class StellarBurgerFrontLoginSteps {
    private final IndexPageObject indexPageObject;
    private final LoginPageObject loginPageObject;

    public StellarBurgerFrontLoginSteps(WebDriver driver) {
        this.indexPageObject = new IndexPageObject(driver);
        this.loginPageObject = new LoginPageObject(driver);
    }

    public IndexPageObject getIndexPageObject() {
        return indexPageObject;
    }

    public LoginPageObject getLoginPageObject() {
        return loginPageObject;
    }

    @Step("Login user")
    public void loginIntoUserFromLoginPage(String email, String password) {
        enterUserCredentials(email, password);
        indexPageObject.waitWhilePageLoaded();
    }

    @Step("Try to login and check nothing changed")
    public void tryLoginIntoUserAndCheckNotLoggedIn(String email, String password) {
        enterUserCredentials(email, password);
        loginPageObject.waitWhilePageLoaded();
    }

    private void enterUserCredentials(String email, String password) {
        loginPageObject.waitWhilePageLoaded();

        loginPageObject.fillEmailField(email);
        loginPageObject.fillPasswordField(password);
        loginPageObject.clickEnterButton();
    }
}
