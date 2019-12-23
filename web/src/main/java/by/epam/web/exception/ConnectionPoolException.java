package by.epam.web.exception;

/**
 * The type Connection pool exception.
 */
public class ConnectionPoolException extends Exception {
    /**
     * Instantiates a new Connection pool exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
