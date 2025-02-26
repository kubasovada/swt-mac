import libs.CoreTestCase;
import libs.ui.ArticlePageObject;
import libs.ui.SearchPageObject;
import org.junit.Test;


public class Lesson3Ex3 extends CoreTestCase {

    @Test
    public void testCheckTitle() {

        String searchLine = "Java";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForListResult();
        searchPageObject.clickCancelSearch();
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String actual = articlePageObject.getTextFromElement();
        assertEquals("Search empty message is incorrect",
                "Search Wikipedia in more languages",
                actual);
    }

}
