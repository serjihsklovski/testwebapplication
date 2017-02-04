package service;

/**
 * All exceptions related with services,
 * must be extended by this exception class.
 */
public class ServiceException extends Exception {
    public ServiceException(Exception cause) {
        super(cause);
    }
}
