package tests;

import constants.IngredientType;
import constants.ServerUrl;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObject.IndexPageObject;
import util.WebDriverUtil;

@RunWith(Parameterized.class)
public class ConstructorIngredientsTest {

    private final IngredientType ingredientTypeToCheck;
    private WebDriver driver;
    private IndexPageObject indexPageObject;

    public ConstructorIngredientsTest(IngredientType ingredientTypeToCheck) {
        this.ingredientTypeToCheck = ingredientTypeToCheck;
    }

    @Before
    public void setUp() {
        driver = WebDriverUtil.createWebDriver();
        driver.get(ServerUrl.MAIN_PAGE_URL);

        indexPageObject = new IndexPageObject(driver);

        indexPageObject.waitWhilePageLoaded();
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] getIngredientType() {
        return new Object[][]{
                {IngredientType.BUN},
                {IngredientType.MAIN},
                {IngredientType.SAUCE},
        };
    }

    @Test
    @DisplayName("Ingredient type button scrolls ingredients to corresponding section")
    public void ingredientTypeButtonScrollsToCorrespondingSection() {
        scrollToInitialIngredientType();

        indexPageObject.clickToIngredientButton(ingredientTypeToCheck);
        indexPageObject.waitIngredientHeaderIsVisible(ingredientTypeToCheck);
    }

    @Step("Scroll to some another ingredient type to hide target one")
    private void scrollToInitialIngredientType() {
        IngredientType initialIngredientType = getInitialIngredientType();
        indexPageObject.clickToIngredientButton(initialIngredientType);
        indexPageObject.waitIngredientHeaderIsVisible(initialIngredientType);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    private IngredientType getInitialIngredientType() {
        if (ingredientTypeToCheck == IngredientType.SAUCE) {
            return IngredientType.MAIN;
        } else {
            return IngredientType.SAUCE;
        }
    }
}
