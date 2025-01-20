package nazeel.pages;

import nazeel.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Page Object Model for the Reservation Page in the Nazeel system.
 * This class contains locators and actions related to the Reservation Page.
 */
public class P03_Reservation {

    // Locator for the unit cards displayed on the reservation page
    private final By unitsCards = By.cssSelector("div[class='col-lg-12 pdr-0']>div");

    // Locator for the "Add Reservation" buttons on the unit cards
    private final By addReservationButtons = By.cssSelector("div[class = 'unit-card__action--primary us-add ng-star-inserted']");

    // Locator for the calendar buttons used for selecting dates
    private final By calendarButtons = By.cssSelector("div[class='input-group-append']");

    // Locator for the visit purpose dropdown list
    private final By visitPurposeDropList = By.cssSelector(".col-md-12>kendo-dropdownlist>span[class='k-dropdown-wrap k-state-default']");

    // Locator for the reservation source dropdown list
    private final By reservationSourceDropList = By.cssSelector(".col-md-5>kendo-dropdownlist>span[class='k-dropdown-wrap k-state-default']");

    // Locator for the visit purpose options displayed in the dropdown
    private final By visitPurposeOptions = By.cssSelector("div[class='k-list-scroller']>ul>li");

    // Locator for the page title on the new reservation page
    private final By newReservationPageTitle = By.cssSelector("h2[class = 'page-header__title ng-star-inserted']");

    // Locator for the calendar dialog for date selection
    private final By calendarDialog = By.cssSelector("div[role='dialog']");

    // Locator for the next month button in the calendar dialog
    private final By nextMonthButton = By.cssSelector(".p-datepicker-next");

    // Locator for the previous month button in the calendar dialog
    private final By prevMonthButton = By.cssSelector(".p-datepicker-prev");

    // Locator for the title label showing the calendar month and year
    private final By calendarDialogTitleLabel = By.cssSelector(".p-datepicker-title>span");

    // Locator for the days displayed in the calendar dialog
    private final By daysInCalendar = By.cssSelector("table.p-datepicker-calendar td span");

    // Locator for the options in the reservation source dropdown
    private final By reservationSourceOptions = By.cssSelector("div[class='k-list-scroller']>ul>li[role='option']");

    // Locator for the "Select Guest Now" button
    private final By selectGuestNowButton = By.cssSelector("button[class='n-button n-button--primary u-m-end-15']");

    // Locator for the search input field for guest ID
    private final By searchGuestByIDET = By.cssSelector("input[placeholder='ID Number']");

    // Locator for the search button in the guest selection dialog
    private final By searchGuestButton = By.cssSelector("button[class='button button--primary']");

    // Locator for the dialog displayed for selecting a guest
    private final By selectGuestDialog = By.cssSelector("div[role='dialog']");

    // Locator for the row displaying a found guest in the selection dialog
    private final By foundGuestRow = By.cssSelector("#guestFormDialogContainer>kendo-grid>div>kendo-grid-list>div>div>table>tbody[role='presentation']>tr[role='row']");

    // Locator for the confirm button in the search guest dialog
    private final By confirmSearchButton = By.cssSelector("button[class='n-button n-button--primary ng-star-inserted']");

    // Locator for the "Check In" button
    private final By checkInButton = By.cssSelector(".u-d-flex.u-mb-15>button.button--green-border");

    // Locator for the confirm button for check-in
    private final By confirmCheckInButton = By.cssSelector(".m-3.ng-star-inserted>button");

    // Locator for the "Check Out" button
    private final By checkOutButton = By.cssSelector(".u-d-flex.u-mb-15>button.button--danger-border");

    // Locator for the confirm button for check-out
    private final By confirmCheckOutButton = By.cssSelector("button[class='n-button n-button--primary']");

    // Locator for the receipt voucher button
    private final By receiptVoucherButton = By.cssSelector("button[class = 'k-button k-state-active k-group-start']");

    // Locator for the input field to select the payment method
    private final By paymentMethodInput = By.cssSelector("input[placeholder='Select Payment Method']");

    // Locator for the "Save and Continue" button
    private final By saveAndContinueButton = By.cssSelector("div.ng-star-inserted>button.button.button--primary.ng-star-inserted");

