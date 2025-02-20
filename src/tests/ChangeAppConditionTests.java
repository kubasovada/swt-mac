package src.tests;

import libs.CoreTestCase;
import libs.ui.ArticlePageObject;
import libs.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {

        String articleTitleFirst = "Java (programming language)";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(articleTitleFirst);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String titleBeforeRotation = articlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String titleAfterRotation = articlePageObject.getArticleTitle();
        Assert.assertEquals("Article title changed after screen rotation",
                titleBeforeRotation, titleAfterRotation);
        this.rotateScreenPortrait();
        String titleAfterSecondRotation = articlePageObject.getArticleTitle();
        Assert.assertEquals(titleBeforeRotation, titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {

        String articleTitleFirst = "Java (programming language)";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult(articleTitleFirst);
        this.backgroundApp(2);
        searchPageObject.waitForSearchResult(articleTitleFirst);
    }
}
