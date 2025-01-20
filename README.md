# Nazeel Test Automation Project

This repository contains an automated test script for the Nazeel staging environment. The script automates the process of creating, checking in, and checking out a reservation using Selenium, TestNG, and Maven.

## Table of Contents
- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Scenario Details](#scenario-details)
- [Prerequisites](#prerequisites)
- [Execution Demo](#execution-demo)
- [Reports](#reports)
- [Project Structure](#project-structure)
- [License](#license)

## Project Overview

The test automation script automates the following scenario:
- Logging into the Nazeel staging environment.
- Selecting a property.
- Adding a new reservation.
- Validating the reservation process from check-in to check-out.

## Technologies Used
- **Selenium** for browser automation.
- **TestNG** for test management.
- **Maven** for project management and dependency handling.

## Scenario Details

### Steps Automated
1. Open the Nazeel Staging environment.
2. Log in with the following credentials:
   - **Username**: Mahmoud Gamal
   - **Password**: 123456Mm&&
   - **Access Code**: 01373
3. On the property selection popup, select the property "Test One."
4. If prompted with the User Verification popup, click "Later" to proceed.
5. Navigate to the **Reservation** page from the blue menu on the left.
6. In "Units View," hover over any card and click the "Add Reservation (+)" button to open the insert mode.
7. Set the check-in date to **today - 2** and the check-out date to **today**.
8. Select any option for the fields:
   - Visit Purpose
   - Reservation Source
9. Choose a guest by clicking the "Select Guest Now" button:
   - Enter **Guest ID**: 123456789 in the popup.
   - Hover over the record and confirm the selection.
10. Click "Check-In" to finalize the reservation.
11. Perform the check-out process:
    - Add a Receipt Voucher.
    - Select payment method as "Cash."
12. Validate that the reservation is successfully checked out.

## Prerequisites

- Java JDK 8 or higher installed.
- Compatible WebDriver for the chosen browser.
- IntelliJ IDEA, Eclipse, or any IDE for Java development.
- Git installed for repository cloning.

## Execution Demo

Below is a demo GIF showing the execution of the script:
![nazeel-automation-preview](https://github.com/user-attachments/assets/3093cd6f-5f8a-4c6c-9d53-7fb303ffae5d)

## Reports

TestNG generates an HTML report upon test execution. The report can be found in the `test-output` folder.

To view the report:
1. Open `test-output/index.html` in your browser.

## Project Structure

- **Pages**: Contains page objects for different sections of the application.
  - `P01_Login.java`: Defines interactions with the login page.
  - `P02_Home.java`: Contains functions to interact with the home page.
  - `P03_Reservation.java`: Contains functions to interact with the reservation page.
- **Actions**: Handles interactions with locators for specific scenarios.
  - `A01_Login.java`: Implements login functionality.
  - `A02_Home.java`: Implements property selection functionality.
  - `A03_Reservation.java`: Implements reservation management functionality.
- **Hooks**: Contains setup and teardown configurations.
  - `Hooks.java`: Manages the WebDriver lifecycle and configurations.
- **Test Suites**: Contains the test scripts for automated scenarios.
  - `TS01_Reservation.java`: Test suite for creating, checking in, and checking out reservations.
- **Configuration Files**:
  - `pom.xml`: Maven configuration file for dependency management.

## License

This project is licensed under the MIT License. 
