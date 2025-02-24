package libs.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject{

    private static final String
    SEARCH_INIT_ELEMENT = "xpath://android.widget.TextView[@text=\"Search Wikipedia\"]",
    SEARCH_INPUT = "xpath://android.widget.EditText[@resource-id=\"org.wikipedia:id/search_src_text\"]",
    SEARCH_CANCEL_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"Clear query\"]",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text='{SUBSTRING}']",

    SEARCH_RESULT_ELEMENT = "xpath://android.widget.ImageView[@resource-id=\"org.wikipedia:id/page_list_item_image\"]",
    SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text, 'No results']",
    SEARCH_RESULTS_LIST = "xpath://androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/search_results_list']";

//"Java (programming language)"

    // TEMPLATES METHODS
    public static String getResultSearchElement (String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    // TEMPLATES METHODS


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput()
    {
        this.skipOnboarding();
        this.waitForElementPresent((SEARCH_INIT_ELEMENT), "Cannot find search input");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INPUT,
                "Cannot find search input after clicking search init element");
    }
    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXPath = getResultSearchElement(substring);
        this.waitForElementPresent(searchResultXPath, "Cannot find search result with substring " + substring);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button");
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXPath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXPath, "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request", 5);
        return  this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result by the request", 5);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "we supposed not to find any results");
    }

    public void waitForListResult() {
        waitForElementPresent(SEARCH_RESULTS_LIST,
                "Cannot find search result list", 5);
    }




}
