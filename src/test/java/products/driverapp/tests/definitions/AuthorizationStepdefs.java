package products.driverapp.tests.definitions;

import core.framework.App;
import core.framework.Log;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import products.driverapp.config.ConfigDriverApp;
import products.driverapp.pages.HomePage;
import products.driverapp.pages.LogInPage;
import products.driverapp.pages.SplashPage;
import products.driverapp.scenarioContext.ScenarioContext;

import java.net.MalformedURLException;

public class AuthorizationStepdefs {
    public ScenarioContext scenarioContext;
    static App app;
    SplashPage splashPage;
    LogInPage logInPage;
    HomePage homePage;


    public AuthorizationStepdefs(ScenarioContext scenarioContext) throws MalformedURLException {
        this.scenarioContext = scenarioContext;
        app = App.getInstance();
        splashPage = new SplashPage(app);
        logInPage = new LogInPage(app);
        homePage = new HomePage(app);
    }

    @Given("^I accept License Agreement$")
    public void iAcceptLicenseAgreement() {
        splashPage.clickAcceptLicenseAgreementButton();
    }

    @When("^I click Sign In button$")
    public void iClickSignInButton() throws Throwable {
        splashPage.clickSignInButton();
    }

    @When("^I enter \"([^\"]*)\" to user ID field$")
    public void iEnterToUserIDField(String userID) throws Throwable {
        logInPage.enterUserID(userID);
    }

    @When("^I enter \"([^\"]*)\" to password field$")
    public void iEnterToPasswordField(String password) throws Throwable {
        logInPage.enterPassword(password);
    }

    @When("^I click Log In button$")
    public void iClickLogInButton() throws Throwable {
        logInPage.clickLogInButton();
    }

    @When("^I am signing in with \"([^\"]*)\" default user ID and password$")
    public void iAmSigningInWithDefaultUserIDAndPassword(String user) throws Throwable {
        String userId = ConfigDriverApp.getInstance().getValuePipeline(user.concat(".id"));
        String password = ConfigDriverApp.getInstance().getValuePipeline(user.concat(".password"));
        logInPage.enterUserID(userId);
        logInPage.enterPassword(password);
        logInPage.clickLogInButton();
    }

    @Then("^Log In button is displayed$")
    public void logInButtonIsDisplayed() throws Throwable {
        Assert.assertTrue(logInPage.isLogInButtonDisplayed(), "Log in button is not displayed");
    }

    @Then("^Log In button is disabled$")
    public void logInButtonIsDisabled() throws Throwable {
        Assert.assertFalse(logInPage.isLogInButtonEnabled(), "Log in button is enabled");
    }

    @Then("^Remember me checkbox state is unchecked$")
    public void rememberMeCheckboxStateIsUnchecked() throws Throwable {
        Assert.assertFalse(logInPage.isRememberMeCheckboxChecked(), "Remember me checkbox is checked");
    }


    @Then("^Toast notification message is shown on Log In page: \"([^\"]*)\"$")
    public void toastNotificationMessageIsShownOnLogInPage(String message) throws Throwable {
        Assert.assertEquals(message, logInPage.getErrorMessage());
    }


    @Then("^home page is shown$")
    public void homePageIsShown() throws Throwable {
        Assert.assertEquals(homePage.expectedToolbarTitle(), homePage.getToolbarTitle(),
                "Toolbar title does not match");
    }

    @Then("^\"([^\"]*)\" button is displayed on homepage$")
    public void buttonIsDisplayedOnHomepage(String option) throws Throwable {
        Assert.assertTrue(homePage.isMenuButtonDisplayed(option), "\"%s\" button is not displayed");
    }

    @Then("^Add your units button is displayed on homepage$")
    public void addYourUnitsButtonIsDisplayedOnHomepage() throws Throwable {
        Assert.assertTrue(homePage.isAddUnitsButtonDisplayed(), "Add units button is not displayed");
    }
}
