package pageObject;

import constants.IngredientType;
import constants.ServerUrl;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPageObject extends CommonPageObject {
    private final By headerAccountButton = By.xpath("//header/nav/a");
    private final By constructedOrderAccountButton = By.xpath("//main/section[2]/div/button");

    private final By bunsButton = By.xpath("//main/section[1]/div[1]/div[1]");
    private final By saucesButton = By.xpath("//main/section[1]/div[1]/div[2]");
    private final By mainsButton = By.xpath("//main/section[1]/div[1]/div[3]");

    private final By bunsHeader = By.xpath("//main/section[1]/div[2]/h2[1]");
    private final By saucesHeader = By.xpath("//main/section[1]/div[2]/h2[2]");
    private final By mainsHeader = By.xpath("//main/section[1]/div[2]/h2[3]");

    public IndexPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Wait main (constructor) page fully loaded")
    public void waitWhilePageLoaded() {
        waitWhilePageUrlIsChangedTo(ServerUrl.MAIN_PAGE_URL);
        waitWhileOverlayGone();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(headerAccountButton));
    }

    @Step("Click 'Personal Account' button in header on main (constructor) page")
    public void clickHeaderAccountButton() {
        driver.findElement(headerAccountButton).click();
    }

    @Step("Click 'Login into Account' button under built order on main (constructor) page")
    public void clickConstructedOrderAccountButton() {
        driver.findElement(constructedOrderAccountButton).click();
    }

    private By getIngredientButtonByType(IngredientType ingredientType) {
        switch (ingredientType) {
            case BUN: return bunsButton;
            case SAUCE: return saucesButton;
            case MAIN: return mainsButton;
            default: return null;
        }
    }

    private By getIngredientHeaderByType(IngredientType ingredientType) {
        switch (ingredientType) {
            case BUN: return bunsHeader;
            case SAUCE: return saucesHeader;
            case MAIN: return mainsHeader;
            default: return null;
        }
    }

    @Step("Click to ingredient button on main (constructor) page")
    public void clickToIngredientButton(IngredientType ingredientType) {
        By selector = getIngredientButtonByType(ingredientType);
        driver.findElement(selector).click();
    }

    @Step("Wait for ingredient header in ingredient section is visible on main (constructor) page")
    public void waitIngredientHeaderIsVisible(IngredientType ingredientType) {
        By selector = getIngredientHeaderByType(ingredientType);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(selector));
    }
}