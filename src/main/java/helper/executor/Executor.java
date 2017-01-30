package helper.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {

    public static int execUpdate(Connection conn, String upd) throws SQLException {
        int affectedRowsCount;

        try (Statement stmt = conn.createStatement()) {
            affectedRowsCount = stmt.executeUpdate(upd);
        }

        return affectedRowsCount;
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
}
