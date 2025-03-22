package libs.ui.android;

import io.appium.java_client.AppiumDriver;
import libs.ui.MyListsPageObject;

public class AndroidMyListsPageObject  extends MyListsPageObject {

    static {
        MODAL = "xpath://android.widget.Button[@resource-id=\"org.wikipedia:id/negativeButton\"]";
        FOLDER_NAME_TMPL = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text='{FOLDER_NAME}']";
        ARTICLE_TITLE_TMPL = "xpath://*[@text='{TITLE}']";
    }

    public AndroidMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
