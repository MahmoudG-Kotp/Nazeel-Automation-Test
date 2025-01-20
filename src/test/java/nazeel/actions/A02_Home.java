package nazeel.actions;

import nazeel.pages.P02_Home;

/**
 * Action Class for the Home Page in the Nazeel system.
 * This class contains methods to perform user actions on the Home Page.
 */
public class A02_Home {
    // Instance of the P02_Home class to access page elements
    P02_Home homePage = new P02_Home();

    /**
     * Checks if the "Later" button is displayed on the Home Page.
     *
     * @return true if the "Later" button is displayed, false otherwise.
     */
    public boolean isLaterButtonDisplayed() {
        return homePage.getLaterButton().isDisplayed();
    }

    /**
     * Clicks the "Later" button on the Home Page.
     *
     * @return The current A02_Home instance for method chaining.
     */
    public A02_Home clickLaterButton() {
        homePage.getLaterButton().click();
        return this;
    }

    /**
     * Selects the Reservations tab from the navigation menu on the Home Page.
     *
     * @return The current A02_Home instance for method chaining.
     */
    public A02_Home selectReservationsTab() {
        homePage.getReservationsTab().click();
        return this;
    }
}