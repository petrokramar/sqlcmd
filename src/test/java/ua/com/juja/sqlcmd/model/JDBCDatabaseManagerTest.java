package ua.com.juja.sqlcmd.model;

import static org.junit.Assert.assertEquals;

public class JDBCDatabaseManagerTest extends DatabaseManagerTest{

    @Override
    protected DatabaseManager getDatabaseManager() {
        return new JDBCDatabaseManager();
    }

}
