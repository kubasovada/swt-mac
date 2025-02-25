package libs.ui.android;

import io.appium.java_client.AppiumDriver;
import libs.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
    SEARCH_INIT_ELEMENT ="xpath://android.widget.TextView[@text=\"Search Wikipedia\"]";
    SEARCH_INPUT ="xpath://android.widget.EditText[@resource-id=\"org.wikipedia:id/search_src_text\"]";
    SEARCH_CANCEL_BUTTON ="xpath://android.widget.ImageView[@content-desc=\"Clear query\"]";
    SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text='{SUBSTRING}']";

    SEARCH_RESULT_ELEMENT ="xpath://android.widget.ImageView[@resource-id=\"org.wikipedia:id/page_list_item_image\"]";
    SEARCH_EMPTY_RESULT_ELEMENT ="xpath://*[@text, 'No results']";
    SEARCH_RESULTS_LIST ="xpath://androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/search_results_list']";

}

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
