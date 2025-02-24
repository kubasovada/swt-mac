package src.tests.iOS;

import libs.ui.WelcomePageObject;
import libs.ui.iOSTestCase;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {

    @Test
    public void testPassThrowWelcome() {
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);

        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForNewWayToExploreText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForAddOrEditPreferredLangText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForLearnMoreAboutDataCollectedText();
        welcomePageObject.clickGetStartedButton();
    }
}
