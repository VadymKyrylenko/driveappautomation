package core.framework.exceptions;

public class PropertiesReaderException extends RuntimeException {
    public PropertiesReaderException() {
        super();
    }

    public PropertiesReaderException(String msg) {
        super(msg);
    }

    public PropertiesReaderException(String msg, Throwable e) {
        super(msg, e);
    }
}
