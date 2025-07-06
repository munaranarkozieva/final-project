package com.epam.training.munara_narkozieva.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver(String browser) {
        if (driverThread.get() == null) {
            logger.info("Starting WebDriver for browser: {}", browser);

            switch (browser.toLowerCase()) {
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driverThread.set(new EdgeDriver());
                    break;
                case "chrome":
                default:
                    logger.warn("Unknown browser '{}', defaulting to Chrome", browser);
                    WebDriverManager.chromedriver().setup();
                    driverThread.set(new ChromeDriver());
                    break;
            }
        }
        return driverThread.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            logger.info("Quitting WebDriver");
            driver.quit();
            driverThread.remove();
        }
    }
}
