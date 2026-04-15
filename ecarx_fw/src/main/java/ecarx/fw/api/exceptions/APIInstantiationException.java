package ecarx.fw.api.exceptions;

public class APIInstantiationException extends Exception {
    public APIInstantiationException() {
        super();
    }

    public APIInstantiationException(String message) {
        super(message);
    }

    public APIInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIInstantiationException(Throwable cause) {
        super(cause);
    }
}
