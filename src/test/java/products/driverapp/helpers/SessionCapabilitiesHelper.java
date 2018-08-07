package products.driverapp.helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class SessionCapabilitiesHelper {
    private static SessionCapabilitiesHelper instance = new SessionCapabilitiesHelper();
    private final static String PROPERTY_FILE = "src/test/resources/config/capabilities.properties";

    private SessionCapabilitiesHelper() {
    }

    public static SessionCapabilitiesHelper getInstance() {
        return instance;
    }

    private String getDeviceId() {
        String result;
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            Scanner scanner = new Scanner(process.getInputStream());
            scanner.nextLine();
            result = scanner.next();

        } catch (IOException e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    private String getDeviceName() {
        return getDetail("adb.exe -s " + getDeviceId() + " shell getprop ro.product.model");
    }


    private String getDetail(String command) {
        String result;
        try {
            Process process = Runtime.getRuntime().exec(command);
            Scanner scanner = new Scanner(process.getInputStream());
            result = scanner.next();
        } catch (IOException e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    public void addDeviceDetatilsToCapabilities() {
        Properties props = new Properties();
        try {
            FileInputStream configStream = new FileInputStream(PROPERTY_FILE);
            props.load(configStream);
            configStream.close();

            //modifies existing or adds new property
            props.setProperty("deviceName", getDeviceName());
            props.setProperty("udid", getDeviceId());

            //save modified property file
            FileOutputStream output = new FileOutputStream(PROPERTY_FILE);
            props.store(output, "This description goes to the header of a file");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
