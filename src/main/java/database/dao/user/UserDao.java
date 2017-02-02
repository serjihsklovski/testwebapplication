package database.dao.user;

import database.dao.AbstractDao;
import database.dao.DaoException;
import database.dataset.user.User;

import java.sql.SQLException;
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
    long insert(User user) throws DaoException;

    @Override
    User get(long id) throws DaoException;

    @Override
    List<User> getList() throws DaoException;

    @Override
    boolean update(User user) throws DaoException;

    @Override
    boolean delete(long id) throws DaoException;

    /**
     * Read-method.
     * Returns a user by its login.
     *
     * @param login user login value
     * @return user data set
     * @throws DaoException
     * @throws NoSuchUserException
     */
    User getByLogin(String login) throws DaoException, NoSuchUserException;

    /**
     * Update-method.
     * Sets the new login value by the user id.
     *
     * @param id user id
     * @param login new login value
     * @return was the operation successful?
     * @throws SQLException
     * @throws NoSuchUserException
     * @throws NotUniqueUserLoginException
     */
    boolean updateLogin(long id, String login)
            throws DaoException, NoSuchUserException, NotUniqueUserLoginException;

    /**
     * Update-method.
     * Sets the new email value by the user id.
     *
     * @param id user id
     * @param email new email value
     * @return was the operation successful?
     * @throws SQLException
     * @throws NoSuchUserException
     * @throws NotUniqueUserEmailException
     */
    boolean updateEmail(long id, String email)
            throws DaoException, NoSuchUserException, NotUniqueUserEmailException;

    /**
     * Update-method.
     * Sets the new password value by the user id.
     *
     * @param id user id
     * @param password new password value
     * @return was the operation successful?
     * @throws SQLException
     * @throws NoSuchUserException
     */
    boolean updatePassword(long id, String password)
            throws DaoException, NoSuchUserException;
}
