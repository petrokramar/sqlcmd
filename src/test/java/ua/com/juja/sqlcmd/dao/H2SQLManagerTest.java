package ua.com.juja.sqlcmd.dao;

import org.junit.Before;

import java.sql.SQLException;

/**
 * Created by peter.kramar on 30.11.2016.
 */
public class H2SQLManagerTest extends DatabaseManagerTest{
    static boolean needInitTables = true;

    @Override
    protected DatabaseManager getDatabaseManager() {
        return new H2SQLManager();
    }


    @Before
    public void setup() throws SQLException {
        manager = getDatabaseManager();
        manager.connect("test", "sa", "");
        if (needInitTables) {
            manager.createTable(
                    TEST_TABLE_NAME, "id INTEGER PRIMARY KEY, name VARCHAR(45) NOT NULL, password  VARCHAR(45) NOT NULL");
        }
        needInitTables = false;
        manager.clearTable(TEST_TABLE_NAME);
    }
}