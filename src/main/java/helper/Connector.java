package helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connector {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        try {
            Driver driver = (Driver) Class.forName(ServiceProperties.getInstance().getJdbcDriverClassName()).newInstance();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(ServiceProperties.getInstance().getConnectionUrl());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
