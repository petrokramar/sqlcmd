package ua.com.juja.sqlcmd.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private final Properties properties;

    public PropertiesLoader() {
        String resourceName = "sqlcmd.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        this.properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getServerName() {
        return properties.getProperty("database.server.name");
    }

    public String getDatabasePort() {
        return properties.getProperty("database.port");
    }


}
