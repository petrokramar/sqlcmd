package ua.com.juja.sqlcmd.model;

import static org.junit.Assert.assertEquals;

/**
 * Created by Peter on 17.09.2016.
 */
public class JDBCDatabaseManagerTest extends DatabaseManagerTest{

    @Override
    protected DatabaseManager getDatabaseManager() {
        return new JDBCDatabaseManager();
    }

}
