package src.tests;

import libs.CoreTestCase;
import libs.ui.ArticlePageObject;
import libs.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String artTitle = articlePageObject.getArticleTitle();

        assertEquals("We see unexpected title",
                "Java (programming language)",
                artTitle
        );
    }


    @Test
    public void testSwipeArticle() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

}
