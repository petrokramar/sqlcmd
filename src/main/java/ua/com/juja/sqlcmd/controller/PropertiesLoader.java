package ua.com.juja.sqlcmd.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public static String DATABASE_URL;
    public static String DATABASE_NAME;
    public static String DATABASE_USER_NAME;
    public static String DATABASE_USER_PASSWORD;

    static {
        String resourceName = "sqlcmd.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            properties.load(resourceStream);
            DATABASE_URL = String.format(
                    "%s%s:%s/", properties.getProperty("database.jdbc.driver"),
                    properties.getProperty("database.server.name"),
                    properties.getProperty("database.port"));
            DATABASE_NAME = properties.getProperty("database.name");
            DATABASE_USER_NAME = properties.getProperty("database.user.name");
            DATABASE_USER_PASSWORD = properties.getProperty("database.user.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
