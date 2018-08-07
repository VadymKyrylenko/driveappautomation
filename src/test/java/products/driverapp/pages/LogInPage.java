package products.driverapp.pages;

import core.framework.App;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class LogInPage extends BasePageDriverApp {

    public enum RememberMeValue {
        On,
        Off
    }

    private final static String USER_ID_FIELD_ID = "login";
    private final static String PASSWORD_FIELD_ID = "password";
    private final static String REMEMBER_ME_CHECKBOX_ID = "rememberMe";
    private final static String FORGOT_LINK_ID = "forgot";
    private final static String REGISTER_LINK_ID = "register";
    private final static String LOG_IN_BUTTON_ID = "loginButton";
    private final static String ERROR_MESSAGE_LABEL_ID = "loginError";

    public final static String EXPECTED_FLEET_CREDS_POPUP_ERROR_ALERT_TITLE = "Authorization Error";
    public final static String EXPECTED_FLEET_CREDS_POPUP_ERROR_MESSAGE =
            "Your account is not authorized to access Penske Driver. Please contact support.";
    public final static String EXPECTED_INCORRECT_CREDS_MESSAGE = "Incorrect User ID or Password";

    @Override
    public String expectedToolbarTitle() {
        return null;
    }

    public LogInPage(App app) throws MalformedURLException {
        super(app);
    }

    //actions
    public void enterUserID(String userID) {
        app.sendKeys(webElementUserIDField(), userID);
    }

    public void enterPassword(String password) {
        app.sendKeys(webElementPasswordField(), password);
    }

    public void clickLogInButton() {
        app.clickElement(webElementLogInButton());
        app.waitTillNoElementDisplayed(webElementOverlay());
    }

    public void setRememberMeCheckboxValue(RememberMeValue rememberMeValue) {
        if (rememberMeValue == RememberMeValue.On) {
            app.checkCheckbox(webElementRememberMeCheckbox());
        } else if (rememberMeValue == RememberMeValue.Off) {
            app.uncheckCheckbox(webElementRememberMeCheckbox());
        }
    }

    //checks
    public boolean isLogInButtonDisplayed() {
        return app.isElementDisplayed(webElementLogInButton());
    }

    public boolean isLogInButtonEnabled() {
        return app.isElementEnabled(webElementLogInButton());
    }

    public boolean isRememberMeCheckboxChecked() {
        return app.isChecked(webElementRememberMeCheckbox());
    }

    public String getErrorMessage() {
        return app.getElementText(webElementErrorMessageLabel());
    }

    public String getEnteredUserID() {
        return app.getElementText(webElementUserIDField());
    }

    //private methods
    private By webElementUserIDField() {
        return By.id(USER_ID_FIELD_ID);
    }

    private By webElementPasswordField() {
        return By.id(PASSWORD_FIELD_ID);
    }

    private By webElementRememberMeCheckbox() {
        return By.id(REMEMBER_ME_CHECKBOX_ID);
    }

    private By webElementForgotLink() {
        return By.id(FORGOT_LINK_ID);
    }

    private By webElementRegisterLink() {
        return By.id(REGISTER_LINK_ID);
    }

    private By webElementLogInButton() {
        return By.id(LOG_IN_BUTTON_ID);
    }

    private By webElementErrorMessageLabel() {
        return By.id(ERROR_MESSAGE_LABEL_ID);
    }
}
