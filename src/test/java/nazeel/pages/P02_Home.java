package nazeel.pages;

import nazeel.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Page Object Model for the Home Page in the Nazeel system.
 * This class contains locators and actions related to the Home Page.
 */
public class P02_Home {
    // Locator for the "Later" button displayed on the Home Page
    private final By laterButton = By.className("n-button--primary-border");

    // Locator for the Reservations tab in the navigation menu
    private final By reservationsTab = By.cssSelector("a[href='/reservations']");

    /**
     * Gets the WebElement for the "Later" button.
     *
     * @return WebElement representing the "Later" button.
     */
    public WebElement getLaterButton() {
        return Hooks.Browser.getDriver().findElement(laterButton);
    }

    /**
     * Gets the WebElement for the Reservations tab.
     *
     * @return WebElement representing the Reservations tab.
     */
    public WebElement getReservationsTab() {
        return Hooks.Browser.getDriver().findElement(reservationsTab);
    }
}
