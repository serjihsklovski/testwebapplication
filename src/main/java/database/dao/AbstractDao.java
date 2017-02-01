package database.dao;

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
     * @throws DaoException
     */
    default void createTableIfNotExists() throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * Drops the table related with dao if it exist.
     *
     * @throws DaoException
     */
    default void dropTableIfExists() throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * Create-method.
     * Inserts a data set object into a table.
     * Is unsupported by default.
     *
     * @param dataSet
     * @return id of the inserted data set object
     * @throws DaoException
     */
    default long insert(T dataSet) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * Read-method.
     * Returns the data set object by id from the table.
     * Is unsupported by default.
     *
     * @param id
     * @return data set object matched with the id
     * @throws DaoException
     */
    default T get(long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * Read-method.
     * Returns a list of data set objects from the table.
     * Is unsupported by default.
     *
     * @return a list of data set objects
     * @throws DaoException
     */
    default List<T> getList() throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * Update-method.
     * Updates a row in the table with assigned data set object by its id.
     * Is unsupported by default.
     *
     * @param dataSet
     * @return was the operation successful?
     * @throws DaoException
     */
    default boolean update(T dataSet) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete-method.
     * Deletes a row from the table by id.
     * Is unsupported by default.
     *
     * @param id
     * @return was the operation successful?
     * @throws DaoException
     */
    default boolean delete(long id) throws DaoException {
        throw new UnsupportedOperationException();
    }
}
