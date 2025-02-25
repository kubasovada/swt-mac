package libs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import libs.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {
    private static final String PLATFORM_IOS = "iOS";
    private static final String PLATFORM_ANDROID = "android";
   // protected Platform platform;


    protected AppiumDriver driver;
    private String AppiumUrl = "http://127.0.0.1:4723";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        driver = Platform.getInstance().getDriver();
//        this.platform = new Platform();
//        driver = this.platform.getDriver();

//        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
//        driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
        this.rotateScreenPortrait();
        this.skipWelcomePageForIosApp();

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        driver.quit();
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(seconds);
    }

//    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
//        String platform = System.getenv("PLATFORM");
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//
//        if (platform.equals(PLATFORM_ANDROID)) {
//            capabilities.setCapability("platformName", "Android");
//            capabilities.setCapability("appium:deviceName", "and80");
//            capabilities.setCapability("appium:platformVersion", "9.0");
//            capabilities.setCapability("appium:automationName", "UiAutomator2");
//            capabilities.setCapability("appium:appPackage", "org.wikipedia");
//            capabilities.setCapability("appium:appActivity", "main.MainActivity");
//            //capabilities.setCapability("appium:app", "/Users/darya/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
//            capabilities.setCapability("appium:app", "/Users/darya/Desktop/JavaAppiumAutomation/apks/Wikipedia_Apkpure.apk");
//
//
//        } else if (platform.equals(PLATFORM_IOS)) {
//            capabilities.setCapability("platformName", "iOS");
//            capabilities.setCapability("appium:deviceName", "iPhone 15");
//            capabilities.setCapability("appium:platformVersion", "17.5");
//            capabilities.setCapability("appium:automationName", "XCUITest");
//            capabilities.setCapability("appium:app", "/Users/darya/Desktop/JavaAppiumAutomation/apks/Wikipedia772.app");
//
//        } else {
//            throw new Exception("Cannot get run platform from env variables" + platform);
//        }
//        return capabilities;
//    }

    private void skipWelcomePageForIosApp() {
        if (Platform.getInstance().isIOS()) {
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }

}

