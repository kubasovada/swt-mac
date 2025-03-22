package libs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Platform {

    private static final String PLATFORM_IOS = "iOS";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723";

    //паттерн проектирования синглтон. объект класса создаётся один раз, хранится в поле
    private static Platform instance;

    private Platform() {}

    public static Platform getInstance()
    {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }


    public AppiumDriver getDriver() throws Exception {
        URL url = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return  new AndroidDriver(url,this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(url, this.getIOSDesiredCapabilities());
        } else {
            throw  new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "and80");
        capabilities.setCapability("appium:platformVersion", "9.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", "main.MainActivity");
        //capabilities.setCapability("appium:app", "/Users/darya/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        capabilities.setCapability("appium:app", "/Users/darya/Desktop/JavaAppiumAutomation/apks/Wikipedia_Apkpure.apk");
        return capabilities;
    }


    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "ios");
//        capabilities.setCapability("appium:deviceName", "iPhone 14");
//        capabilities.setCapability("appium:platformVersion", "16.4");
        capabilities.setCapability("appium:deviceName", "iPhone 15");
        capabilities.setCapability("appium:platformVersion", "17.5");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:app", "/Users/darya/Desktop/JavaAppiumAutomation/apks/Wikipedia693.app");
        //capabilities.setCapability("appium:app", "/Users/darya/Desktop/JavaAppiumAutomation/apks/Wikipedia745.app");
        return capabilities;
    }

    private boolean isPlatform(String myPlatform) {
        String platform  = this.getPlatformVar();
        return myPlatform.equals(platform);
    }

    private String getPlatformVar() {
        return System.getenv("PLATFORM");
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
    }
