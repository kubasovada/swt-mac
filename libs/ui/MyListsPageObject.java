package libs.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{
    public static final String
    MODAL = "//android.widget.Button[@resource-id=\"org.wikipedia:id/negativeButton\"]",
    FOLDER_NAME_TMPL = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text='{FOLDER_NAME}']",
    ARTICLE_TITLE_TMPL = "//*[@text='{TITLE}']";

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
        this.waitForElementAndClick(By.xpath(MODAL), "not now", 5);
        this.waitForElementAndClick(By.xpath(folderNameXPath),
                "Cannot find folder name" + folderName, 5);
    }

    public void waitForArticleToAppear(String articleTitle) {
        String articleXpath = getSavedArticleXPathByTitle(articleTitle);
        this.waitForElementPresent(By.xpath(articleXpath), "Saved article not present with title " + articleTitle, 15);

    }

    public void waitForArticleToDisappear(String articleTitle) {
        String articleXpath = getSavedArticleXPathByTitle(articleTitle);
        this.waitForElementNotPresent(By.xpath(articleXpath), "Saved article still present with title " + articleTitle, 15);

    }

    public void swipeByArticleToDelete(String articleTitle) {
        String articleXpath = getSavedArticleXPathByTitle(articleTitle);
        this.waitForArticleToAppear(articleTitle);
        this.swipeElementToLeft(By.xpath(articleXpath), "Cannot find saved article");
        this.waitForArticleToDisappear(articleTitle);
    }

}
