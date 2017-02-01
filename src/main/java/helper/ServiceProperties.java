package helper;

import java.io.*;
import java.util.Properties;

public final class ServiceProperties {

    private static final String CONFIG_FILE_PATH = "configs.properties";
    private static Properties properties;

    private static Properties getProperties() throws IOException {
        if (properties != null) {
            return properties;
        }

        properties = new Properties();

        try (InputStream inputStream = ServiceProperties.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE_PATH)) {

            properties.load(inputStream);
        }

        return properties;
    }

    public static String getConnectionUrl() throws IOException {
        return getProperties().getProperty("connection_url");
    }

    public static String getUserDaoImplementationClassName() throws IOException {
        return getProperties().getProperty("user_dao_implementation_class_name");
    }

    public static String getJdbcDriverClassName() throws IOException {
        return getProperties().getProperty("jdbc_driver_class_name");
    }
}
