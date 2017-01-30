package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T> {

    protected Connection conn;

    public AbstractDao(Connection conn) {
        this.conn = conn;
    }

    public int insert(T dataSet) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public T get(long id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public List<T> getList() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int delete(long id) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
