package libs.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {
    protected static String SAVED_LINKS;
     //SAVED_LINKS = "xpath:(//android.widget.ImageView[@resource-id=\"org.wikipedia:id/navigation_bar_item_icon_view\"])[2]";
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void     clickMyLists() {
        this.waitForElementAndClick(SAVED_LINKS,
                "Cannot find navigation button to my lists",
                5);

    }


}
