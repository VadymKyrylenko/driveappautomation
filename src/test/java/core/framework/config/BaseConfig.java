package core.framework.config;

import core.helpers.EncryptPropertiesReader;

import java.util.Properties;

public abstract class BaseConfig {
    private Properties properties;

    private String getSystemProperty(String key) {
        return System.getProperty(key);
    }

    public void initProperties(String propertiesFile) {
        if (properties == null) {
            properties = EncryptPropertiesReader.getInstance().getProperties(propertiesFile);
        }
    }

    public String getValuePipeline(String propName) {
        return getValuePipeline(propName, getPropertyValue(propName));
    }

    private String getValuePipeline(String propName, String defaultValue) {
        String systemProperty = getSystemProperty(propName);
        String localProperty = getPropertyValue(propName);
        if (systemProperty != null) {
            return systemProperty;
        } else if (localProperty != null) {
            return localProperty;
        }
        return defaultValue;
    }

    private String getPropertyValue(String key) {
        return properties.getProperty(key);
    }
}