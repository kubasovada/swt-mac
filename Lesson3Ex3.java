import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;


public class Lesson3Ex3 {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "and80");
        capabilities.setCapability("appium:platformVersion", "9.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", "main.MainActivity");
        capabilities.setCapability("appium:app", "/Users/darya/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCheckTitle() {
        skipOnboarding();
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find search line",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot find search input",
                5);

        waitForElementPresent(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/search_results_list']"),
                "Cannot find search result list", 5);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='Clear query']"),
                "Cannot find cancel search button",
                5
        );

        WebElement element  = waitForElementPresent(By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/search_empty_message']"),
                "Cannot find search empty message", 15);

        String actual = element.getText();
        Assert.assertEquals("Search empty message is incorrect",
                "Search Wikipedia in more languages",
                actual);
    }

    private WebElement waitForElementPresent(By by, String errorMessage,  long timeOutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String errorMessage,  long timeOutSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutSeconds);
        element.click();
        return element;
    }

    private void skipOnboarding() {
        waitForElementAndClick(By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
                "Cannot find skip button in Onboarding",
                5);
    }

    private void assertElementHasText(By by, String expectedText, String errorMessage) {
        String actualTextFromElement = waitForElementPresent(by, errorMessage, 5).getText();
        Assert.assertEquals(errorMessage, expectedText, actualTextFromElement);
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeOutInSeconds) {
        WebElement element =  waitForElementPresent(by, errorMessage,  timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

}
