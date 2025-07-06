package com.epam.training.munara_narkozieva.pages;

import com.epam.training.munara_narkozieva.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {

    private final By usernameInput  = By.xpath("//input[@id='user-name']");
    private final By passwordInput  = By.xpath("//input[@id='password']");
    private final By loginButton    = By.xpath("//input[@id='login-button']");
    private final By errorMessage   = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String user) {
        enterText(driver.findElement(usernameInput), user);
    }

    public void enterPassword(String pwd) {
        enterText(driver.findElement(passwordInput), pwd);
    }

    public void clearUsername() {
        driver.findElement(usernameInput).clear();
    }

    public void clearPassword() {
        driver.findElement(passwordInput).clear();
    }

    public void clickLogin() {
        clickElement(driver.findElement(loginButton));
    }

    public String getErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return getElementText(driver.findElement(errorMessage)).trim();
    }
}