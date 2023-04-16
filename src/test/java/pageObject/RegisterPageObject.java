package pageObject;

import constants.ServerUrl;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPageObject extends CommonPageObject {

    private final By nameField = By.xpath("//main/div/form/fieldset[1]/div/div/input"); 
    private final By emailField = By.xpath("//main/div/form/fieldset[2]/div/div/input"); 
    private final By passwordField = By.xpath("//main/div/form/fieldset[3]/div/div/input"); 
    private final By registerButton = By.xpath("//main/div/form/button");
    private final By errorMessage = By.cssSelector("p.input__error.text_type_main-default");

    private final By loginLink = By.xpath("//a[@href=\"/login\"]");
    
    public RegisterPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Wait register page fully loaded")
    public void waitWhilePageLoaded() {
        waitWhilePageUrlIsChangedTo(ServerUrl.REGISTER_PAGE_URL);
        waitWhileOverlayGone();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }

    @Step("Fill name field on register page")
    public void fillNameField(String value) {
        driver.findElement(nameField).sendKeys(value);
    }

    @Step("Fill email field on register page")
    public void fillEmailField(String value) {
        driver.findElement(emailField).sendKeys(value);
    }

    @Step("Fill password field on register page")
    public void fillPasswordField(String value) {
        driver.findElement(passwordField).sendKeys(value);
    }

    @Step("Click 'Register' button on register page")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Extract error message on register page")
    public String getErrorMessage() {
        WebElement element = driver.findElement(errorMessage);
        if (element.isDisplayed()) {
            return element.getText();
        } else {
            return "";
        }
    }

    @Step("Wait while error message appeared and get it on register page")
    public String waitErrorMessageAppearedAndGet() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return getErrorMessage();
    }

    @Step("Click login link on register page")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}