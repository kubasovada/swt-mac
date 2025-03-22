package libs.ui.ios;

import io.appium.java_client.AppiumDriver;
import libs.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {

    static {SAVED_LINKS = "xpath://XCUIElementTypeButton[@name=\"Saved\"]";
}
    public iOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
