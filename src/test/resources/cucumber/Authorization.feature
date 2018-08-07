@AuthorizationDriverApp
Feature: Authorization Driver app
  As a PO
  I want only users that are registered to use my app

  Background:
    Given I accept License Agreement
    When I click Sign In button

  @Authorization
  Scenario: As a driver I can navigate to log in page after accepting license agreement
    Then Log In button is displayed
    And Log In button is disabled
    And Remember me checkbox state is unchecked

  @Authorization
  Scenario Outline:Log In button is disabled until user Id and password of valid length are entered
    When I enter "<userId>" to user ID field
    And I enter "<password>" to password field
    Then Log In button is disabled
    Examples:
      | userId | password |
      | us     | password |
      | us     | pass     |
      | user   | pass     |

  @Authorization
  Scenario: As a driver I can not be signed in to Driver app with incorrect login/password (Negative)
    When I enter "incorrect" to user ID field
    And I enter "incorrect" to password field
    And I click Log In button
    Then Toast notification message is shown on Log In page: "Incorrect User ID or Password"

  @Authorization
  @AuthorizationSuccess
  Scenario: As a driver I can be signed in to driver app
    When I am signing in with "driverapp.user.no.hos" default user ID and password
    Then home page is shown
    And "LOCATIONS" button is displayed on homepage
    And "FUEL RECEIPTS" button is displayed on homepage
    And "24/7 ROADSIDE" button is displayed on homepage
    And "HOURS OF SERVICE" button is displayed on homepage
    And Add your units button is displayed on homepage
