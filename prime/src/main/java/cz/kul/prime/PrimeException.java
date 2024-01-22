package cz.kul.prime;

/**
 * Main exception of the application.
 */
public class PrimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PrimeException() {
    }

    public PrimeException(String message) {
        super(message);
    }

    public PrimeException(Throwable cause) {
        super(cause);
    }

    public PrimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
