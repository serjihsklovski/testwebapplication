package database.dao.user;

import helper.Connector;
import helper.executor.Executor;
import database.model.user.User;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public final class UserDaoMysqlImpl implements UserDao {

    @Override
    public void createTableIfNotExists() throws SQLException {
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder
                .append("CREATE TABLE IF NOT EXISTS `user` (")
                .append("`id` integer NOT null AUTO_INCREMENT, ")
                .append("`login` varchar(32) NOT null UNIQUE, ")
                .append("`email` varchar(64) NOT null UNIQUE, ")
                .append("`password` varchar(32) NOT null, ")
                .append("`role` varchar(32) NOT null, ")
                .append("PRIMARY KEY (`id`)) ")
                .append("ENGINE = InnoDB ")
                .append("DEFAULT CHARACTER SET = utf8;");

        Executor.execTransaction(() ->
                Executor.execUpdate(Connector.getConnection(), sqlBuilder.toString()));
    }

    @Override
    public void dropTableIfExists() throws SQLException {
        String sql = "DROP TABLE IF EXISTS `user`";

        Executor.execTransaction(() ->
                Executor.execUpdate(Connector.getConnection(), sql));
    }

    @Override
    public long insert(User user) throws SQLException {
        String sql = String.format(
                "INSERT INTO `user` (`login`, `email`, `password`, `role`) VALUES ('%s', '%s', '%s', '%s')",
                user.getLogin(), user.getEmail(), user.getPassword(), user.getRole());

        return Executor.execTransaction(() -> {
            user.setId(Executor.execInsert(Connector.getConnection(), sql));
            return user.getId();
        });
    }

    @Override
    public User get(long id) throws SQLException {
        String sql = String.format(
                "SELECT `id`, `login`, `email`, `password`, `role` FROM `user` WHERE `id` = %d", id);

        return Executor.execTransaction(() ->
                Executor.execQuery(Connector.getConnection(), sql, (resultSet) -> {
                    if (resultSet.next()) {
                        return new User(
                                resultSet.getLong("id"),
                                resultSet.getString("login"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("role")
                        );
                    }

                    return null;
                }));
    }

    @Override
    public List<User> getList() throws SQLException {
        String sql = "SELECT `id`, `login`, `email`, `password`, `role` FROM `user`";

        return Executor.execTransaction(() ->
                Executor.execQuery(Connector.getConnection(), sql, (resultSet) -> {
                    List<User> users = new LinkedList<>();

                    while (resultSet.next()) {
                        users.add(new User(
                                resultSet.getLong("id"),
                                resultSet.getString("login"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("role")
                        ));
                    }

                    return users;
                }));
    }

    @Override
    public User getByLogin(String login) throws SQLException {
        String sql = String.format(
                "SELECT `id`, `login`, `email`, `password`, `role` FROM `user` WHERE `login` = '%s'", login);

        return Executor.execTransaction(() ->
                Executor.execQuery(Connector.getConnection(), sql, (resultSet) -> {
                    if (resultSet.next()) {
                        return new User(
                                resultSet.getLong("id"),
                                resultSet.getString("login"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("role")
                        );
                    }

                    return null;
                }));
    }

    @Override
    public User getByEmail(String email) throws SQLException {
        String sql = String.format("SELECT `id`, `login`, `email`, `password`, `role` FROM `user` WHERE `email` = '%s'", email);

        return Executor.execTransaction(() ->
                Executor.execQuery(Connector.getConnection(), sql, (resultSet) -> {
                    if (resultSet.next()) {
                        return new User(
                                resultSet.getLong("id"),
                                resultSet.getString("login"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("role")
                        );
                    }

                    return null;
                }));
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql = String.format("UPDATE `user` SET `login` = '%s', `email` = '%s', `password` = '%s', `role` = '%s' WHERE `id` = %d",
                user.getLogin(), user.getEmail(), user.getPassword(), user.getRole(), user.getId());

        return Executor.execTransaction(() ->
                Executor.execUpdate(Connector.getConnection(), sql) != 0);
    }

    @Override
    public boolean delete(long id) throws SQLException {
        String sql = String.format("DELETE FROM `user` WHERE `id` = %d", id);

        return Executor.execTransaction(() ->
                Executor.execUpdate(Connector.getConnection(), sql) != 0);
    }

    @Override
    public boolean updateLogin(long id, String login) throws SQLException {
        String sql = String.format("UPDATE `user` SET `login` = '%s' WHERE `id` = %d", login, id);

        return Executor.execTransaction(() ->
                Executor.execUpdate(Connector.getConnection(), sql) != 0);
    }

    @Override
    public boolean updateEmail(long id, String email) throws SQLException {
        String sql = String.format("UPDATE `user` SET `email` = '%s' WHERE `id` = %d", email, id);

        return Executor.execTransaction(() ->
                Executor.execUpdate(Connector.getConnection(), sql) != 0);
    }

    @Override
    public boolean updatePassword(long id, String password) throws SQLException {
        String sql = String.format("UPDATE `user` SET `password` = '%s' WHERE `id` = %d", password, id);

        return Executor.execTransaction(() ->
                Executor.execUpdate(Connector.getConnection(), sql) != 0);
    }

    @Override
    public boolean updateRole(long id, String role) throws SQLException {
        String sql = String.format("UPDATE `user` SET `role` = '%s' WHERE `id` = %d", role, id);

        return Executor.execTransaction(() ->
                Executor.execUpdate(Connector.getConnection(), sql) != 0);
    }
}
