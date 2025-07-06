package com.epam.training.munara_narkozieva.base;

import com.epam.training.munara_narkozieva.utils.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@Execution(ExecutionMode.CONCURRENT)
public class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected WebDriver driver;
    protected String browser = System.getProperty("browser", "chrome");


    @BeforeEach
    public void setUp() {
        logger.info("Initializing WebDriver for browser: {}", browser);
        driver = WebDriverFactory.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            logger.info("Quitting WebDriver");
            driver.quit();
            WebDriverFactory.quitDriver();
        }
    }

}