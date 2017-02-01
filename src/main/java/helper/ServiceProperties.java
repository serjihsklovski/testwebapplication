package helper;

import java.io.*;
import java.util.Properties;

public final class ServiceProperties {

    private static final String CONFIG_FILE_PATH = "configs.properties";

    private static ServiceProperties instance;

    private String connectionUrl;
    private String userDaoImplementationClassName;
    private String jdbcDriverClassName;

    private ServiceProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream = ServiceProperties.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE_PATH)) {

            properties.load(inputStream);

            connectionUrl = properties.getProperty("connection_url");
            userDaoImplementationClassName = properties.getProperty("user_dao_implementation_class_name");
            jdbcDriverClassName = properties.getProperty("jdbc_driver_class_name");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ServiceProperties getInstance() {
        if (instance == null) {
            instance = new ServiceProperties();
        }

        return instance;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public String getUserDaoImplementationClassName() {
        return userDaoImplementationClassName;
    }

    public String getJdbcDriverClassName() {
        return jdbcDriverClassName;
    }
}
