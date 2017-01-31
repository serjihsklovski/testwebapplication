package dao.user;

import dao.AbstractDao;
import model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Takes needed common CRUD methods from AbstractDao by overriding.
 * Defines specific CRUD methods for User data set
 */
public interface UserDao extends AbstractDao<User> {

    @Override
    long insert(User user) throws SQLException;

    @Override
    User get(long id) throws SQLException;

    @Override
    List<User> getList() throws SQLException;

    @Override
    int update(User user) throws SQLException;

    @Override
    int delete(long id) throws SQLException;

    /**
     * Update-method.
     * Sets the new login value by the user id.
     *
     * @param id user id
     * @param login new login value
     * @return affected rows count
     * @throws SQLException
     */
    int updateLogin(long id, String login) throws SQLException;

    /**
     * Update-method.
     * Sets the new email value by the user id.
     *
     * @param id user id
     * @param email new email value
     * @return affected rows count
     * @throws SQLException
     */
    int updateEmail(long id, String email) throws SQLException;

    /**
     * Update-method.
     * Sets the new password value by the user id.
     *
     * @param id user id
     * @param password new password value
     * @return affected rows count
     * @throws SQLException
     */
    int updatePassword(long id, String password) throws SQLException;
}
