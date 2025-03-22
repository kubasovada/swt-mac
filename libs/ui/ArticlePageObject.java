package libs.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import libs.Platform;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
    TITLE,
    TITLE2,
    FOOTER_ELEMENT,
    SAVE_ARTICLE_BUTTON,
    SAVE_IN_MODAL,
    MY_LIST_NAME_INPUT,
    OK_BUTTON_IN_MODAL,
    NAVIGATE_BACK,
    CLEAR_QUERY,
    CLICK_TO_CLOSE_KEYBORD_MP,
    iOS_CREATE_NEW_LIST_BUTTON,
    SEARCH_EMPTY_MESSAGE;


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
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getText();
        } else {
            return titleElement.getText();
        }

    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT,
                    "Cannot find rhe end of the article", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
            "Cannot find the end of article", 100);
        }
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

    public void closeArticleiOS() {

        this.waitForElementAndClick(NAVIGATE_BACK,
                "back",
                5);
        this.waitForElementAndClick(CLEAR_QUERY,
                "Cannot find Cancel button",
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

    public void addArticlesToMySaved(String folderNAme) {
        this.waitForElementAndClick(SAVE_ARTICLE_BUTTON, "Cannot find Save article button", 5);
        waitForElementAndClick(SAVE_IN_MODAL, "Cannot find locator in modal", 5);
        waitForElementAndClick(iOS_CREATE_NEW_LIST_BUTTON, "", 5);
        waitForElementAndSendKeys(MY_LIST_NAME_INPUT, folderNAme, "", 5);
        waitForElementAndClick(OK_BUTTON_IN_MODAL, "", 5);


    }

}
