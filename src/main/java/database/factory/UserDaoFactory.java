package database.factory;

import database.dao.user.UserDao;
import helper.ServiceProperties;

public final class UserDaoFactory implements DaoFactory<UserDao> {

    private static UserDaoFactory instance = new UserDaoFactory();
    private UserDao dao;

    private UserDaoFactory() {
        try {
            dao = (UserDao) Class.forName(ServiceProperties.getInstance()
                    .getUserDaoImplementationClassName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserDaoFactory getInstance() {
        return instance;
    }

    @Override
    public UserDao getDao() {
        return dao;
    }
}
