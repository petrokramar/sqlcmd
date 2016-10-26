package ua.com.juja.sqlcmd.model;

import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JDBCDatabaseManagerTest extends DatabaseManagerTest {
    @Override
    protected DatabaseManager getDatabaseManager() {
        return new JDBCDatabaseManager();
    }

    @Test
    public void testExecuteQuery() throws SQLException {
        List<DataSet> result = manager.executeQuery("SELECT * FROM users");
        assertEquals(0, result.size());

        DataSet input = new DataSet();
        input.put("id", 1);
        input.put("name", "John");
        input.put("password", "pass");
        manager.create("users", input);

        DataSet inputTwo = new DataSet();
        inputTwo.put("id", 2);
        inputTwo.put("name", "Max");
        inputTwo.put("password", "1234");
        manager.create("users", inputTwo);

        result = manager.executeQuery("SELECT * FROM users");
        assertEquals(2, result.size());
    }
}
