package libs.ui.factories;

import io.appium.java_client.AppiumDriver;
import libs.Platform;
import libs.ui.ArticlePageObject;
import libs.ui.android.AndroidArticlePageObject;
import libs.ui.ios.iOSArticlePageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else {
            return  new iOSArticlePageObject(driver);
        }
    }
}
