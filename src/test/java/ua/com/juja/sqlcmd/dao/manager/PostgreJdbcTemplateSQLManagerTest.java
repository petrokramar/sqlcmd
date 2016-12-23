package ua.com.juja.sqlcmd.dao.manager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        manager.dropTable(TEST_TABLE_NAME);
        manager.createTable(
                TEST_TABLE_NAME,
                "id INTEGER PRIMARY KEY, name VARCHAR(45) NOT NULL, password  VARCHAR(45) NOT NULL");
    }
}