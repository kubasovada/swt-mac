package libs.ui.factories;

import io.appium.java_client.AppiumDriver;
import libs.Platform;
import libs.ui.WelcomePageObject;
import libs.ui.android.AndroidWelcomePageObject;
import libs.ui.ios.iOSWelcomePageObject;

public class WelcomePageFactory {

    public static WelcomePageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidWelcomePageObject(driver);
        } else {
            return new iOSWelcomePageObject(driver);
        }
     }
}
