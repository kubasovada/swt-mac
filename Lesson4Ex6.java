import libs.CoreTestCase;
import libs.ui.ArticlePageObject;
import libs.ui.SearchPageObject;
import org.junit.Test;

public class Lesson4Ex6 extends CoreTestCase {

    @Test
    public void testAssertTitlePresent() {

        String searchLine = "JAVA";
        String articleTitle = "Java (programming language)";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.clickByArticleWithSubstring(articleTitle);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        boolean isElementPresent = articlePageObject.assertElementPresent();

        assertTrue("Cannot find title Java (programming language)",
                isElementPresent);

    }

}





