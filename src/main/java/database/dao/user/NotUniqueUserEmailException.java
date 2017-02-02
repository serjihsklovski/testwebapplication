package database.dao.user;

import database.DataBaseException;

public class NotUniqueUserEmailException extends DataBaseException {

    public NotUniqueUserEmailException(String message) {
        super(message);
    }

    public NotUniqueUserEmailException(Throwable cause) {
        super(cause);
    }

    public NotUniqueUserEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
