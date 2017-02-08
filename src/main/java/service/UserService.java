package service;

import database.dao.DaoFactory;
import database.dao.user.UserDao;
import database.model.user.User;
import helper.ServiceProperties;
import helper.executor.Executor;

import java.util.List;

public class UserService {

    private static UserService instance;

    // daos
    private UserDao userDao;

    private UserService() throws ServiceException {
        try {
            userDao = DaoFactory.createDao(ServiceProperties.getInstance()
                    .getUserDaoImplementationClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new ServiceException(e);
        }
    }

    public static UserService getInstance() throws ServiceException {
        if (instance == null) {
            instance = new UserService();
        }

        return instance;
    }

    /**
     * Inserts a user in the `user` table, returns its id.
     *
     * @param user new user
     * @return last inserted user id
     * @throws ServiceException
     */
    public long addUser(User user) throws ServiceException {
        return Executor.execTransaction(() -> {
            userDao.createTableIfNotExists();
            return userDao.insert(user);
        });
    }

    /**
     * Inserts a new admin into the `user` table, returns its id.
     *
     * @param admin new admi
     * @return last inserted user id
     * @throws ServiceException
     */
    public long addAdmin(User admin) throws ServiceException {
        return Executor.execTransaction(() -> {
            userDao.createTableIfNotExists();
            admin.setRole(User.ROLE_ADMIN);
            return userDao.insert(admin);
        });
    }

    /**
     * Returns user.
     *
     * @param id user id
     * @return user data set object
     * @throws ServiceException
     */
    public User getUser(long id) throws ServiceException {
        return Executor.execTransaction(() -> userDao.get(id));
    }

    /**
     * Returns all users from the `user` table.
     *
     * @return user list
     * @throws ServiceException
     */
    public List<User> getUserList() throws ServiceException {
        return Executor.execTransaction(() -> {
            userDao.createTableIfNotExists();
            return userDao.getList();
        });
    }

    /**
     * Updates user's login, email and password.
     *
     * @param user
     * @return was the operation successful?
     * @throws ServiceException
     */
    public boolean updateUser(User user) throws ServiceException {
        return Executor.execTransaction(() -> userDao.update(user));
    }

    /**
     * Updates user's login.
     *
     * @param userId user's id value
     * @param userLogin new user's login value
     * @return was the operation successful?
     * @throws ServiceException
     */
    public boolean updateUserLogin(long userId, String userLogin) throws ServiceException {
        return Executor.execTransaction(() -> userDao.updateLogin(userId, userLogin));
    }

    /**
     * Updates user's email.
     *
     * @param userId user's id value
     * @param userEmail new user's email value
     * @return was the operation successful?
     * @throws ServiceException
     */
    public boolean updateUserEmail(long userId, String userEmail) throws ServiceException {
        return Executor.execTransaction(() -> userDao.updateEmail(userId, userEmail));
    }

    /**
     * Updates user's password.
     *
     * @param userId user's id value
     * @param password new user's password value
     * @return was the operation successful?
     * @throws ServiceException
     */
    public boolean updateUserPassword(long userId, String password) throws ServiceException {
        return Executor.execTransaction(() -> userDao.updatePassword(userId, password));
    }

    /**
     * Updates user's password.
     *
     * @param userId user's id value
     * @param role new user's role value
     * @return was the operation successful?
     * @throws ServiceException
     */
    public boolean updateUserRole(long userId, String role) throws ServiceException {
        return Executor.execTransaction(() -> userDao.updateRole(userId, role));
    }

    /**
     * Deletes a user by its id.
     *
     * @param userId user's id value
     * @return was the operation successful?
     * @throws ServiceException
     */
    public boolean deleteUser(long userId) throws ServiceException {
        return Executor.execTransaction(() -> userDao.delete(userId));
    }
}
