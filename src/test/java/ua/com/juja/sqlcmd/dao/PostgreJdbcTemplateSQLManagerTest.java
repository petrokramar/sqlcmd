package ua.com.juja.sqlcmd.dao;

import org.junit.Before;
import ua.com.juja.sqlcmd.controller.PropertyHandler;

import java.sql.SQLException;

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
        manager.clearTable(TEST_TABLE_NAME);
    }
}