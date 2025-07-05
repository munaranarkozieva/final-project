package com.epam.training.munara_narkozieva.tests;

import com.epam.training.munara_narkozieva.pages.LoginPage;
 import com.epam.training.munara_narkozieva.utils.WebDriverFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private final String browser = System.getProperty("browser", "chrome"); // Default to chrome


    void setUp(String browser) {
        driver = WebDriverFactory.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    void tearDown() {
        WebDriverFactory.quitDriver();
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "edge"}) // Runs on both browsers
    void testEmptyCredentials(String browser) {
        setUp(browser);

        loginPage.enterUsername("foo");
        loginPage.enterPassword("bar");
        loginPage.clearUsername();
        loginPage.clearPassword();

        driver.navigate().refresh();
        loginPage = new LoginPage(driver);

        loginPage.clickLogin();

        String err = loginPage.getErrorMessage();
        System.out.println("Error shown: [" + err + "]");
        Assertions.assertTrue(
                err.contains("Username is required"),
                "Expected 'Username is required', but got: " + err
        );

        tearDown();
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "edge"})
    void testMissingPassword(String browser) {
        setUp(browser);

        loginPage.enterPassword("bar");
        loginPage.clearPassword();

        driver.navigate().refresh();
        loginPage = new LoginPage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.clickLogin();

        String err = loginPage.getErrorMessage();
        System.out.println("Error shown: [" + err + "]");
        Assertions.assertTrue(
                err.contains("Password is required"),
                "Expected 'Password is required', but got: " + err
        );

        tearDown();
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "edge"})
    void testSuccessfulLogin(String browser) {
        setUp(browser);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");

        loginPage.clickLogin();

        Assertions.assertEquals("Swag Labs", driver.getTitle());

        tearDown();
    }
}
