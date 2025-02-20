package src.tests;

import libs.CoreTestCase;
import libs.ui.ArticlePageObject;
import libs.ui.MyListsPageObject;
import libs.ui.NavigationUI;
import libs.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {

        String articleTitleFirst = "Java (programming language)";
        String folderName = "List";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(articleTitleFirst);


        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();

        articlePageObject.addArticleToMyList(folderName);
        articlePageObject.closeArticle();
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(folderName);
        myListsPageObject.swipeByArticleToDelete(articleTitleFirst);
    }
}
