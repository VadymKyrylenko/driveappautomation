package core.helpers;


import core.framework.exceptions.PropertiesReaderException;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EncryptPropertiesReader {
    private final static StandardPBEStringEncryptor encryptor = createEncryptor();
    private final static String KEY = "Pa55word";

    private static EncryptPropertiesReader ourInstance = new EncryptPropertiesReader();

    public static EncryptPropertiesReader getInstance() {
        return ourInstance;
    }

    private EncryptPropertiesReader() {
    }

    private static StandardPBEStringEncryptor createEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(KEY);
        return encryptor;
    }

    public Properties getProperties(String propertyFile) {
        Properties properties = new EncryptableProperties(encryptor);
        try {
            InputStream fileInput = new FileInputStream(propertyFile);
            properties.load(fileInput);
        } catch (IOException e) {
            throw new PropertiesReaderException("Cannot load configuration file " + propertyFile + ". ", e);
        }
        return properties;
    }

    public String encryptString(String toEncrypt) {
        return encryptor.encrypt(toEncrypt);
    }
}