package core.framework;

import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Log extends Logger {

    protected Log(String name) {
        super(name);
    }

    public static ArrayList<String> sessionLog = new ArrayList<>();

    public static void info(String message) {
        sessionLog.add(message);
        String name = getCallerClassName();
        Logger log = Logger.getLogger(name);
        log.info(message);
    }

    public static void error(String message) {
        sessionLog.add(message);
        String name = getCallerClassName();
        Logger log = Logger.getLogger(name);
        log.error(message);
    }

    public static void warn(String message) {
        sessionLog.add(message);
        String name = getCallerClassName();
        Logger log = Logger.getLogger(name);
        log.warn(message);
    }

    private static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        String className = stElements[3].getClassName();
        try {
            return Class.forName(className).getName();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
