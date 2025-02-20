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

public class MainPageObject {
    protected AppiumDriver driver;
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

    public void swipeUpToFindElement(By by, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        while(driver.findElements(by).size()== 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "error", 0);
                return;
            }
            //swipeUpQuick();
            swipeElementToFooter(by, "");
            ++alreadySwiped;
        }
    }


    public WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    public WebElement waitForElementPresent(By by, String errorMessage, long timeOutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element =  waitForElementPresent(by, errorMessage,  timeOutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeOutInSeconds) {
        WebElement element =  waitForElementPresent(by, errorMessage,  timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element =  waitForElementPresent(by, errorMessage,  timeOutInSeconds);
        element.clear();
        return element;
    }

    public void skipOnboarding() {
        waitForElementAndClick(By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
                "Cannot find skip button in Onboarding",
                5);
    }

    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String errorMessage) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defaultMessage = "An element " + by.toString() + " supposed to be not present ";
            throw new AssertionError(defaultMessage + "   " + errorMessage);

        }
    }

    public String wailForElementAndGetAttribute(By by, String attribute, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    public void swipeElementToLeft(By by, String error_message) {

        // Находим элемент на экране, ожидая его появления в течение 10 секунд.
        WebElement element = waitForElementPresent(by, error_message, 10);

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

    public void swipeElementToFooter(By by, String error_message) {

        // Находим элемент на экране, ожидая его появления в течение 10 секунд.
        WebElement element = waitForElementPresent(by, error_message, 10);

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
}
