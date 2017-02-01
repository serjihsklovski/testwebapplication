package database.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Defines common CRUD methods.
 *
 * @param <T> using DataSet class
 */
public interface AbstractDao<T> {

    /**
     * Creates the table related with dao if it does not exist.
     *
     * @throws SQLException
     */
    default void createTableIfNotExists() throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Drops the table related with dao if it exist.
     *
     * @throws SQLException
     */
    default void dropTableIfExists() throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Create-method.
     * Inserts a data set object into a table.
     * Is unsupported by default.
     *
     * @param dataSet
     * @return id of the inserted data set object
     * @throws SQLException
     */
    default long insert(T dataSet) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Read-method.
     * Returns the data set object by id from the table.
     * Is unsupported by default.
     *
     * @param id
     * @return data set object matched with the id
     * @throws SQLException
     */
    default T get(long id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Read-method.
     * Returns a list of data set objects from the table.
     * Is unsupported by default.
     *
     * @return a list of data set objects
     * @throws SQLException
     */
    default List<T> getList() throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Update-method.
     * Updates a row in the table with assigned data set object by its id.
     * Is unsupported by default.
     *
     * @param dataSet
     * @return affected rows count
     * @throws SQLException
     */
    default int update(T dataSet) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete-method.
     * Deletes a row from the table by id.
     * Is unsupported by default.
     *
     * @param id
     * @return affected rows count
     * @throws SQLException
     */
    default int delete(long id) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
