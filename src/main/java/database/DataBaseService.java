package database;

import database.dao.DaoFactory;
import database.dao.user.UserDao;
import database.dataset.user.User;
import helper.ServiceProperties;
import helper.executor.Executor;

import java.sql.SQLException;
import java.util.List;

public class DataBaseService {

    private static DataBaseService instance;

    // daos
    private UserDao userDao;

    private DataBaseService() throws DataBaseServiceException {
        try {
            userDao = DaoFactory.createDao(ServiceProperties.getInstance()
                    .getUserDaoImplementationClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new DataBaseServiceException(e);
        }
    }

    public static DataBaseService getInstance() throws DataBaseServiceException {
        if (instance == null) {
            instance = new DataBaseService();
        }

        return instance;
    }

    /**
     * Inserts a user in the `user` table, returns its id.
     *
     * @param user new user
     * @return last inserted user id
     * @throws DataBaseServiceException
     */
    public long addUser(User user) throws DataBaseServiceException {
        return Executor.execTransaction(() -> {
            userDao.createTableIfNotExists();
            return userDao.insert(user);
        });
    }

    /**
     * Returns user.
     *
     * @param id user id
     * @return user data set object
     * @throws DataBaseServiceException
     */
    public User getUser(long id) throws DataBaseServiceException {
        try {
            return userDao.get(id);
        } catch (SQLException e) {
            throw new DataBaseServiceException(e);
        }
    }

    /**
     * Returns all users from the `user` table.
     *
     * @return user list
     * @throws DataBaseServiceException
     */
    public List<User> getUserList() throws DataBaseServiceException {
        try {
            return userDao.getList();
        } catch (SQLException e) {
            throw new DataBaseServiceException(e);
        }
    }

    /**
     * Updates user's login, email and password.
     *
     * @param user
     * @return affected rows count
     * @throws DataBaseServiceException
     */
    public int updateUser(User user) throws DataBaseServiceException {
        return Executor.execTransaction(() -> userDao.update(user));
    }

    /**
     * Updates user's login.
     *
     * @param userId user's id value
     * @param userLogin new user's login value
     * @return affected rows count
     * @throws DataBaseServiceException
     */
    public int updateUserLogin(long userId, String userLogin) throws DataBaseServiceException {
        return Executor.execTransaction(() -> userDao.updateLogin(userId, userLogin));
    }

    /**
     * Updates user's email.
     *
     * @param userId user's id value
     * @param userEmail new user's email value
     * @return affected rows count
     * @throws DataBaseServiceException
     */
    public int updateUserEmail(long userId, String userEmail) throws DataBaseServiceException {
        return Executor.execTransaction(() -> userDao.updateEmail(userId, userEmail));
    }

    /**
     * Updates user's password.
     *
     * @param userId user's id value
     * @param password new user's password value
     * @return affected rows count
     * @throws DataBaseServiceException
     */
    public int updateUserPassword(long userId, String password) throws DataBaseServiceException {
        return Executor.execTransaction(() -> userDao.updatePassword(userId, password));
    }

    /**
     * Deletes a user by its id.
     *
     * @param userId user's id value
     * @return affected rows count
     * @throws DataBaseServiceException
     */
    public int deleteUser(long userId) throws DataBaseServiceException {
        return Executor.execTransaction(() -> userDao.delete(userId));
    }
}
