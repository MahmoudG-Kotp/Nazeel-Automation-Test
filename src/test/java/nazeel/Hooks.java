package nazeel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.ArrayList;

/**
 * The Hooks class handles browser setup and teardown for the tests,
 * as well as providing utility methods for browser interactions.
 */
public class Hooks {

    /**
     * Runs before each test method to initialize the browser.
     */
    @BeforeTest
    public void openBrowser() {
        Browser.open();
    }

    /**
     * Runs after each test method to clean up and close the browser.
     *
     * @throws InterruptedException if interrupted during wait before quitting the browser.
     */
    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Browser.quit();
    }

    /**
     * Nested static class to manage browser-related utilities and driver instance.
     */
    public static class Browser {
        private static WebDriver mainDriver;

        /**
         * Returns the current WebDriver instance.
         *
         * @return The WebDriver instance in use.
         */
        public static WebDriver getDriver() {
            return mainDriver;
        }

        /**
         * Opens the browser, sets up WebDriver, and maximizes the window.
         */
        private static void open() {
            // Set up the WebDriver
            WebDriverManager.chromedriver().setup();
            mainDriver = new ChromeDriver();
            // Maximize the browser window
            mainDriver.manage().window().maximize();
            // Wait for the browser to be ready by ensuring a window handle exists
            explicitWait(3).until(driver -> !mainDriver.getWindowHandles().isEmpty());
        }

        /**
         * Switches to a browser window by index.
         *
         * @param windowIndex The index of the window to switch to.
         * @return The WebDriver instance focused on the desired window.
         */
        public static WebDriver switchTo(int windowIndex) {
            return mainDriver.switchTo().window(new ArrayList<>(mainDriver.getWindowHandles()).get(windowIndex));
        }

        /**
         * Sets an implicit wait for the WebDriver.
         *
         * @param seconds The duration of the implicit wait in seconds.
         */
        public static void implicitWait(int seconds) {
            mainDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        }

        /**
         * Creates and returns a WebDriverWait instance for explicit waits.
         *
         * @param seconds The duration of the explicit wait in seconds.
         * @return A WebDriverWait instance.
         */
        public static WebDriverWait explicitWait(int seconds) {
            return new WebDriverWait(mainDriver, Duration.ofSeconds(seconds));
        }

        /**
         * Closes a specific browser window by index and switches back to the previous window.
         *
         * @param windowIndex The index of the window to close.
         */
        public static void closeWindow(int windowIndex) {
            switchTo(windowIndex).close();
            switchTo(windowIndex - 1); // Switch back to the previous window
        }

        /**
         * Quits the browser after a short wait to ensure cleanup is complete.
         *
         * @throws InterruptedException if interrupted during the wait.
         */
        private static void quit() throws InterruptedException {
            Thread.sleep(2000); // Wait for 2 seconds before quitting
            mainDriver.quit();
        }
    }
}
