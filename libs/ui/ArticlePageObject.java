package libs.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
    TITLE = "//android.widget.TextView[@text=\"Java (programming language)\"]",
    TITLE2 = "//android.widget.TextView[@text=\"Appium\"]",
    FOOTER_ELEMENT = "//android.widget.TextView[@text=\"View article in browser\"]",
    SAVE_ARTICLE_BUTTON = "//android.widget.TextView[@content-desc=\"Save\"]",
    SAVE_IN_MODAL = "//android.widget.Button[@resource-id=\"org.wikipedia:id/snackbar_action\"]",
    MY_LIST_NAME_INPUT = "//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]",
    OK_BUTTON_IN_MODAL = "//android.widget.Button[@resource-id=\"android:id/button1\"]",
    NAVIGATE_BACK = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]",
    CLEAR_QUERY = "//android.widget.ImageView[@content-desc=\"Clear query\"]",
    CLICK_TO_CLOSE_KEYBORD_MP = "//android.widget.ImageView[@resource-id=\"org.wikipedia:id/view_announcement_header_image\"]",
    SEARCH_EMPTY_MESSAGE = "//android.widget.TextView[@resource-id='org.wikipedia:id/search_empty_message']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }


    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page", 15);
    }

    public WebElement waitForTitleElement(String substring) {
        return this.waitForElementPresent(By.xpath(TITLE2), "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getText();
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),
                "Cannot find rhe end of the article", 20);
    }

    public void addArticleToMyList(String folderName) {

        this.waitForElementAndClick(By.xpath(SAVE_ARTICLE_BUTTON), "save", 5);
        this.waitForElementAndClick(By.xpath(SAVE_IN_MODAL), "save2", 5 );

        this.waitForElementAndSendKeys(By.xpath(MY_LIST_NAME_INPUT),
                folderName,
                "nameList",
                5);
        this.waitForElementAndClick(By.xpath(OK_BUTTON_IN_MODAL),
                "ok list",
                5);
    }

    public void closeArticle() {

        this.waitForElementAndClick(By.xpath(NAVIGATE_BACK),
                "back",
                5);
        this.waitForElementAndClick(By.xpath(CLEAR_QUERY),
                "crest",
                5);
        this.waitForElementAndClick(By.xpath(NAVIGATE_BACK),
                "back",
                5);

        this.waitForElementAndClick(By.xpath(CLICK_TO_CLOSE_KEYBORD_MP),
                "pict",
                5);
    }

    public String getTextFromElement() {
        WebElement element  = waitForElementPresent(By.xpath(SEARCH_EMPTY_MESSAGE),
                "Cannot find search empty message", 15);
        return element.getText();
    }

    public boolean assertElementPresent() {
        WebElement element = driver.findElement(By.xpath(TITLE));
        return element.isEnabled();
    }

}
