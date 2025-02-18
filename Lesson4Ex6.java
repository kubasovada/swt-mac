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

public class Lesson4Ex6 {
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
    public void testAssertTitlePresent() {
        skipOnboarding();
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text=\"Search Wikipedia\"]"),
                "Cannot find element",
                5
        );

        String searchLine = "JAVA";
        waitForElementAndSendKeys(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                searchLine,
                "Cannot find search input",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cannot find element: JAVA programming language",
                5
        );

//waitForElementPresent(
//        By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
//        "Cannot find element: JAVA programming language",
//        5
//);
        boolean isElementPresent = assertElementPresent( By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"));
        Assert.assertTrue("Cannot find title Java (programming language)",
                isElementPresent);

    }


    private WebElement waitForElementPresent(By by, String errorMessage, long timeOutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element =  waitForElementPresent(by, errorMessage,  timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeOutInSeconds) {
        WebElement element =  waitForElementPresent(by, errorMessage,  timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }
    private void skipOnboarding() {
        waitForElementAndClick(By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
                "Cannot find skip button in Onboarding",
                5);
    }

    private boolean assertElementPresent(By by) {
        WebElement element = driver.findElement(by);
        return element.isEnabled();
    }


    }





