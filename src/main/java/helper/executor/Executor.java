package helper.executor;

import helper.Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Executor {

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

    public static <T> T execTransaction(TransactionBody<T> transactionBody) throws SQLException {
        T value;

        try (Connection conn = Connector.getConnection()) {
            try {
                conn.setAutoCommit(false);
                value = transactionBody.call();
                conn.commit();

                return value;
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                }

                throw e;
            }
        }
    }
}
