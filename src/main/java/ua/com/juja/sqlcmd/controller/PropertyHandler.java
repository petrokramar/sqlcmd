package ua.com.juja.sqlcmd.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {
    private static PropertyHandler instance;
    private final Properties properties;

    private PropertyHandler() {
        String resourceName = "sqlcmd.properties";
//        String resourceName = "../resources/sqlcmd.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            properties.load(resourceStream);
        } catch (IOException e) {
            throw new RuntimeException("Error loading sqlcmd.properties");
        }
    }

    public static PropertyHandler getInstance() {
        if (instance == null)
            instance = new PropertyHandler();
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
