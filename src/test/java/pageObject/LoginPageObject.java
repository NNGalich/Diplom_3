package pageObject;

import constants.ServerUrl;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageObject extends CommonPageObject {

    private final By emailField = By.xpath("//main/div/form/fieldset[1]/div/div/input");
    private final By passwordField = By.xpath("//main/div/form/fieldset[2]/div/div/input");
    private final By enterButton = By.xpath("//main/div/form/button");

    private final By registerLink = By.xpath("//a[@href=\"/register\"]");

    public LoginPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Wait login page fully loaded")
    public void waitWhilePageLoaded() {
        waitWhilePageUrlIsChangedTo(ServerUrl.LOGIN_PAGE_URL);
        waitWhileOverlayGone();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(emailField));
    }

    @Step("Fill email field on login page")
    public void fillEmailField(String value) {
        driver.findElement(emailField).sendKeys(value);
    }

    @Step("Fill password field on login page")
    public void fillPasswordField(String value) {
        driver.findElement(passwordField).sendKeys(value);
    }

    @Step("Click enter button on login page")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Click register link on login page")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }
}