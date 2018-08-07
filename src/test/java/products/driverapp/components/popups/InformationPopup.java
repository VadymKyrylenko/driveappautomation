package products.driverapp.components.popups;

import core.framework.App;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class InformationPopup {
    App app;

    private final static String MESSAGE_ID = "message";
    private final static String TITLE_ID = "alertTitle";
    private final static String OK_BUTTON_ID = "button1";

    public InformationPopup(App app) throws MalformedURLException {
        this.app = app;
    }

    //actions
    public void clickOkButton(){
        app.clickElement(webelementOkButton());
    }

    //checks
    public String getMessageText() {
        return app.getElementText(webelementMessage());
    }

    public String getTitle() {
        return app.getElementText(webelementTitle());
    }

    //private methods
    private By webelementMessage(){
        return By.id(MESSAGE_ID);
    }
    private By webelementTitle(){
        return By.id(TITLE_ID);
    }
    private By webelementOkButton(){
        return By.id(OK_BUTTON_ID);
    }
}
