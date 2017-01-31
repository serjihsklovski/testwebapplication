package database.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

public final class DaoFactory {

    /**
     * Creates dao instances.
     *
     * @param userDaoImplementationClassName
     * @param conn
     * @param <T> data access object class
     * @return data access object instance
     * @throws Exception ClassNotFoundException and other exceptions
     *      related with Reflection
     */
    public static <T extends AbstractDao> T createDao(String userDaoImplementationClassName,
                                                      Connection conn)
            throws Exception {

        Class clazz = Class.forName(userDaoImplementationClassName);
        Constructor constructor = clazz.getConstructor(Connection.class);

        return (T) constructor.newInstance(conn);
    }
}
