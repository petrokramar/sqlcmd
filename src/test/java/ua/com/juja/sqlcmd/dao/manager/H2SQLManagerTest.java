package ua.com.juja.sqlcmd.dao.manager;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import java.sql.SQLException;

/**
 * Created by peter.kramar on 30.11.2016.
 */
public class H2SQLManagerTest extends DatabaseManagerTest{
    @Override
    protected DatabaseManager getDatabaseManager() {
        return new H2SQLManager();
    }

    @Before
    public void setup() throws SQLException {
        manager = getDatabaseManager();
        manager.connect("test", "sa", "");
        manager.dropTable(TEST_TABLE_NAME);
        manager.createTable(
                TEST_TABLE_NAME,
                "id INTEGER PRIMARY KEY, name VARCHAR(45) NOT NULL, password  VARCHAR(45) NOT NULL");
    }
}