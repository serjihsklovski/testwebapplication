package database.dao;

import database.DataBaseException;

public class NoSuchEntryException extends DataBaseException {

    public NoSuchEntryException(String message) {
        super(message);
    }

    public NoSuchEntryException(Throwable cause) {
        super(cause);
    }

    public NoSuchEntryException(String message, Throwable cause) {
        super(message, cause);
    }
}
