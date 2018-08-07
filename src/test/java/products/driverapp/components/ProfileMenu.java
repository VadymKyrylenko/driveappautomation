package products.driverapp.components;

import core.framework.App;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class ProfileMenu {
    App app;

    private final static String LOG_OUT_BUTTON_ID = "logout";

    public ProfileMenu(App app) throws MalformedURLException {
        this.app = app;
    }

    //actions
    public void clicklogOutButton() {
        app.clickElement(webElementLogOutButton());
    }

    //private methods
    private By webElementLogOutButton() {
        return By.id(LOG_OUT_BUTTON_ID);
    }

}