    // Locator for the success message displayed after a reservation is successfully made
    private final By reservationSuccessMessage = By.cssSelector("div.toast-success[style='display: block;']>div.toast-message");

    /**
     * Retrieves a list of WebElements representing the unit cards.
     *
     * @return List of WebElements for unit cards.
     */
    public List<WebElement> getUnitsCards() {
        return Hooks.Browser.getDriver().findElements(unitsCards);
    }

    /**
     * Retrieves a list of WebElements representing the "Add Reservation" buttons.
     *
     * @return List of WebElements for add reservation buttons.
     */
    public List<WebElement> getAddReservationButtons() {
        return Hooks.Browser.getDriver().findElements(addReservationButtons);
    }

    /**
     * Retrieves the WebElement for the check-in date button.
     *
     * @return WebElement for the check-in date button.
     */
    public WebElement getCheckInDateButton() {
        return Hooks.Browser.getDriver().findElements(calendarButtons).getFirst();
    }

    /**
     * Retrieves the WebElement for the check-out date button.
     *
     * @return WebElement for the check-out date button.
     */
    public WebElement getCheckOutDateButton() {
        return Hooks.Browser.getDriver().findElements(calendarButtons).get(1);
    }

    /**
     * Retrieves the WebElement for the visit purpose dropdown list.
     *
     * @return WebElement for the visit purpose dropdown list.
     */
    public WebElement getVisitPurposeDropList() {
        return Hooks.Browser.getDriver().findElement(visitPurposeDropList);
    }

    /**
     * Retrieves the WebElement for the reservation source dropdown list.
     *
     * @return WebElement for the reservation source dropdown list.
     */
    public WebElement getReservationSourceDropList() {
        return Hooks.Browser.getDriver().findElement(reservationSourceDropList);
    }

    /**
     * Retrieves a list of WebElements representing the visit purpose options in the dropdown.
     *
     * @return List of WebElements for visit purpose options.
     */
    public List<WebElement> getVisitPurposeOptions() {
        Hooks.Browser.explicitWait(5).until(ExpectedConditions.presenceOfAllElementsLocatedBy(visitPurposeOptions));
        return Hooks.Browser.getDriver().findElements(visitPurposeOptions);
    }

    /**
     * Retrieves the WebElement for the page title on the new reservation page.
     *
     * @return WebElement for the new reservation page title.
     */
    public WebElement getNewReservationPageTitle() {
        return Hooks.Browser.getDriver().findElement(newReservationPageTitle);
    }

    /**
     * Retrieves the WebElement for the calendar dialog used for date selection.
     *
     * @return WebElement for the calendar dialog.
     */
    public WebElement getCalenderDialog() {
        return Hooks.Browser.getDriver().findElement(calendarDialog);
    }

    /**
     * Retrieves the WebElement for the month title in the calendar dialog.
     *
     * @return WebElement for the calendar dialog month title.
     */
    public WebElement getCalendarDialogMonthTitle() {
        return Hooks.Browser.getDriver().findElements(calendarDialogTitleLabel).getFirst();
    }

    /**
     * Retrieves the WebElement for the year title in the calendar dialog.
     *
     * @return WebElement for the calendar dialog year title.
     */
    public WebElement getCalendarDialogYearTitle() {
        return Hooks.Browser.getDriver().findElements(calendarDialogTitleLabel).get(1);
    }

    /**
     * Retrieves the WebElement for the "Next Month" button in the calendar dialog.
     *
     * @return WebElement for the next month button.
     */
    public WebElement getNextMonthCalendarDialogButton() {
        return Hooks.Browser.getDriver().findElement(nextMonthButton);
    }

    /**
     * Retrieves the WebElement for the "Previous Month" button in the calendar dialog.
     *
     * @return WebElement for the previous month button.
     */
    public WebElement getPrevMonthCalendarDialogButton() {
        return Hooks.Browser.getDriver().findElement(prevMonthButton);
    }

    /**
     * Retrieves a list of WebElements representing the days displayed in the calendar dialog.
     *
     * @return List of WebElements for the days in the calendar.
     */
    public List<WebElement> getDaysInCalendarDialog() {
        return Hooks.Browser.getDriver().findElements(daysInCalendar);
    }

