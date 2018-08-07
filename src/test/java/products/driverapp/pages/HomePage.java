package products.driverapp.pages;

import core.framework.App;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class HomePage extends BasePageDriverApp {

    private final static String TOOLBAR_TITLE = "PENSKE DRIVER";

    private final static String PROFILE_MENU_BUTTON_ID = "driversInitials";
    private final static String MENU_BUTTON_XPATH = "//*[contains(@text,'%s')]";
    private final static String ADD_UNITS_BUTTON_ID = "addMyUnit";

    @Override
    public String expectedToolbarTitle() {
        return TOOLBAR_TITLE;
    }

    public HomePage(App app) throws MalformedURLException {
        super(app);
    }

    //actions
    public void openProfileMenu() {
        app.clickElement(webElementProfileMenuButton());
    }

    //checks
    public boolean isMenuButtonDisplayed(String option) {
        return app.isElementDisplayed(webElementMenuButton(option));
    }

    public boolean isAddUnitsButtonDisplayed() {
        return app.isElementDisplayed(webElementAddUnitsButton());
    }

    //private methods
    private By webElementProfileMenuButton() {
        return By.id(PROFILE_MENU_BUTTON_ID);
    }

    private By webElementMenuButton(String option) {
        return By.xpath(String.format(MENU_BUTTON_XPATH, option));
    }

    private By webElementAddUnitsButton() {
        return By.id(ADD_UNITS_BUTTON_ID);
    }

}
