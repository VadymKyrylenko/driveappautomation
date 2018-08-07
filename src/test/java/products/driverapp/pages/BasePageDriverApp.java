package products.driverapp.pages;

import core.framework.App;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public abstract class BasePageDriverApp {
    AppiumDriver driver;
    App app;

    private final static String BACK_BUTTON_XPATH = "//*[@content-desc='Navigate up']";
    private final static String TOOLBAR_TITLE_ID = "toolbar_title";
    private final static String OVERLAY_ID = "overlay";

    public abstract String expectedToolbarTitle();

    public BasePageDriverApp(App app) throws MalformedURLException {
        this.app = app;
    }

    //actions
    public void navigateBack() {
        app.clickElement(webElementBackButton());
    }

    //checks
    public String getToolbarTitle() {
        return app.getElementText(webElementToolbarTitle());
    }

    //protected methods
    protected By webElementBackButton() {
        return By.xpath(BACK_BUTTON_XPATH);
    }

    protected By webElementToolbarTitle() {
        return By.id(TOOLBAR_TITLE_ID);
    }

    protected By webElementOverlay() {
        return By.id(OVERLAY_ID);
    }

}
