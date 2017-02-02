package database.dao.user;

import database.DataBaseException;

public class NoSuchUserException extends DataBaseException {

    public NoSuchUserException(String message) {
        super(message);
    }

    public NoSuchUserException(Throwable cause) {
        super(cause);
    }

    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
