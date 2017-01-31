package database.dao.user;

import helper.executor.Executor;
import database.dataset.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public final class MysqlUserDaoImplementation implements UserDao {

    private Connection conn;

    public MysqlUserDaoImplementation(Connection conn) {
        this.conn = conn;
    }

    @Override
    public long insert(User user) throws SQLException {
        String sql = String.format("INSERT INTO `user` (`login`, `email`, `password`) VALUES ('%s', '%s', '%s')",
                user.getLogin(), user.getEmail(), user.getPassword());

        Executor.execUpdate(conn, sql);

        return Executor.execQuery(conn, "SELECT LAST_INSERT_ID();", (resultSet) -> {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }

            return -1L;
        });
    }

    @Override
    public User get(long id) throws SQLException {
        String sql = String.format("SELECT `id`, `login`, `email`, `password` FROM `user` WHERE `id` = %d", id);

        return Executor.execQuery(conn, sql, (resultSet) -> {
            if (resultSet.next()) {
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }

            return new User(-1, "", "", "");
        });
    }

    @Override
    public List<User> getList() throws SQLException {
        String sql = "SELECT `id`, `login`, `email`, `password` FROM `user`";

        return Executor.execQuery(conn, sql, (resultSet) -> {
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
    }

    @Override
    public int update(User user) throws SQLException {
        String sql = String.format("UPDATE `user` SET `login` = '%s', `email` = '%s', `password` = '%s' WHERE `id` = %d",
                user.getLogin(), user.getEmail(), user.getPassword(), user.getId());

        return Executor.execUpdate(conn, sql);
    }

    @Override
    public int delete(long id) throws SQLException {
        String sql = String.format("DELETE FROM `user` WHERE `id` = %d", id);
        return Executor.execUpdate(conn, sql);
    }

    @Override
    public int updateLogin(long id, String login) throws SQLException {
        String sql = String.format("UPDATE `user` SET `login` = '%s' WHERE `id` = %d", login, id);
        return Executor.execUpdate(conn, sql);
    }

    @Override
    public int updateEmail(long id, String email) throws SQLException {
        String sql = String.format("UPDATE `user` SET `email` = '%s' WHERE `id` = %d", email, id);
        return Executor.execUpdate(conn, sql);
    }

    @Override
    public int updatePassword(long id, String password) throws SQLException {
        String sql = String.format("UPDATE `user` SET `password` = '%s' WHERE `id` = %d", password, id);
        return Executor.execUpdate(conn, sql);
    }
}