    /**
     * Retrieves a list of WebElements representing the options in the reservation source dropdown list.
     *
     * @return List of WebElements for reservation source options.
     */
    public List<WebElement> getReservationSourceOptions() {
        return Hooks.Browser.getDriver().findElements(reservationSourceOptions);
    }

    /**
     * Retrieves the WebElement for the "Select Guest Now" button.
     *
     * @return WebElement for the select guest now button.
     */
    public WebElement getSelectGuestNowButton() {
        return Hooks.Browser.getDriver().findElement(selectGuestNowButton);
    }

    /**
     * Retrieves the WebElement for the search input field for guest ID.
     *
     * @return WebElement for the search guest by ID field.
     */
    public WebElement getSearchGuestByIDET() {
        return Hooks.Browser.getDriver().findElement(searchGuestByIDET);
    }

    /**
     * Retrieves the WebElement for the search button in the guest selection dialog.
     *
     * @return WebElement for the search guest button.
     */
    public WebElement getSearchGuestButton() {
        return Hooks.Browser.getDriver().findElement(searchGuestButton);
    }

    /**
     * Retrieves the WebElement for the guest selection dialog.
     *
     * @return WebElement for the guest selection dialog.
     */
    public WebElement getSelectGuestNowDialog() {
        return Hooks.Browser.getDriver().findElement(selectGuestDialog);
    }

    /**
     * Retrieves the WebElement for the row displaying a found guest in the selection dialog.
     *
     * @return WebElement for the found guest row.
     */
    public WebElement getFoundGuestRow() {
        return Hooks.Browser.getDriver().findElement(foundGuestRow);
    }

    /**
     * Retrieves the WebElement for the confirm button in the search guest dialog.
     *
     * @return WebElement for the confirm search button.
     */
    public WebElement getConfirmSearchButton() {
        return Hooks.Browser.getDriver().findElement(confirmSearchButton);
    }

    /**
     * Retrieves the WebElement for the "Check In" button.
     *
     * @return WebElement for the check-in button.
     */
    public WebElement getCheckInButton() {
        return Hooks.Browser.getDriver().findElement(checkInButton);
    }

    /**
     * Retrieves the WebElement for the confirm button for check-in.
     *
     * @return WebElement for the confirm check-in button.
     */
    public WebElement getConfirmCheckInButton() {
        return Hooks.Browser.getDriver().findElement(confirmCheckInButton);
    }

    /**
     * Retrieves the WebElement for the "Check Out" button.
     *
     * @return WebElement for the check-out button.
     */
    public WebElement getCheckOutButton() {
        return Hooks.Browser.getDriver().findElement(checkOutButton);
    }

    /**
     * Retrieves the WebElement for the confirm button for check-out.
     *
     * @return WebElement for the confirm check-out button.
     */
    public WebElement getConfirmCheckOutButton() {
        return Hooks.Browser.getDriver().findElement(confirmCheckOutButton);
    }

    /**
     * Retrieves the WebElement for the receipt voucher button.
     *
     * @return WebElement for the receipt voucher button.
     */
    public WebElement getReceiptVoucherButton() {
        return Hooks.Browser.getDriver().findElement(receiptVoucherButton);
    }

    /**
     * Retrieves the WebElement for the input field to select the payment method.
     *
     * @return WebElement for the payment method input field.
     */
    public WebElement getPaymentMethod() {
        return Hooks.Browser.getDriver().findElement(paymentMethodInput);
    }

    /**
     * Retrieves the WebElement for the "Save and Continue" button.
     *
     * @return WebElement for the save and continue button.
     */
    public WebElement getSaveAndContinueButton() {
        return Hooks.Browser.getDriver().findElement(saveAndContinueButton);
    }

    /**
     * Retrieves the WebElement for the success message displayed after a reservation is successfully made.
     *
     * @return WebElement for the reservation success message.
     */
    public WebElement getSuccessfulMessageReservation() {
        return Hooks.Browser.getDriver().findElement(reservationSuccessMessage);
    }
}
