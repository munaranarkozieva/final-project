package com.epam.training.munara_narkozieva.steps;

import com.epam.training.munara_narkozieva.pages.LoginPage;
import com.epam.training.munara_narkozieva.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginSteps {

    private static final String BASE_URL = "https://www.saucedemo.com/";

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.getDriver(browser);
        loginPage = new LoginPage(driver);
    }

    @Given("the user is on the SauceDemo login page")
    public void navigateToLoginPage() {

        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.getDriver(browser);
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
    }
    @When("the user clears the username and password fields using XPath")
    public void clearInputs() {

        loginPage.enterUsername("temp_user");
        loginPage.enterPassword("temp_pass");
        loginPage.clearUsername();
        loginPage.clearPassword();
    }

    @When("the user enters {string} in the username field using XPath")
    public void enterUsername(String username) {

        loginPage.enterUsername(username);
    }

    @When("enters {string} in the password field using XPath")
    public void enterPassword(String password) {

        loginPage.enterPassword(password);
    }

    @When("clears the password field using XPath")
    public void clearPasswordField() {

        loginPage.enterPassword("temp_pass");
        loginPage.clearPassword();
    }

    @When("clicks the login button using XPath")
    public void clickLogin() {

        loginPage.clickLogin();
    }

    @Then("an error message {string} should appear using XPath")
    public void verifyErrorMessage(String expectedMessage) {

        String errorMessage = loginPage.getErrorMessage();
        assertThat(errorMessage, containsString(expectedMessage));
    }

    @Then("the user should be redirected to the inventory page with title {string}")
    public void verifyDashboardTitle(String expectedTitle) {

        assertThat(driver.getTitle(), is(expectedTitle));
    }

    @After
    public void tearDown() {

        if (driver != null) {
            WebDriverFactory.quitDriver();
        }
    }
}