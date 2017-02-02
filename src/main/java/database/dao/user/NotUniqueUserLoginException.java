package database.dao.user;

import database.DataBaseException;

public class NotUniqueUserLoginException extends DataBaseException {

    public NotUniqueUserLoginException(String message) {
        super(message);
    }

    public NotUniqueUserLoginException(Throwable cause) {
        super(cause);
    }

    public NotUniqueUserLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
