package database.dao.user;

import database.dao.AbstractDao;
import database.dao.DaoException;
import database.dao.DuplicateEntryException;
import database.dao.NoSuchEntryException;
import database.dataset.user.User;

import java.util.List;

/**
 * Takes needed common CRUD methods from AbstractDao by overriding.
 * Defines specific CRUD methods for User data set
 */
public interface UserDao extends AbstractDao<User> {

    @Override
    void createTableIfNotExists() throws DaoException;

    @Override
    void dropTableIfExists() throws DaoException;

    @Override
    long insert(User user) throws DaoException, DuplicateEntryException;

    @Override
    User get(long id) throws DaoException, NoSuchUserException;

    @Override
    List<User> getList() throws DaoException;

    @Override
    boolean update(User user) throws DaoException, DuplicateEntryException;

    @Override
    boolean delete(long id) throws DaoException;

    /**
     * Read-method.
     * Returns a user by its login.
     *
     * @param login user login value
     * @return user data set
     * @throws DaoException
     * @throws NoSuchEntryException
     */
    User getByLogin(String login) throws DaoException, NoSuchEntryException;

    /**
     * Update-method.
     * Sets the new login value by the user id.
     *
     * @param id user id
     * @param login new login value
     * @return was the operation successful?
     * @throws DaoException
     * @throws NoSuchEntryException
     * @throws DuplicateEntryException
     */
    boolean updateLogin(long id, String login)
            throws DaoException, NoSuchEntryException, DuplicateEntryException;

    /**
     * Update-method.
     * Sets the new email value by the user id.
     *
     * @param id user id
     * @param email new email value
     * @return was the operation successful?
     * @throws DaoException
     * @throws NoSuchEntryException
     * @throws DuplicateEntryException
     */
    boolean updateEmail(long id, String email)
            throws DaoException, NoSuchEntryException, DuplicateEntryException;

    /**
     * Update-method.
     * Sets the new password value by the user id.
     *
     * @param id user id
     * @param password new password value
     * @return was the operation successful?
     * @throws DaoException
     * @throws NoSuchUserException
     */
    boolean updatePassword(long id, String password)
            throws DaoException, NoSuchEntryException;
}
