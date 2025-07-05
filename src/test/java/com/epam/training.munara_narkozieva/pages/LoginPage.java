package com.epam.training.munara_narkozieva.pages; // Make sure this is correct

import com.epam.training.munara_narkozieva.base.BasePage; // This import is crucial for extending BasePage
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage { // This must extend BasePage

    private final By usernameInput  = By.xpath("//input[@id='user-name']");
    private final By passwordInput  = By.xpath("//input[@id='password']");
    private final By loginButton    = By.xpath("//input[@id='login-button']");
    private final By errorMessage   = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver); // Call BasePage constructor
    }

    public void enterUsername(String user) {
        enterText(driver.findElement(usernameInput), user); // Uses BasePage method
    }

    public void enterPassword(String pwd) {
        enterText(driver.findElement(passwordInput), pwd); // Uses BasePage method
    }

    public void clearUsername() {
        driver.findElement(usernameInput).clear(); // Direct Selenium clear
    }

    public void clearPassword() {
        driver.findElement(passwordInput).clear(); // Direct Selenium clear
    }

    public void clickLogin() { // This method name matches what LoginSteps expects
        clickElement(driver.findElement(loginButton)); // Uses BasePage method
    }

    public String getErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return getElementText(driver.findElement(errorMessage)).trim(); // Uses BasePage method
    }
}