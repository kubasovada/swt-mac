package libs.ui.android;

import io.appium.java_client.AppiumDriver;
import libs.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        SAVED_LINKS = "xpath:(//android.widget.ImageView[@resource-id=\"org.wikipedia:id/navigation_bar_item_icon_view\"])[2]";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
