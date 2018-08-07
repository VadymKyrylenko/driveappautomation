package products.driverapp.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src//test//resources//cucumber",
        glue = "products.driverapp.tests",
        tags = "@Authorization",
        plugin = {"pretty", "json:target/cucumber.json"})


public class TestRunner extends AbstractTestNGCucumberTests {
}
