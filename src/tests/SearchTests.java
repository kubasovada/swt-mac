package src.tests;

import libs.CoreTestCase;
import libs.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch()  {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");
    }

    @Test
    public void secondTest() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }



    @Test
    public void testAmountOfNotEmptySearch() {

        String searchLine = "Linkin Park Disco";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResults  = searchPageObject.getAmountOfFoundArticles();
        assertTrue("We found too few results",
                amountOfSearchResults > 0 );
    }


    @Test
    public void testAmountOfEmptySearch() {

        String searchLine = "asdfkkajhsdgfhg";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

}
