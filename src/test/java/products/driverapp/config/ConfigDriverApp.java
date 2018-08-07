package products.driverapp.config;

import core.framework.config.BaseConfig;
import core.helpers.EncryptPropertiesReader;

public class ConfigDriverApp extends BaseConfig {
    private static final String PROPERTIES_FILE = "src/test/resources/config/driverapp.properties";

    private static final ConfigDriverApp ourInstance = init();

    private static ConfigDriverApp init() {
        ConfigDriverApp configDRM = new ConfigDriverApp();
        configDRM.initProperties(PROPERTIES_FILE);
        return configDRM;
    }

    public static ConfigDriverApp getInstance() {
        return ourInstance;
    }

    private ConfigDriverApp() {
    }

    public String getDecryptedPropertyValue(String propName) {
        String password = EncryptPropertiesReader.getInstance().
                getProperties(PROPERTIES_FILE).
                getProperty(propName);
        return password;
    }

}
