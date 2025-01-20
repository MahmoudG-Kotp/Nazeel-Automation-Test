package nazeel.actions;

import nazeel.Hooks;
import nazeel.pages.P01_Login;

/**
 * Action Class for the Login Page in the Nazeel system.
 * This class contains methods to perform user actions on the Login Page.
 */
public class A01_Login {
    // Instance of the P01_Login class to access page elements
    P01_Login loginPage = new P01_Login();

    /**
     * Enters the username in the username field.
     *
     * @param username The username to enter.
     * @return The current A01_Login instance for method chaining.
     */
    public A01_Login enterUsername(String username) {
        loginPage.getUsernameET().sendKeys(username);
        return this;
    }

    /**
     * Enters the password in the password field.
     *
     * @param password The password to enter.
     * @return The current A01_Login instance for method chaining.
     */
    public A01_Login enterPassword(String password) {
        loginPage.getPasswordET().sendKeys(password);
        return this;
    }

    /**
     * Enters the access code in the access code field.
     *
     * @param accessCode The access code to enter.
     * @return The current A01_Login instance for method chaining.
     */
    public A01_Login enterAccessCode(String accessCode) {
        loginPage.getAccessCodeET().sendKeys(accessCode);
        return this;
    }

    /**
     * Clicks the login button to submit the login form.
     *
     * @return The current A01_Login instance for method chaining.
     */
    public A01_Login clickLogin() {
        loginPage.getLoginButton().click();
        return this;
    }

    /**
     * Opens the login page by navigating to its URL.
     */
    public void open() {
        Hooks.Browser.getDriver().get(P01_Login.URL);
    }

    /**
     * Checks if the first property test element is displayed on the page.
     *
     * @return true if the property test element is displayed, false otherwise.
     */
    public boolean isPropertyTestOneDisplayed() {
        return loginPage.getPropertyTestOne().isDisplayed();
    }

    /**
     * Clicks on the first property test element on the page.
     */
    public void clickPropertyTestOne() {
        loginPage.getPropertyTestOne().click();
    }
}