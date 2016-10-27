package ua.com.juja.sqlcmd.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {

    private static String databaseUrl;
    private static String databaseName;
    private static String databaseUserName;
    private static String databaseUserPassword;

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
            e.printStackTrace();
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