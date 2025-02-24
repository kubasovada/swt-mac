package libs.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
    TITLE = "xpath://android.widget.TextView[@text=\"Java (programming language)\"]",
    TITLE2 = "xpath://android.widget.TextView[@text=\"Appium\"]",
    FOOTER_ELEMENT = "xpath://android.widget.TextView[@text=\"View article in browser\"]",
    SAVE_ARTICLE_BUTTON = "xpath://android.widget.TextView[@content-desc=\"Save\"]",
    SAVE_IN_MODAL = "xpath://android.widget.Button[@resource-id=\"org.wikipedia:id/snackbar_action\"]",
    MY_LIST_NAME_INPUT = "xpath://android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]",
    OK_BUTTON_IN_MODAL = "xpath://android.widget.Button[@resource-id=\"android:id/button1\"]",
    NAVIGATE_BACK = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]",
    CLEAR_QUERY = "xpath://android.widget.ImageView[@content-desc=\"Clear query\"]",
    CLICK_TO_CLOSE_KEYBORD_MP = "xpath://android.widget.ImageView[@resource-id=\"org.wikipedia:id/view_announcement_header_image\"]",
    SEARCH_EMPTY_MESSAGE = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/search_empty_message']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }


    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public WebElement waitForTitleElement(String substring) {
        return this.waitForElementPresent(TITLE2, "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getText();
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT,
                "Cannot find rhe end of the article", 20);
    }

    public void addArticleToMyList(String folderName) {

        this.waitForElementAndClick(SAVE_ARTICLE_BUTTON, "save", 5);
        this.waitForElementAndClick(SAVE_IN_MODAL, "save2", 5 );

        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                folderName,
                "nameList",
                5);
        this.waitForElementAndClick(OK_BUTTON_IN_MODAL,
                "ok list",
                5);
    }

    public void closeArticle() {

        this.waitForElementAndClick(NAVIGATE_BACK,
                "back",
                5);
        this.waitForElementAndClick(CLEAR_QUERY,
                "crest",
                5);
        this.waitForElementAndClick(NAVIGATE_BACK,
                "back",
                5);

        this.waitForElementAndClick(CLICK_TO_CLOSE_KEYBORD_MP,
                "pict",
                5);
    }

    public String getTextFromElement() {
        WebElement element  = waitForElementPresent(SEARCH_EMPTY_MESSAGE,
                "Cannot find search empty message", 15);
        return element.getText();
    }

    public boolean assertElementPresent() {
        By by = this.getLocatorByString(TITLE);
        WebElement element = driver.findElement(by);
        return element.isEnabled();
    }

}
