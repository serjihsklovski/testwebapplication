package database.dao.user;

import database.dao.DaoException;
import database.dao.DuplicateEntryException;
import database.dao.NoSuchEntryException;
import helper.Connector;
import helper.executor.Executor;
import database.dataset.user.User;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public final class MysqlUserDaoImplementation implements UserDao {

    @Override
    public void createTableIfNotExists() throws DaoException {
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder
                .append("CREATE TABLE IF NOT EXISTS `user` (")
                .append("`id` integer NOT null AUTO_INCREMENT, ")
                .append("`login` varchar(32) NOT null UNIQUE, ")
                .append("`email` varchar(64) NOT null UNIQUE, ")
                .append("`password` varchar(32) NOT null, ")
                .append("PRIMARY KEY (`id`)) ENGINE = InnoDB;");

        String sql = sqlBuilder.toString();

        try {
            Executor.execUpdate(Connector.getConnection(), sqlBuilder.toString());
        } catch (SQLException e) {
            throw new DaoException(String.format("Can't execute SQL: { %s }", sql), e);
        }
    }

    @Override
    public void dropTableIfExists() throws DaoException {
        String sql = "DROP TABLE IF EXISTS `user`";

        try {
            Executor.execUpdate(Connector.getConnection(), sql);
        } catch (SQLException e) {
            throw new DaoException(String.format("Can't execute SQL: { %s }", sql), e);
        }
    }

    @Override
    public long insert(User user) throws DaoException, DuplicateEntryException {
        String sql = String.format("INSERT INTO `user` (`login`, `email`, `password`) VALUES ('%s', '%s', '%s')",
                user.getLogin(), user.getEmail(), user.getPassword());

        try {
            user.setId(Executor.execInsert(Connector.getConnection(), sql));
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new DuplicateEntryException("Unsuccessful attempt to insert: duplicate entry", e);
            }

            throw new DaoException(String.format("Can't execute SQL: { %s }", sql), e);
        }

        return user.getId();
    }

    @Override
    public User get(long id) throws DaoException, NoSuchEntryException {
        String sql = String.format("SELECT `id`, `login`, `email`, `password` FROM `user` WHERE `id` = %d", id);
        return getUserBySomething(sql);
    }

    @Override
    public List<User> getList() throws DaoException {
        String sql = "SELECT `id`, `login`, `email`, `password` FROM `user`";

        try {
            return Executor.execQuery(Connector.getConnection(), sql, (resultSet) -> {
                List<User> users = new LinkedList<>();

                while (resultSet.next()) {
                    users.add(new User(
                            resultSet.getLong("id"),
                            resultSet.getString("login"),
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    ));
                }

                return users;
            });
        } catch (SQLException e) {
            throw new DaoException(String.format("Can't execute SQL: { %s }", sql), e);
        }
    }

    @Override
    public User getByLogin(String login) throws DaoException, NoSuchEntryException {
        String sql = String.format("SELECT `id`, `login`, `email`, `password` FROM `user` WHERE `login` = '%s'", login);
        return getUserBySomething(sql);
    }

    @Override
    public boolean update(User user) throws DaoException, DuplicateEntryException {
        String sql = String.format("UPDATE `user` SET `login` = '%s', `email` = '%s', `password` = '%s' WHERE `id` = %d",
                user.getLogin(), user.getEmail(), user.getPassword(), user.getId());
        return updateUserData(sql);
    }

    @Override
    public boolean delete(long id) throws DaoException {
        String sql = String.format("DELETE FROM `user` WHERE `id` = %d", id);

        try {
            return Executor.execUpdate(Connector.getConnection(), sql) != 0;
        } catch (SQLException e) {
            throw new DaoException(String.format("Can't execute SQL: { %s }", sql), e);
        }
    }

    @Override
    public boolean updateLogin(long id, String login)
            throws DaoException, DuplicateEntryException {

        String sql = String.format("UPDATE `user` SET `login` = '%s' WHERE `id` = %d", login, id);
        return updateUserData(sql);
    }

    @Override
    public boolean updateEmail(long id, String email)
            throws DaoException, DuplicateEntryException {

        String sql = String.format("UPDATE `user` SET `email` = '%s' WHERE `id` = %d", email, id);
        return updateUserData(sql);
    }

    @Override
    public boolean updatePassword(long id, String password) throws DaoException {
        String sql = String.format("UPDATE `user` SET `password` = '%s' WHERE `id` = %d", password, id);

        try {
            return Executor.execUpdate(Connector.getConnection(), sql) != 0;
        } catch (SQLException e) {
            throw new DaoException(String.format("Can't execute SQL: { %s }", sql), e);
        }
    }

    private boolean updateUserData(String sql)
            throws DaoException, DuplicateEntryException {

        try {
            return Executor.execUpdate(Connector.getConnection(), sql) != 0;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new DuplicateEntryException("Unsuccessful attempt to insert: duplicate entry", e);
            }

            throw new DaoException(String.format("Can't execute SQL: { %s }", sql), e);
        }
    }

    private User getUserBySomething(String sql) throws DaoException, NoSuchEntryException {
        try {
            User user = Executor.execQuery(Connector.getConnection(), sql, (resultSet) -> {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getLong("id"),
                            resultSet.getString("login"),
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    );
                }

                return null;
            });

            if (user == null) {
                throw new NoSuchEntryException("No such user entry");
            }

            return user;
        } catch (SQLException e) {
            throw new DaoException(String.format("Can't execute SQL: { %s }", sql), e);
        }
    }
}
