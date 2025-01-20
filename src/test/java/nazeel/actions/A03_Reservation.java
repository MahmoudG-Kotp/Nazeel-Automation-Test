package nazeel.actions;

import nazeel.Hooks;
import nazeel.pages.P03_Reservation;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;
import java.util.Random;

/**
 * Action Class for the Reservation Page in the Nazeel system.
 * This class contains methods to perform user actions on the Reservation Page.
 */
public class A03_Reservation {
    // Instance of the P03_Reservation class to access page elements
    P03_Reservation reservationPage = new P03_Reservation();

    /**
     * Checks if the unit cards are displayed on the Reservation Page.
     *
     * @return true if unit cards are displayed, false otherwise.
     */
    public boolean isUnitsCardsDisplayed() {
        if (!reservationPage.getUnitsCards().isEmpty())
            return reservationPage.getUnitsCards().getFirst().isDisplayed();
        else
            return false;
    }

    /**
     * Hovers over a randomly selected unit card.
     *
     * @return The index of the hovered unit card.
     */
    public int hoverRandomCard() {
        // Get the list of unit cards
        List<WebElement> cards = reservationPage.getUnitsCards();

        // Check if the list is empty
        if (cards.isEmpty()) {
            throw new RuntimeException("No unit cards found to hover over.");
        }

        // Generate a random number within the range of the cards list
        int randomCardNumber = new Random().nextInt(reservationPage.getAddReservationButtons().size());

        // Get the card corresponding to the random number
        WebElement randomCard = cards.get(randomCardNumber);

        // Perform a hover action using the Actions class
        Actions actions = new Actions(Hooks.Browser.getDriver());
        actions.moveToElement(randomCard).perform();

        return randomCardNumber;
    }

    /**
     * Checks if the "Add Reservation" button for the specified card is displayed.
     *
     * @param indexOfHoveredCard The index of the card to check.
     * @return true if the button is displayed, false otherwise.
     */
    public boolean isAddReservationButtonDisplayed(int indexOfHoveredCard) {
        return reservationPage.getAddReservationButtons().get(indexOfHoveredCard).isDisplayed();
    }

    /**
     * Clicks the "Add Reservation" button for the specified card.
     *
     * @param indexOfHoveredCard The index of the card to click the button for.
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickAddReservationButton(int indexOfHoveredCard) {
        reservationPage.getAddReservationButtons().get(indexOfHoveredCard).click();
        return this;
    }

    /**
     * Clicks the "Next Month" button in the calendar dialog.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickNextMonthCalenderDialogButton() {
        reservationPage.getNextMonthCalendarDialogButton().click();
        return this;
    }

    /**
     * Clicks the "Previous Month" button in the calendar dialog.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickPrevMonthCalenderDialogButton() {
        reservationPage.getPrevMonthCalendarDialogButton().click();
        return this;
    }

    /**
     * Converts a month name to its corresponding number.
     *
     * @param monthName The name of the month.
     * @return The month number as a string.
     */
    private String convertMonthNameToNumber(String monthName) {
        return switch (monthName.toLowerCase()) {
            case "january" -> "01";
            case "february" -> "02";
            case "march" -> "03";
            case "april" -> "04";
            case "may" -> "05";
            case "june" -> "06";
            case "july" -> "07";
            case "august" -> "08";
            case "september" -> "09";
            case "october" -> "10";
            case "november" -> "11";
            case "december" -> "12";
            default -> throw new IllegalArgumentException("Invalid month name: " + monthName);
        };
    }

