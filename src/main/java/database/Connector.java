package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static Connection conn;

    public static Connection getConnection() {
        if (conn != null) {
            return conn;
        }

        StringBuilder url = new StringBuilder();

        // todo: parsing xml

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Connector.conn = conn;

        return conn;
    }

    public static void closeConnection(Connection toBeClosed) {
        if (toBeClosed == null) {
            return;
        }

        try {
            toBeClosed.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
