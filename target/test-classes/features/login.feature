Feature: SauceDemo Login Functionality
  As a user
  I want to log in to SauceDemo
  So that I can access the inventory page or see appropriate error messages

  Background:
    Given the user is on the SauceDemo login page

  @UC-1 @EmptyCredentials
  Scenario: Login with empty credentials
    When the user clears the username and password fields using XPath
    And clicks the login button using XPath
    Then an error message "Epic sadface: Username and password do not match any user in this service" should appear using XPath

  @UC-2 @MissingPassword
  Scenario: Login with username but no password
    When the user enters "standard_user" in the username field using XPath
    And clears the password field using XPath
    And clicks the login button using XPath
    Then an error message "Epic sadface: Username and password do not match any user in this service" should appear using XPath

  @UC-3 @ValidCredentials
  Scenario Outline: Login with valid credentials
    When the user enters "<username>" in the username field using XPath
    And enters "secret_sauce" in the password field using XPath
    And clicks the login button using XPath
    Then the user should be redirected to the inventory page with title "Swag Labs"

    Examples:
      | username       |
      | standard_user  |