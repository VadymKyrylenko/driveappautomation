package products.driverapp.tests;

import core.framework.App;
import core.framework.BaseHooks;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.net.MalformedURLException;

public class Hooks {
    @After(order=1)
    public void closeApp(){
        App.getInstance().close();
    }

    @After(order=2)
    public void takeScreenshot(Scenario scenario){
        new BaseHooks().embedScreenshot(scenario);
    }

    @Before()
    public void launchApp() throws MalformedURLException {
        App.getInstance().launch();
    }

    @Before(order = 1)
    public void printScenarioName(Scenario scenario) {
        new BaseHooks().printScenarioName(scenario);
    }


}
