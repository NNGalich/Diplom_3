package pageObject;

import constants.ServerUrl;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPageObject extends CommonPageObject {
    private final By loginLink = By.xpath("//a[@href=\"/login\"]");

    public ForgotPasswordPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Wait forgot password page fully loaded")
    public void waitWhilePageLoaded() {
        waitWhilePageUrlIsChangedTo(ServerUrl.FORGOT_PASSWORD_PAGE_URL);
        waitWhileOverlayGone();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(loginLink));
    }

    @Step("Click login link on forgot password page")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}