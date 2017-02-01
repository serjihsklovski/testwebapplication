package helper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connector {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            Driver driver = (Driver) Class.forName(ServiceProperties.getInstance().getJdbcDriverClassName()).newInstance();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(ServiceProperties.getInstance().getConnectionUrl());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
