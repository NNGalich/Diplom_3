package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPageObject {
    protected final WebDriver driver;

    private final By overlay = By.cssSelector("div.Modal_modal_overlay__x2ZCr");

    public CommonPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Wait page fully loaded")
    public void waitWhilePageLoaded() {
        waitWhileOverlayGone();
    }

    @Step("Wait while page overlay is gone")
    public void waitWhileOverlayGone() {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.invisibilityOfAllElements(driver.findElements(overlay))
        );
    }

    @Step("Wait while page url is changed")
    public void waitWhilePageUrlIsChangedTo(String url) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe(url));
    }
}
