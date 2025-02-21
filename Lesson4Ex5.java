import libs.CoreTestCase;
import libs.ui.MainPageObject;
import org.junit.Test;

public class Lesson4Ex5 extends CoreTestCase {

    @Test
    public void testSwipeOnbording() {

        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.swipeOnbording();
        mainPageObject.swipeOnbording();
        mainPageObject.swipeOnbording();
        mainPageObject.clickAcceptButtonOnOnbording();
        boolean isElementPresent = mainPageObject.assertMainPagePresent();
        assertTrue("Cannot find main page Wikipedia",
                isElementPresent);
    }

}
