package libs.ui.factories;

import io.appium.java_client.AppiumDriver;
import libs.Platform;
import libs.ui.MyListsPageObject;
import libs.ui.android.AndroidMyListsPageObject;
import libs.ui.ios.iOSMyListsPageObject;

public class MyListsPageObjectFactory {

    public static MyListsPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else {
            return new iOSMyListsPageObject(driver);
        }
     }
}

