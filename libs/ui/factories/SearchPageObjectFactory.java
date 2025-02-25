package libs.ui.factories;

import io.appium.java_client.AppiumDriver;
import libs.Platform;
import libs.ui.SearchPageObject;
import libs.ui.android.AndroidSearchPageObject;
import libs.ui.ios.iOSSearchPageObject;

public class SearchPageObjectFactory {
    public static SearchPageObject get(AppiumDriver driver) {
if (Platform.getInstance().isAndroid()) {
    return new AndroidSearchPageObject(driver);
} else {
    return new iOSSearchPageObject(driver);
}
    }
}
