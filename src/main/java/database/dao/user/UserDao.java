package database.dao.user;

import database.dao.AbstractDao;
import database.model.user.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Takes needed common CRUD methods from AbstractDao by overriding.
 * Defines specific CRUD methods for User data set
 */
public interface UserDao extends AbstractDao<User> {

    @Override
    void createTableIfNotExists() throws SQLException;

    @Override
    void dropTableIfExists() throws SQLException;

    @Override
    long insert(User user) throws SQLException;

    @Override
    User get(long id) throws SQLException;

    @Override
    List<User> getList() throws SQLException;

    @Override
    boolean update(User user) throws SQLException;

    @Override
    boolean delete(long id) throws SQLException;

    /**
     * Read-method.
     * Returns a user by its login.
     *
     * @param login user login value
     * @return user data set
     * @throws SQLException
     */
    User getByLogin(String login) throws SQLException;

    /**
     * Read-method.
     * Returns a user by its email.
     *
     * @param email user emaul value
     * @return user data set
     * @throws SQLException
     */
    User getByEmail(String email) throws SQLException;

    /**
     * Update-method.
     * Sets the new login value by the user id.
     *
     * @param id user id
     * @param login new login value
     * @return was the operation successful?
     * @throws SQLException
     */
    boolean updateLogin(long id, String login) throws SQLException;

    /**
     * Update-method.
     * Sets the new email value by the user id.
     *
     * @param id user id
     * @param email new email value
     * @return was the operation successful?
     * @throws SQLException
     */
    boolean updateEmail(long id, String email) throws SQLException;

    /**
     * Update-method.
     * Sets the new password value by the user id.
     *
     * @param id user id
     * @param password new password value
     * @return was the operation successful?
     * @throws SQLException
     */
    boolean updatePassword(long id, String password) throws SQLException;

    /**
     * Update-method.
     * Sets the new role value by the user id.
     *
     * @param id user id
     * @param role new role value
     * @return was the operation successful?
     * @throws SQLException
     */
    boolean updateRole(long id, String role) throws  SQLException;
}