    /**
     * Selects a specific date from the calendar dialog.
     *
     * @param targetDate The date to select in the format "dd/MM/yyyy".
     */
    public void selectDateFromCalendar(String targetDate) {
        // Split the date into day, month, and year
        String[] dateParts = targetDate.split("/");
        String day = dateParts[0];
        String month = dateParts[1]; // Use as month number
        String year = dateParts[2];

        // Wait until the calendar is visible
        Hooks.Browser.explicitWait(2).until(driver -> reservationPage.getCalenderDialog().isDisplayed());

        // Navigate to the correct year and month
        while (true) {
            // Get the currently displayed month and year
            String displayedYear = reservationPage.getCalendarDialogYearTitle().getText();
            String displayedMonthName = reservationPage.getCalendarDialogMonthTitle().getText();
            String displayedMonth = convertMonthNameToNumber(displayedMonthName);

            // Stop if the current month and year match the target
            if (displayedYear.equals(year) && displayedMonth.equals(month)) {
                break;
            }

            // Navigate forward or backward
            if (Integer.parseInt(displayedYear) < Integer.parseInt(year) ||
                    (displayedYear.equals(year) && Integer.parseInt(displayedMonth) < Integer.parseInt(month))) {
                clickNextMonthCalenderDialogButton();
            } else {
                clickPrevMonthCalenderDialogButton();
            }
        }

        // Select the correct day
        for (WebElement dayElement : reservationPage.getDaysInCalendarDialog()) {
            if (dayElement.getText().trim().equals(day)) {
                dayElement.click();
                break;
            }
        }
    }

    /**
     * Enters a check-in date using the calendar dialog.
     *
     * @param checkInDate The check-in date in the format "dd/MM/yyyy".
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation enterCheckInDate(String checkInDate) {
        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        reservationPage.getCheckInDateButton().click(); // Open the calendar
        selectDateFromCalendar(checkInDate); // Select the desired date
        return this;
    }

    /**
     * Checks if the check-out date button is displayed.
     *
     * @return true if the check-out date button is displayed, false otherwise.
     */
    public boolean isCheckOutDateDisplayed() {
        return reservationPage.getCheckOutDateButton().isDisplayed();
    }

    /**
     * Enters a check-out date using the calendar dialog.
     *
     * @param checkOutDate The check-out date in the format "dd/MM/yyyy".
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation enterCheckOutDate(String checkOutDate) {
        reservationPage.getCheckOutDateButton().click(); // Open the calendar
        selectDateFromCalendar(checkOutDate); // Select the desired date
        return this;
    }

    /**
     * Clicks the dropdown list for selecting the visit purpose.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickVisitPurposeDropList() {
        reservationPage.getVisitPurposeDropList().click();
        return this;
    }

    /**
     * Clicks the dropdown list for selecting the reservation source.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickReservationSourceDropList() {
        reservationPage.getReservationSourceDropList().click();
        return this;
    }

    /**
     * Checks if the options for visit purpose are displayed.
     *
     * @return true if the options are displayed, false otherwise.
     */
    public boolean isVisitPurposeOptionsDisplayed() {
        return reservationPage.getVisitPurposeOptions().getFirst().isDisplayed();
    }

    /**
     * Checks if the options for reservation source are displayed.
     *
     * @return true if the options are displayed, false otherwise.
     */
    public boolean isReservationSourceOptionsDisplayed() {
        return reservationPage.getReservationSourceOptions().getFirst().isDisplayed();
    }

    /**
     * Selects a random option from the visit purpose dropdown list.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation selectRandomVisitPurposeOptions() {
        List<WebElement> visitPurposeOptions = reservationPage.getVisitPurposeOptions();
        int randomOptionNumber = new Random().nextInt(visitPurposeOptions.size());
        visitPurposeOptions.get(randomOptionNumber).click();
        return this;
    }

    /**
     * Selects a random option from the reservation source dropdown list.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation selectRandomReservationSourceOptions() {
        List<WebElement> reservationSourceOptions = reservationPage.getReservationSourceOptions();
        int randomOptionNumber = new Random().nextInt(reservationSourceOptions.size());
        reservationSourceOptions.get(randomOptionNumber).click();
        return this;
    }

    /**
     * Checks if the title of the new reservation page is displayed.
     *
     * @return true if the title is displayed, false otherwise.
     */
    public boolean isNewReservationPageTitleDisplayed() {
        return reservationPage.getNewReservationPageTitle().isDisplayed();
    }

