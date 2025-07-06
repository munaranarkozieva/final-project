package com.epam.training.munara_narkozieva.steps;

import com.epam.training.munara_narkozieva.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CucumberHooks {

    private static final Logger logger = LoggerFactory.getLogger(CucumberHooks.class);

    @Before
    public void setupBrowser() {
        String browserType = System.getProperty("browser", "chrome");
        WebDriverFactory.getDriver(browserType);
        logger.info("Started test in thread {} on browser {}", Thread.currentThread().getId(), browserType);
    }

    @After
    public void tearDownBrowser() {
        WebDriverFactory.quitDriver();
    }
}