package helper.executor;

import database.service.DataBaseServiceException;
import helper.Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Callable;

public class Executor {

    public static int execUpdate(Connection conn, String upd) throws SQLException {
        int affectedRowsCount;

        try (Statement stmt = conn.createStatement()) {
            affectedRowsCount = stmt.executeUpdate(upd);
        }

        return affectedRowsCount;
    }

    public static long execInsert(Connection conn, String ins) throws SQLException {
        long lastInsertedId;

        try (Statement stmt = conn.createStatement()) {
            lastInsertedId = stmt.executeLargeUpdate(ins, Statement.RETURN_GENERATED_KEYS);
        }

        return lastInsertedId;
    }

    public static <T> T execQuery(Connection conn, String query, ResultHandler<T> handler)
            throws SQLException {

        T value;

        try (Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            value = handler.handle(resultSet);
        }

        return value;
    }

    public static <T> T execTransaction(Callable<T> transactionBody) throws DataBaseServiceException {
        Connection conn = Connector.getConnection();
        T value;

        try {
            conn.setAutoCommit(false);
            value = transactionBody.call();
            conn.commit();

            return value;
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ignored) {
            }

            throw new DataBaseServiceException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception ignored) {
            }
        }
    }
}
