# SauceDemo Test Automation Project

## Overview
This project automates the login functionality of the SauceDemo application 
using Selenium WebDriver, Maven, and JUnit. It implements Behavior-Driven Development (BDD) principles
with Cucumber and utilizes XPath for element locators. The project is designed to validate 
various login scenarios as specified in the use cases.

## Launch URL
- [SauceDemo Login Page](https://www.saucedemo.com/)

## Use Cases
### UC-1: Test Login Form with Empty Credentials
1. Type any credentials into the "Username" and "Password" fields.
2. Clear the inputs.
3. Hit the "Login" button.
4. Check for the error message: "Username is required".

### UC-2: Test Login Form with Username but No Password
1. Type any credentials in the username field.
2. Enter a password.
3. Clear the "Password" input.
4. Hit the "Login" button.
5. Check for the error message: "Password is required".

### UC-3: Test Login Form with Valid Credentials
1. Type an accepted username (e.g., `standard_user`).
2. Enter the password as `secret_sauce`.
3. Click on the "Login" button.
4. Validate the title of the dashboard: "Swag Labs".

## Project Setup
### Requirements
- Java 11 or higher
- Maven
- WebDriver for Chrome and Edge
- SLF4J with Logback for logging (optional)

# final-project
