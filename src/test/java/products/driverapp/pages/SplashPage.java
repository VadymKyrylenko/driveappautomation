package products.driverapp.pages;

import core.framework.App;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;

public class SplashPage extends BasePageDriverApp {

    @FindBy(id = "accept")
    private WebElement acceptLicenseButton;

    @FindBy(id = "signIn")
    private WebElement signInButton;

    @FindBy(id = "signInText")
    private WebElement signInButtonText;

    private final static String ACCEPT_LICENSE_AGREEMENT_BUTTON_ID = "accept";
    private final static String SIGN_IN_BUTTON_ID = "signIn";
    private final static String SIGN_IN_TEXT_ID = "signInText";


    @Override
    public String expectedToolbarTitle() {
        return null;
    }

    public SplashPage(App app) throws MalformedURLException {
        super(app);
    }

    //action methods

    public void clickAcceptLicenseAgreementButton() {
        app.clickElement(webElementAcceptLicenseAgreementButton());
    }

    public void clickSignInButton() {
        app.clickElement(webElementSignInButton());
    }

    //get methods

    public String getSignInButtonText() {
        return app.getElementText(webElementSignInButtonText());
    }

    //private methods
    private By webElementAcceptLicenseAgreementButton() {
        return By.id(ACCEPT_LICENSE_AGREEMENT_BUTTON_ID);
    }

    private By webElementSignInButton() {
        return By.id(SIGN_IN_BUTTON_ID);
    }

    private By webElementSignInButtonText() {
        return By.id(SIGN_IN_TEXT_ID);
    }
}
