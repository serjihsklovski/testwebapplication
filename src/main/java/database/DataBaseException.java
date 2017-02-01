package database;

/**
 * All exceptions related with data base service, dao and etc,
 * must be extended by this exception class.
 */
public class DataBaseException extends Exception {

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(Throwable cause) {
        super(cause);
    }

    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