    /**
     * Clicks the "Select Guest Now" button on the reservation page.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickSelectGuestNowButton() {
        reservationPage.getSelectGuestNowButton().click();
        return this;
    }

    /**
     * Enters the guest ID into the search field.
     *
     * @param id The guest ID to enter.
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation enterSearchGuestID(String id) {
        reservationPage.getSearchGuestByIDET().sendKeys(id);
        return this;
    }

    /**
     * Clicks the button to search for a guest by ID.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickSearchGuestButton() {
        reservationPage.getSearchGuestButton().click();
        return this;
    }

    /**
     * Checks if the new guest dialog is displayed.
     *
     * @return true if the dialog is displayed, false otherwise.
     */
    public boolean isNewGuestDialogDisplayed() {
        return reservationPage.getSelectGuestNowDialog().isDisplayed();
    }

    /**
     * Selects and hovers over a found guest in the dialog.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation selectAndHoverFoundGuest() {
        WebElement foundGuest = reservationPage.getFoundGuestRow();
        // Perform a hover action using the Actions class
        Actions actions = new Actions(Hooks.Browser.getDriver());
        actions.moveToElement(foundGuest).perform();
        foundGuest.click();
        return this;
    }

    /**
     * Clicks the confirm button in the guest search dialog.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickConfirmSearchButton() {
        reservationPage.getConfirmSearchButton().click();
        return this;
    }

    /**
     * Clicks the "Check In" button on the reservation page.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickCheckInButton() {
        reservationPage.getCheckInButton().click();
        return this;
    }

    /**
     * Checks if the confirm button for check-in is displayed.
     *
     * @return true if the button is displayed, false otherwise.
     */
    public boolean isConfirmCheckInButtonDisplayed() {
        return reservationPage.getConfirmCheckInButton().isDisplayed();
    }

    /**
     * Clicks the confirm button for check-in.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickConfirmCheckInButton() {
        reservationPage.getConfirmCheckInButton().click();
        return this;
    }

    /**
     * Checks if the button for check-out is displayed.
     *
     * @return true if the button is displayed, false otherwise.
     */
    public boolean isCheckOutButtonDisplayed() {
        return reservationPage.getCheckOutButton().isDisplayed();
    }

    /**
     * Clicks the "Check Out" button on the reservation page.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickCheckOutButton() {
        reservationPage.getCheckOutButton().click();
        return this;
    }

    /**
     * Checks if the confirm button for check-out is displayed.
     *
     * @return true if the button is displayed, false otherwise.
     */
    public boolean isConfirmCheckOutButtonDisplayed() {
        return reservationPage.getConfirmCheckOutButton().isDisplayed();
    }

    /**
     * Clicks the confirm button for check-out.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickConfirmCheckOutButton() {
        reservationPage.getConfirmCheckOutButton().click();
        return this;
    }

    /**
     * Clicks the receipt voucher button on the reservation page.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickReceiptVoucherButton() {
        reservationPage.getReceiptVoucherButton().click();
        return this;
    }

    /**
     * Enters the payment method into the appropriate input field.
     *
     * @param paymentMethod The payment method to enter.
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation enterPaymentMethod(String paymentMethod) {
        reservationPage.getPaymentMethod().sendKeys(paymentMethod + Keys.ENTER);
        return this;
    }

    /**
     * Checks if the save and continue button is displayed.
     *
     * @return true if the save and continue button is displayed, false otherwise.
     */
    public boolean isSaveAndContinueButtonDisplayed() {
        return reservationPage.getSaveAndContinueButton().isDisplayed();
    }

    /**
     * Clicks the "Save and Continue" button on the reservation page.
     *
     * @return The current A03_Reservation instance for method chaining.
     */
    public A03_Reservation clickSaveAndContinueButton() {
        reservationPage.getSaveAndContinueButton().click();
        return this;
    }

    /**
     * Checks if the success message for a successful reservation is displayed.
     *
     * @return true if the success message is displayed, false otherwise.
     */
    public boolean isSuccessfulMessageDisplayed() {
        return reservationPage.getSuccessfulMessageReservation().isDisplayed();
    }

    /**
     * Returns text of the success message for a successful reservation.
     *
     * @return text of the success message.
     */
    public String getSuccessfulMessageText() {
        return reservationPage.getSuccessfulMessageReservation().getText();
    }
}