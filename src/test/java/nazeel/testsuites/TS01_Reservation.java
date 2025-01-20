package nazeel.testsuites;

import nazeel.Hooks;
import nazeel.actions.A01_Login;
import nazeel.actions.A02_Home;
import nazeel.actions.A03_Reservation;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Test Suite for Reservation functionality in the Nazeel system.
 * This class contains test cases to validate reservation operations such as check-in and check-out.
 */
public class TS01_Reservation extends Hooks {

    // Action classes for performing actions on Login, Home, and Reservation pages
    private final A01_Login loginActions = new A01_Login(); // Handles login-related actions
    private final A02_Home homeActions = new A02_Home(); // Handles home page-related actions
    private final A03_Reservation reservationActions = new A03_Reservation(); // Handles reservation-related actions

    /**
     * Navigates to the login page before each test method.
     * Ensures that the browser starts at the login page before any test begins.
     */
    @BeforeMethod
    public void navigateToLogin() {
        loginActions.open(); // Opens the login page
    }

    /**
     * Test Case: Validate that the reservation can be checked out successfully.
     * <p>
     * This test performs the following steps:
     * - Logs into the system using valid credentials.
     * - Navigates to the reservations section.
     * - Performs a reservation and checks out successfully.
     * - Verifies that the success message is displayed after the check-out process.
     */
    @Test(testName = "TC01 :: Reservation CheckedOut successfully!",
            suiteName = "Reservation",
            description = "Validate that the reservation is checked-Out successfully")
    public void tc01_ValidateReservation() {
        // Formatter for date fields used during the reservation process
        DateTimeFormatter checkInOutDatesFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Step 1: Log in with valid credentials
        loginActions.enterUsername("Mahmoud Gamal") // Enter username
                .enterPassword("123456Mm&&") // Enter password
                .enterAccessCode("01373") // Enter access code
                .clickLogin(); // Submit the login form

        // Step 2: Wait for the first property to be displayed and select it
        Hooks.Browser.explicitWait(5).until(driver -> loginActions.isPropertyTestOneDisplayed());
        loginActions.clickPropertyTestOne(); // Click "Test One" property

        // Step 3: Handle the User Verification popup and navigate to the Reservations tab
        Hooks.Browser.explicitWait(10).until(driver -> homeActions.isLaterButtonDisplayed());
        Hooks.Browser.implicitWait(3); // Add implicit wait for stability
        homeActions.clickLaterButton() // Dismiss verification popup
                .selectReservationsTab(); // Navigate to the reservations page

        // Step 4: Wait for unit cards to load and hover over a random card
        Hooks.Browser.explicitWait(10).until(driver -> reservationActions.isUnitsCardsDisplayed());
        int hoveredCardNum = reservationActions.hoverRandomCard(); // Hover over a random unit card

        // Step 5: Click the "Add Reservation" button for the selected card
        Hooks.Browser.explicitWait(5).until(driver -> reservationActions.isAddReservationButtonDisplayed(hoveredCardNum));
        reservationActions.clickAddReservationButton(hoveredCardNum); // Open the reservation form

        // Step 6: Enter Check-In and Check-Out dates
        Hooks.Browser.explicitWait(5).until(driver -> reservationActions.isNewReservationPageTitleDisplayed());
        reservationActions.enterCheckInDate(LocalDate.now().minusDays(2).format(checkInOutDatesFormatter)); // Check-In: Today - 2

        Hooks.Browser.explicitWait(5).until(driver -> reservationActions.isCheckOutDateDisplayed());
        reservationActions.enterCheckOutDate(LocalDate.now().plusDays(1).format(checkInOutDatesFormatter)) // Check-Out: Today + 1
                .clickVisitPurposeDropList(); // Open Visit Purpose dropdown

        // Step 7: Select Visit Purpose and Reservation Source options
        Hooks.Browser.explicitWait(5).until(driver -> reservationActions.isVisitPurposeOptionsDisplayed());
        reservationActions.selectRandomVisitPurposeOptions(); // Select a random Visit Purpose

        reservationActions.clickReservationSourceDropList(); // Open Reservation Source dropdown
        Hooks.Browser.explicitWait(5).until(driver -> reservationActions.isReservationSourceOptionsDisplayed());
        reservationActions.selectRandomReservationSourceOptions() // Select a random Reservation Source
                .clickSelectGuestNowButton(); // Open guest selection popup

        // Step 8: Search for a guest and confirm selection
        Hooks.Browser.explicitWait(5).until(driver -> reservationActions.isNewGuestDialogDisplayed());
        reservationActions.enterSearchGuestID("123456789") // Enter Guest ID
                .clickSearchGuestButton() // Search for the guest
                .selectAndHoverFoundGuest() // Hover over the search result
                .clickConfirmSearchButton() // Confirm the selected guest
                .clickCheckInButton(); // Finalize the reservation check-in

        // Step 9: Confirm Check-In
        Hooks.Browser.explicitWait(5).until(driver -> reservationActions.isConfirmCheckInButtonDisplayed());
        reservationActions.clickConfirmCheckInButton(); // Confirm Check-In action

        Hooks.Browser.explicitWait(5).until(driver -> reservationActions.isCheckOutButtonDisplayed());
        reservationActions.clickCheckOutButton(); // Proceed to Check-Out

        // Step 10: Confirm Check-Out and complete payment
        Hooks.Browser.explicitWait(5).until(driver -> reservationActions.isConfirmCheckOutButtonDisplayed());
        reservationActions.clickConfirmCheckOutButton() // Confirm Check-Out action
                .clickReceiptVoucherButton() // Add receipt voucher
                .enterPaymentMethod("Cash"); // Select payment method as Cash

        Hooks.Browser.explicitWait(5).until(driver -> reservationActions.isSaveAndContinueButtonDisplayed());
        reservationActions.clickSaveAndContinueButton(); // Save and complete the transaction

        // Step 11: Validate the success message
        Hooks.Browser.explicitWait(5).until(driver -> reservationActions.isSuccessfulMessageDisplayed());
        Assert.assertTrue(reservationActions.getSuccessfulMessageText().toLowerCase().contains("successfully"),
                "Success message not displayed within timeout"); // Assertion for success message

        // Optional: Pause execution for observation
        try {
            Thread.sleep(Duration.ofSeconds(10)); // Pause for 10 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e); // Handle interruption exception
        }
    }
}
