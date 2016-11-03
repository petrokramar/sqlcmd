package ua.com.juja.sqlcmd.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {

    private static final String databaseUrl;
    private static final String databaseName;
    private static final String databaseUserName;
    private static final String databaseUserPassword;

    static {
        String resourceName = "sqlcmd.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            properties.load(resourceStream);
            databaseUrl = String.format(
                    "%s%s:%s/", properties.getProperty("database.jdbc.driver"),
                    properties.getProperty("database.server.name"),
                    properties.getProperty("database.port"));
            databaseName = properties.getProperty("database.name");
            databaseUserName = properties.getProperty("database.user.name");
            databaseUserPassword = properties.getProperty("database.user.password");
        } catch (IOException e) {
            throw new RuntimeException("Error loading sqlcmd.properties");
        }
    }

    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static String getDatabaseUserName() {
        return databaseUserName;
    }

    public static String getDatabaseUserPassword() {
        return databaseUserPassword;
    }

}
