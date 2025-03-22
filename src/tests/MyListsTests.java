package src.tests;

import libs.CoreTestCase;
import libs.Platform;
import libs.ui.ArticlePageObject;
import libs.ui.MyListsPageObject;
import libs.ui.NavigationUI;
import libs.ui.SearchPageObject;
import libs.ui.factories.ArticlePageObjectFactory;
import libs.ui.factories.MyListsPageObjectFactory;
import libs.ui.factories.NavigationUIFactory;
import libs.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private final static String FOLDER_NAME = "List";

    @Test
    public void testSaveFirstArticleToMyList() {

        String articleTitleFirst = "Java (programming language)";
        //String folderName = "List";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(articleTitleFirst);


        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(FOLDER_NAME);
            articlePageObject.closeArticle();

        } else {
            articlePageObject.addArticlesToMySaved(FOLDER_NAME);
            articlePageObject.closeArticleiOS();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(FOLDER_NAME);
            myListsPageObject.swipeByArticleToDelete(articleTitleFirst);
        } else {
            myListsPageObject.openFolderByNameiOS(FOLDER_NAME);
            myListsPageObject.swipeByArticleToDelete(articleTitleFirst);
         }


    }
}
