package libs.ui.ios;

import io.appium.java_client.AppiumDriver;
import libs.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT ="xpath://XCUIElementTypeSearchField[@name='Поиск по Википедии']";
        SEARCH_INPUT ="xpath:(//XCUIElementTypeStaticText[@name=\"Раздел Википедии: русский\"])[1]";
        SEARCH_CANCEL_BUTTON ="xpath://XCUIElementTypeButton[@name=\"Очистить текст\"]";
        SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";

        SEARCH_RESULT_ELEMENT ="xpath://android.widget.ImageView[@resource-id=\"org.wikipedia:id/page_list_item_image\"]";
        SEARCH_EMPTY_RESULT_ELEMENT ="xpath://XCUIElementTypeStaticText[@name=\"Ничего не найдено\"]";
        SEARCH_RESULTS_LIST ="xpath://XCUIElementTypeCollectionView[@name=\"Результаты поиска\"]";

    }

    public iOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
