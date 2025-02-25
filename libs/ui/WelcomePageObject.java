package libs.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final String
    STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name=\"Узнать подробнее о Википедии\"]",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT = "xpath://XCUIElementTypeStaticText[@name=\"Новые способы изучения\"]",
    STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "xpath://XCUIElementTypeStaticText[@name=\"Добавить или изменить предпочтительные языки\"]",
    STEP_LEAN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://XCUIElementTypeStaticText[@name=\"Узнайте больше о нашей политике конфиденциальности и условиях использования.\"]",
    NEXT_LINK = "xpath://XCUIElementTypeStaticText[@name=\"Далее\"]",
    GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Начать\"]",
    SKIP = "xpath://XCUIElementTypeStaticText[@name='Пропустить']";


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }
    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wiki'");
}

    public void waitForNewWayToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New Way To Explore'");}

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "Cannot find 'Add or edit preferred text'");
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(STEP_LEAN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Learn more about data collected'");
    }

    public  void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click 'Next' link ", 5);
}

public void clickGetStartedButton() {
        waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click get started button", 5);
}

public void clickSkip() {
        this.waitForElementAndClick(SKIP, "Cannot find and click Skip button", 5);

}

}
