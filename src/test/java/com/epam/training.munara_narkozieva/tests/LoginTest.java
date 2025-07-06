package com.epam.training.munara_narkozieva.tests;

import com.epam.training.munara_narkozieva.pages.LoginPage;
import com.epam.training.munara_narkozieva.utils.WebDriverFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @AfterEach
    void tearDown() {
        WebDriverFactory.quitDriver();
    }

    void initDriver(String browser) {
        driver = WebDriverFactory.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @ParameterizedTest(name = "UC-1: Empty Credentials on {0}")
    @ValueSource(strings = {"chrome", "edge"})
    void testEmptyCredentials(String browser) {
        initDriver(browser);

        loginPage.enterUsername("temp");
        loginPage.enterPassword("temp");
        loginPage.clearUsername();
        loginPage.clearPassword();

        driver.navigate().refresh();

        loginPage = new LoginPage(driver);
        loginPage.clickLogin();

        String error = loginPage.getErrorMessage();
        assertThat(error, containsString("Username is required"));
    }

    @ParameterizedTest(name = "UC-2: Missing Password on {0}")
    @ValueSource(strings = {"chrome", "edge"})
    void testMissingPassword(String browser) {
        initDriver(browser);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("temp_pass");
        loginPage.clearPassword();

        driver.navigate().refresh();

        loginPage = new LoginPage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.clickLogin();

        String error = loginPage.getErrorMessage();
        assertThat(error, containsString("Password is required"));
    }

    @ParameterizedTest(name = "UC-3: Valid Login on {0}")
    @ValueSource(strings = {"chrome", "edge"})
    void testSuccessfulLogin(String browser) {
        initDriver(browser);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        assertThat(driver.getTitle(), is("Swag Labs"));
    }
}
