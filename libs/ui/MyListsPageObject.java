package libs.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{
    public static final String
    MODAL = "xpath://android.widget.Button[@resource-id=\"org.wikipedia:id/negativeButton\"]",
    FOLDER_NAME_TMPL = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text='{FOLDER_NAME}']",
    ARTICLE_TITLE_TMPL = "xpath://*[@text='{TITLE}']";

    //Java (programming language)
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getFolderXpathWithName(String folderName) {
        return FOLDER_NAME_TMPL.replace("{FOLDER_NAME}", folderName);
    }

    private static String getSavedArticleXPathByTitle (String articleTitle) {
        return ARTICLE_TITLE_TMPL.replace("{TITLE}", articleTitle);
    }


    public void openFolderByName(String folderName) {
        String folderNameXPath = getFolderXpathWithName(folderName);
        this.waitForElementAndClick(MODAL, "not now", 5);
        this.waitForElementAndClick(folderNameXPath,
                "Cannot find folder name" + folderName, 5);
    }

    public void waitForArticleToAppear(String articleTitle) {
        String articleXpath = getSavedArticleXPathByTitle(articleTitle);
        this.waitForElementPresent(articleXpath, "Saved article not present with title " + articleTitle, 15);

    }

    public void waitForArticleToDisappear(String articleTitle) {
        String articleXpath = getSavedArticleXPathByTitle(articleTitle);
        this.waitForElementNotPresent(articleXpath, "Saved article still present with title " + articleTitle, 15);

    }

    public void swipeByArticleToDelete(String articleTitle) {
        String articleXpath = getSavedArticleXPathByTitle(articleTitle);
        this.waitForArticleToAppear(articleTitle);
        this.swipeElementToLeft(articleXpath, "Cannot find saved article");
        this.waitForArticleToDisappear(articleTitle);
    }

}
