package core.framework;

import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;

public class BaseHooks {

    public void printScenarioName(Scenario scenario) {
        Log.info("\n");
        Log.info(String.format("SCENARIO: %s", scenario.getName()));
        Log.info("\n");
    }


    public void embedScreenshot(Scenario scenario) {
        final byte[] screenShot = App.getInstance().getDriver().getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenShot, "image/png");
    }

}
