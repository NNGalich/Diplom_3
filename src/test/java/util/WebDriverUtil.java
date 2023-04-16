package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverUtil {
    public static WebDriver createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        return new ChromeDriver(options);
    }
}
