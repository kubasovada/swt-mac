import libs.CoreTestCase;
import libs.ui.MainPageObject;
import libs.ui.WelcomePageObject;
import libs.ui.factories.WelcomePageFactory;
import org.junit.Test;

public class Lesson4Ex5 extends CoreTestCase {

    @Test
    public void testSwipeOnbording() {

        WelcomePageObject welcomePageObject = WelcomePageFactory.get(driver);
        welcomePageObject.swipeOnbording();
        welcomePageObject.swipeOnbording();
        welcomePageObject.swipeOnbording();
        welcomePageObject.clickAcceptButtonOnOnbording();

        boolean isElementPresent = welcomePageObject.assertMainPagePresent();
        assertTrue("Cannot find main page Wikipedia",
                isElementPresent);
    }

}
