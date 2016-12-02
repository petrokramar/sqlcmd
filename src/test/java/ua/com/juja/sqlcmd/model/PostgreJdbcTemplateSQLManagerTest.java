package ua.com.juja.sqlcmd.model;

import org.junit.Before;
import ua.com.juja.sqlcmd.controller.PropertyHandler;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by peter.kramar on 02.12.2016.
 */
public class PostgreJdbcTemplateSQLManagerTest extends DatabaseManagerTest{
    @Override
    protected DatabaseManager getDatabaseManager() {
        return new PostgreJdbcTemplateSQLManager();
    }

    @Before
    public void setup() throws SQLException {
        manager = getDatabaseManager();
        final PropertyHandler settings = PropertyHandler.getInstance();
        manager.connect(settings.getProperty("database.name"), settings.getProperty("database.user.name"),
                settings.getProperty("database.user.password"));
        manager.clearTable("users");
    }
}