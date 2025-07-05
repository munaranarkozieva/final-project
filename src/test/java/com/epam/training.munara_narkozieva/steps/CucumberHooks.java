package com.epam.training.munara_narkozieva.steps; // Make sure this package matches your cucumber.glue in pom.xml

import com.epam.training.munara_narkozieva.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CucumberHooks {

    private static final Logger logger = LoggerFactory.getLogger(CucumberHooks.class);

    @Before // This is io.cucumber.java.Before
    public void setupBrowser() {
        // Get the browser for the current test run from system properties
        String browserType = System.getProperty("browser", "chrome");
        WebDriverFactory.getDriver(browserType); // Pass the instance-specific browserType
        logger.info("Started test in thread {} on browser {}", Thread.currentThread().getId(), browserType);
    }

    @After // This is io.cucumber.java.After
    public void tearDownBrowser() {
        WebDriverFactory.quitDriver();
    }
}