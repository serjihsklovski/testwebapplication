package database.service;

import database.DataBaseException;

public class DataBaseServiceException extends DataBaseException {
    public DataBaseServiceException(Exception cause) {
        super(cause);
    }
}
