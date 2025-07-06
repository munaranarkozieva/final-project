package com.epam.training.munara_narkozieva.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickElement(WebElement element) {
        element.click();
    }

    public void enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }
}