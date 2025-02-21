package libs.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject{

    private static final String
    SEARCH_INIT_ELEMENT = "//android.widget.TextView[@text=\"Search Wikipedia\"]",
    SEARCH_INPUT = "//android.widget.EditText[@resource-id=\"org.wikipedia:id/search_src_text\"]",
    SEARCH_CANCEL_BUTTON = "//android.widget.ImageView[@content-desc=\"Clear query\"]",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text='{SUBSTRING}']",

    SEARCH_RESULT_ELEMENT = "//android.widget.ImageView[@resource-id=\"org.wikipedia:id/page_list_item_image\"]",
    SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text, 'No results']",
    SEARCH_RESULTS_LIST = "//androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/search_results_list']";

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
        this.waitForElementPresent((By.xpath(SEARCH_INIT_ELEMENT)), "Cannot find search input");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT),
                "Cannot find search input after clicking search init element");
    }
    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), searchLine, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXPath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResultXPath), "Cannot find search result with substring " + substring);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button");
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXPath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultXPath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request", 5);
        return  this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result by the request", 5);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "we supposed not to find any results");
    }

    public void waitForListResult() {
        waitForElementPresent(By.xpath(SEARCH_RESULTS_LIST),
                "Cannot find search result list", 5);
    }




}
