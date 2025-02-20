package libs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;
    private String AppiumUrl = "http://127.0.0.1:4723";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "and80");
        capabilities.setCapability("appium:platformVersion", "9.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", "main.MainActivity");
        //capabilities.setCapability("appium:app", "/Users/darya/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        capabilities.setCapability("appium:app", "/Users/darya/Desktop/JavaAppiumAutomation/apks/Wikipedia_Apkpure.apk");

        driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
        this.rotateScreenPortrait();

    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        driver.quit();
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void rotateScreenPortrait () {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(seconds);
    }
}
