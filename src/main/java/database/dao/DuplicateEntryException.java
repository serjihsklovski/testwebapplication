package database.dao;

import database.DataBaseException;

public class DuplicateEntryException extends DataBaseException {

    public DuplicateEntryException(String message) {
        super(message);
    }

    public DuplicateEntryException(Throwable cause) {
        super(cause);
    }

    public DuplicateEntryException(String message, Throwable cause) {
        super(message, cause);
    }
}
