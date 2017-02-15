package service;

import database.factory.UserDaoFactory;
import database.dao.user.UserDao;
import database.model.user.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService instance;
    private UserDao userDao;

    private UserService() {
        userDao = UserDaoFactory.getInstance().getDao();
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
        try {
            userDao.createTableIfNotExists();
            return userDao.insert(user);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Inserts a new admin into the `user` table, returns its id.
     *
     * @param admin new admi
     * @return last inserted user id
     * @throws ServiceException
     */
    public long addAdmin(User admin) throws ServiceException {
        try {
            userDao.createTableIfNotExists();
            admin.setRole(User.ROLE_ADMIN);
            return userDao.insert(admin);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Returns user.
     *
     * @param id user id
     * @return user data set object
     * @throws ServiceException
     */
    public User getUser(long id) throws ServiceException {
        try {
            return userDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public User getUserByLogin(String login) throws ServiceException {
        try {
            return userDao.getByLogin(login);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Returns all users from the `user` table.
     *
     * @return user list
     * @throws ServiceException
     */
    public List<User> getUserList() throws ServiceException {
        try {
            userDao.createTableIfNotExists();
            return userDao.getList();
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Updates user's login, email and password.
     *
     * @param user
     * @return was the operation successful?
     * @throws ServiceException
     */
    public boolean updateUser(User user) throws ServiceException {
        try {
            userDao.createTableIfNotExists();
            return userDao.update(user);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
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
        try {
            userDao.createTableIfNotExists();
            return userDao.updateLogin(userId, userLogin);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
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
        try {
            userDao.createTableIfNotExists();
            return userDao.updateEmail(userId, userEmail);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Updates user's password.
     *
     * @param userId user's id value
     * @param userPassword new user's password value
     * @return was the operation successful?
     * @throws ServiceException
     */
    public boolean updateUserPassword(long userId, String userPassword) throws ServiceException {
        try {
            userDao.createTableIfNotExists();
            return userDao.updatePassword(userId, userPassword);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Updates user's password.
     *
     * @param userId user's id value
     * @param userRole new user's role value
     * @return was the operation successful?
     * @throws ServiceException
     */
    public boolean updateUserRole(long userId, String userRole) throws ServiceException {
        try {
            userDao.createTableIfNotExists();
            return userDao.updateRole(userId, userRole);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Deletes a user by its id.
     *
     * @param userId user's id value
     * @return was the operation successful?
     * @throws ServiceException
     */
    public boolean deleteUser(long userId) throws ServiceException {
        try {
            userDao.createTableIfNotExists();
            return userDao.delete(userId);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
}
