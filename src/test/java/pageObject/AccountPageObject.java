package pageObject;

import constants.ServerUrl;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPageObject extends CommonPageObject {
    private final By constructorButton = By.xpath("//header/nav/ul/li[1]/a");
    private final By headerLogo = By.xpath("//header/nav/div/a");
    private final By logoutButton = By.xpath("//main/div/nav/ul/li[3]/button");

    public AccountPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Wait account page fully loaded")
    public void waitWhilePageLoaded() {
        waitWhilePageUrlIsChangedTo(ServerUrl.ACCOUNT_PAGE_URL);
        waitWhileOverlayGone();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
    }

    @Step("Click constructor button in header on account page")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Click header logo on account page")
    public void clickHeaderLogo() {
        driver.findElement(headerLogo).click();
    }

    @Step("Click logout button on account page")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}