package database.dao;

public final class DaoFactory {

    /**
     * Creates dao instances.
     *
     * @param userDaoImplementationClassName
     * @param <T> data access object class
     * @return data access object instance
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T extends AbstractDao> T createDao(String userDaoImplementationClassName)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        return (T) Class.forName(userDaoImplementationClassName).newInstance();
    }
}
