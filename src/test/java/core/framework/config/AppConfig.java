package core.framework.config;

public class AppConfig extends BaseConfig {
    private static final String PROPERTIES_FILE = "src/test/resources/config/capabilities.properties";
    private static AppConfig instance = init();

    private static AppConfig init() {
        AppConfig appConfig = new AppConfig();
        appConfig.initProperties(PROPERTIES_FILE);
        return appConfig;
    }

    public static AppConfig getInstance() {
        return instance;
    }

    private AppConfig() {
    }

    public final String PLATFORM_NAME_CAPABILITY() {
        return getValuePipeline("platformName");
    }

    public final String DEVICE_NAME_CAPABILITY() {
        return getValuePipeline("deviceName");
    }

    public final String UDID_CAPABILITY() {
        return getValuePipeline("udid");
    }

    public final String APP_PACKAGE_CAPABILITY() {
        return getValuePipeline("appPackage");
    }

    public final String APP_ACTIVITY_CAPABILITY() {
        return getValuePipeline("appActivity");
    }

    public final String APPIUM_URL() {
        return getValuePipeline("url");
    }

}
