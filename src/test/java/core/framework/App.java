package core.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import core.framework.config.AppConfig;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import products.driverapp.helpers.SessionCapabilitiesHelper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class App {

    DesiredCapabilities capabilities = new DesiredCapabilities();
    private AppiumDriver driver;

    private static App instance = new App();

    private App() {
    }

    public static App getInstance() {
        return instance;
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    private void setCapabilities() {
        String platform = AppConfig.getInstance().PLATFORM_NAME_CAPABILITY();
        Log.info("Setting capabilities for " + platform);
        switch (platform) {
            case "Android":
                setAndroidCapabilities();
                break;
            case "iOS":
                setIOSCapabilities();
                break;
            default:
                setAndroidCapabilities();
                break;
        }

    }

    private void setAndroidCapabilities() {
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, AppConfig.getInstance().PLATFORM_NAME_CAPABILITY());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, AppConfig.getInstance().DEVICE_NAME_CAPABILITY());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, AppConfig.getInstance().APP_PACKAGE_CAPABILITY());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, AppConfig.getInstance().APP_ACTIVITY_CAPABILITY());
        capabilities.setCapability(MobileCapabilityType.UDID, AppConfig.getInstance().UDID_CAPABILITY());
    }

    private void setIOSCapabilities() {
    }

    private String getDeviceId() {
        Log.info("Getting connected device id");
        return driver.getCapabilities().getCapability(MobileCapabilityType.UDID).toString();
    }


    public void launch() {
        if (driver == null) {
            Log.info("Initializing Appium driver");
            Log.info("Launching app");
            driverInit();
        } else {
            Log.info("Launching app");
            driver.launchApp();
        }
    }

    public void driverInit() {
        SessionCapabilitiesHelper.getInstance().addDeviceDetatilsToCapabilities();
        setCapabilities();
        try {
            driver = new AndroidDriver(new URL(AppConfig.getInstance().APPIUM_URL()), capabilities);
        } catch (MalformedURLException e) {
            Log.error(e.getMessage());
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void close() {
        Log.info("Closing app");
        driver.closeApp();

    }

    //actions

    public void waitTillNoElementDisplayed(By by) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void clickElement(By by) {
        try {
            Log.info(String.format("Waiting for element '%s'", by));
            WebElement element = driver.findElement(by);
            Log.info(String.format("Element '%s' was located on page", by));
            element.click();
            Log.info(String.format("Element '%s' was clicked", by));
        } catch (NotFoundException e) {
            Assert.fail("The element with locator " + by.toString() + " was not found.");
            Log.error("The element with locator " + by.toString() + " was not found.");
        }
    }

    public void sendKeys(By by, String text) {
        try {
            Log.info(String.format("Waiting for element '%s'", by));
            WebElement element = driver.findElement(by);
            Log.info(String.format("Element '%s' was located on page", by));
            enterText(element, text);
            Log.info(String.format("Text %s was entered to element '%s'", text, by));
            driver.hideKeyboard();
        } catch (NotFoundException e) {
            Assert.fail("The element with locator " + by.toString() + " was not found.");
            Log.error("The element with locator " + by.toString() + " was not found.");
        }
    }

    public void checkCheckbox(By by) {
        if (!isChecked(by)) {
            clickElement(by);
        }
        Log.info(String.format("Checkbox '%s' was checked", by));

    }

    public void uncheckCheckbox(By by) {
        if (isChecked(by)) {
            clickElement(by);
        }
        Log.info(String.format("Checkbox '%s' was unchecked", by));
    }

    public void enterText(WebElement element, String text) {
        try {
            element.click();
            new ProcessBuilder(new String[]{"adb", "-s", getDeviceId(),
                    "shell", "input", "text", text})
                    .redirectErrorStream(true)
                    .start();
        } catch (IOException e) {
            Log.error("Could not enter text to element " + element.getTagName());
            Log.error(e.getMessage());
            e.printStackTrace();
            Assert.fail("Could not enter text to element " + element.getTagName());
        }
    }

    //checks
    public boolean isElementDisplayed(By by) {
        try {
            Log.info(String.format("Waiting for element '%s'", by));
            WebElement element = driver.findElement(by);
            Log.info(String.format("Element '%s' was located on page", by));
            return element.isDisplayed();
        } catch (NotFoundException e) {
            Assert.fail("The element with locator " + by.toString() + " was not found.");
            Log.error("The element with locator " + by.toString() + " was not found.");
            return false;
        }
    }

    public String getElementText(By by) {
        try {
            Log.info(String.format("Waiting for element '%s'", by));
            WebElement element = driver.findElement(by);
            Log.info(String.format("Element '%s' was located on page", by));
            return element.getText();
        } catch (NotFoundException e) {
            Assert.fail("The element with locator " + by.toString() + " was not found.");
            Log.error("The element with locator " + by.toString() + " was not found.");
            return "";
        }
    }

    public boolean isElementEnabled(By by) {
        try {
            Log.info(String.format("Waiting for element '%s'", by));
            WebElement element = driver.findElement(by);
            Log.info(String.format("Element '%s' was located on page", by));
            return element.isEnabled();
        } catch (NotFoundException e) {
            Assert.fail("The element with locator " + by.toString() + " was not found.");
            Log.error("The element with locator " + by.toString() + " was not found.");
            return false;
        }
    }

    public boolean isChecked(By by) {
        try {
            Log.info(String.format("Waiting for element '%s'", by));
            WebElement element = driver.findElement(by);
            Log.info(String.format("Element '%s' was located on page", by));
            return Boolean.valueOf(element.getAttribute("checked"));
        } catch (NotFoundException e) {
            Assert.fail("The element with locator " + by.toString() + " was not found.");
            Log.error("The element with locator " + by.toString() + " was not found.");
            return false;
        }
    }
}

