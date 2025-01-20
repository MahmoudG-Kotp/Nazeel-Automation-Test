package nazeel.pages;

import nazeel.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Page Object Model for the Login Page in the Nazeel system.
 * This class contains locators and actions related to the Login Page.
 */
public class P01_Login {
    // URL of the login page
    public static final String URL = "https://staging.nazeel.net:9002/login";

    // Locators for the login page elements
    private final By usernameField = By.id("usern"); // Username input field
    private final By passwordField = By.id("pass"); // Password input field
    private final By accessCodeField = By.id("acc"); // Access code input field
    private final By loginButton = By.cssSelector("button[class='n-button n-button--primary n-button--full-width u-mb-24 ng-star-inserted']"); // Login button
    private final By propertyTestOneSelection = By.cssSelector("tbody[role='presentation']>tr:nth-child(2)");

    /**
     * Gets the WebElement for the username input field.
     *
     * @return WebElement representing the username input field.
     */
    public WebElement getUsernameET() {
        return Hooks.Browser.getDriver().findElement(usernameField);
    }

    /**
     * Gets the WebElement for the password input field.
     *
     * @return WebElement representing the password input field.
     */
    public WebElement getPasswordET() {
        return Hooks.Browser.getDriver().findElement(passwordField);
    }

    /**
     * Gets the WebElement for the access code input field.
     *
     * @return WebElement representing the access code input field.
     */
    public WebElement getAccessCodeET() {
        return Hooks.Browser.getDriver().findElement(accessCodeField);
    }

    /**
     * Gets the WebElement for the login button.
     *
     * @return WebElement representing the login button.
     */
    public WebElement getLoginButton() {
        return Hooks.Browser.getDriver().findElement(loginButton);
    }

    /**
     * Gets the WebElement for the property test one selection.
     *
     * @return WebElement representing the property test one selection.
     */
    public WebElement getPropertyTestOne() {
        return Hooks.Browser.getDriver().findElement(propertyTestOneSelection);
    }
}
