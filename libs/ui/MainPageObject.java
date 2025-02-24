package libs.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;
    private static String
            ONBORDING_SKIP_BUTTON = "xpath://android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]",
            ONBORDING_FOR_SWIPE = "xpath://android.widget.TextView[@resource-id=\"org.wikipedia:id/primaryTextView\"]",
            ONBORDING_ACCEPT_BUTTON = "xpath://android.widget.Button[@resource-id=\"org.wikipedia:id/acceptButton\"]",
            MAIN_PAGE = "xpath://android.widget.ImageView[@resource-id=\"org.wikipedia:id/main_toolbar_wordmark\"]";

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width /2;
        int start_y = (int)(size.height * 0.8);
        int end_y = (int)(size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }
//        protected void swipeElementToLeft(By by, String errorMessage) {
//            WebElement element = waitForElementPresent(by, errorMessage, 10);
//       int  left_x = element.getLocation().getX();
//       int right_x = left_x + element.getSize().getWidth();
//       int upper_y = element.getLocation().getY();
//       int lower_y = upper_y+ element.getSize().getHeight();
//       int middle_y = (upper_y + lower_y) /2;
//
//       TouchAction action = new TouchAction(driver);
//       action
//               .press(right_x, middle_y)
//               .waitAction(500)
//               .moveTo(left_x, middle_y)
//               .release()
//               .perform();
//
//    }



    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        By by = this.getLocatorByString(locator);
        while(driver.findElements(by).size()== 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(locator, "error", 0);
                return;
            }
            //swipeUpQuick();
            swipeElementToFooter(locator, "");
            ++alreadySwiped;
        }
    }


    public WebElement waitForElementPresent(String locator, String errorMessage) {
        return waitForElementPresent(locator, errorMessage, 5);
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeOutSeconds) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeOutInSeconds) {
        WebElement element =  waitForElementPresent(locator, errorMessage,  timeOutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeOutInSeconds) {
        WebElement element =  waitForElementPresent(locator, errorMessage,  timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeOutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeOutInSeconds) {
        WebElement element =  waitForElementPresent(locator, errorMessage,  timeOutInSeconds);
        element.clear();
        return element;
    }

    public void skipOnboarding() {
        waitForElementAndClick(ONBORDING_SKIP_BUTTON,
                "Cannot find skip button in Onboarding",
                5);
    }

    public void swipeOnbording() {
        swipeElementToLeft(ONBORDING_FOR_SWIPE,
                "Main page of Onbording not found");
    }

    public void clickAcceptButtonOnOnbording() {
        waitForElementAndClick(ONBORDING_ACCEPT_BUTTON,
                "Button Accept not found",
                10);
    }

    public boolean assertMainPagePresent() {
        WebElement element = waitForElementPresent(MAIN_PAGE, "Main page not found", 5);
        return element.isEnabled();
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(String locator, String errorMessage) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String defaultMessage = "An element " + locator + " supposed to be not present ";
            throw new AssertionError(defaultMessage + "   " + errorMessage);

        }
    }

    public String wailForElementAndGetAttribute(String locator, String attribute, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    public void swipeElementToLeft(String locator, String error_message) {

        // Находим элемент на экране, ожидая его появления в течение 10 секунд.
        WebElement element = waitForElementPresent(locator, error_message, 10);

        // Получаем координаты элемента на экране.
        Point location = element.getLocation();
        // Получаем размеры элемента (ширину и высоту).
        Dimension size = element.getSize();

        // Координата по оси X левой границы элемента.
        int left_x = location.getX();
        // Координата по оси X правой границы элемента.
        int right_x = left_x + size.getWidth();
        // Координата по оси Y верхней границы элемента.
        int upper_y = location.getY();
        // Координата по оси Y нижней границы элемента.
        int lower_y = upper_y + size.getHeight();
        // Координата по оси Y средней линии элемента.
        int middle_y = upper_y + (size.getHeight() / 2);

        // Начальная координата по оси X для свайпа (чуть левее правого края элемента).
        int start_x = right_x - 20;
        // Конечная координата по оси X для свайпа (чуть правее левого края элемента).
        int end_x = left_x + 20;
        // Начальная координата по оси Y для свайпа (по центру элемента).
        int start_y = middle_y;
        // Конечная координата по оси Y для свайпа (также по центру элемента).
        int end_y = middle_y;

        // Выполняем свайп с начальной точки до конечной с заданной продолжительностью.
        this.swipe(
                new Point(start_x, start_y),
                new Point(end_x, end_y),
                Duration.ofMillis(550)  // Устанавливаем продолжительность свайпа 550 миллисекунд.
        );
    }

    public void swipeElementToFooter(String locator, String error_message) {

        // Находим элемент на экране, ожидая его появления в течение 10 секунд.
        WebElement element = waitForElementPresent(locator, error_message, 10);

        // Получаем координаты элемента на экране.
        Point location = element.getLocation();
        // Получаем размеры элемента (ширину и высоту).
        Dimension size = element.getSize();

        // Координата по оси X левой границы элемента.
        int left_x = location.getX();
        // Координата по оси X правой границы элемента.
        int right_x = left_x + size.getWidth();
        // Координата по оси Y верхней границы элемента.
        int upper_y = location.getY();
        // Координата по оси Y нижней границы элемента.
        int lower_y = upper_y + size.getHeight();
        // Координата по оси Y средней линии элемента.
        int middle_y = upper_y + (size.getHeight() / 2);

        // Начальная координата по оси X для свайпа (чуть левее правого края элемента).
        int start_x1 = right_x - 20;
        // Конечная координата по оси X для свайпа (чуть правее левого края элемента).
        int end_x1 = left_x + 20;
        // Начальная координата по оси Y для свайпа (по центру элемента).
        int start_y1 = middle_y;
        // Конечная координата по оси Y для свайпа (также по центру элемента).
        int end_y1 = middle_y;

        int x = size.width /2;
        int start_y = (int)(size.height * 0.8);
        int end_y = (int)(size.height * 0.2);

        // Выполняем свайп с начальной точки до конечной с заданной продолжительностью.
        this.swipe(
                new Point(x, start_y),
                new Point(x, end_y),
                Duration.ofMillis(550)  // Устанавливаем продолжительность свайпа 550 миллисекунд.
        );
    }

    public void swipe(Point start, Point end, Duration duration) {

        // Создаем объект, представляющий палец для выполнения свайпа.
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        // Создаем последовательность действий для выполнения свайпа.
        Sequence swipe = new Sequence(finger, 1);

        // Добавляем действие для перемещения пальца к начальной точке.
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        // Добавляем действие для нажатия на экран в начальной точке.
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // Добавляем действие для перемещения пальца из начальной точки в конечную в течение заданного времени.
        swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        // Добавляем действие для отпускания пальца от экрана в конечной точке.
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Выполняем последовательность действий (свайп).
        this.driver.perform(Arrays.asList(swipe));
    }

    public By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        if (byType.equals("xpath")) {
            return By.xpath(locator);
        } else if (byType.equals("id")) {
            return By.id(locator);
        } else {
            throw  new IllegalArgumentException("Cannot get type of locator " + locatorWithType);
        }
    }
}
