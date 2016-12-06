package ua.com.juja.sqlcmd.dao;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.controller.PropertyHandler;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PostgreSQLManagerTest extends DatabaseManagerTest {
    @Override
    protected DatabaseManager getDatabaseManager() {
        return new PostgreSQLManager();
    }

    @Before
    public void setup() throws SQLException {
        manager = getDatabaseManager();
        final PropertyHandler settings = PropertyHandler.getInstance();
        manager.connect(settings.getProperty("database.name"), settings.getProperty("database.user.name"),
                settings.getProperty("database.user.password"));
        manager.clearTable(TEST_TABLE_NAME);
    }

    @Test
    public void testExecuteQuery() throws SQLException {
        List<DataSet> result = manager.executeQuery(String.format("SELECT * FROM %s", TEST_TABLE_NAME));
        assertEquals(0, result.size());

        DataSet input = new DataSet();
        input.put("id", 1);
        input.put("name", "John");
        input.put("password", "pass");
        manager.createRecord(TEST_TABLE_NAME, input);

        DataSet inputTwo = new DataSet();
        inputTwo.put("id", 2);
        inputTwo.put("name", "Max");
        inputTwo.put("password", "1234");
        manager.createRecord(TEST_TABLE_NAME, inputTwo);

        result = manager.executeQuery("SELECT * FROM users");
        assertEquals(2, result.size());
    }

    @Test
    public void testConnectWrongParameters() {
        try {
            manager.connect("sqlcmd", "postgres", "111");
            fail();
        } catch (DatabaseManagerException e) {
            assertEquals("Failed to connect to database: sqlcmd, user: postgres", e.getMessage());
        }
    }

    @Test
    public void testCreateExistingDatabase() {
        try {
            manager.createDatabase("sqlcmd");
            fail();
        } catch (DatabaseManagerException e) {
            assertEquals("Error creating a database 'sqlcmd'", e.getMessage());
        }
    }

    @Test
    public void testSelectQueryWrongParameters() {
        try {
            manager.executeQuery("SELECT pass FROM users");
            fail();
        } catch (DatabaseManagerException e) {
            assertTrue(e.getMessage().startsWith("Error execute query 'SELECT pass FROM users'"));
        }
    }

    @Test
    public void testInsertQueryWrongParameters() {
        try {
            manager.executeQuery("INSERT INTO users (id, name, pass) VALUES (2, 'Max', '456')");
            fail();
        } catch (DatabaseManagerException e) {
            assertTrue(e.getMessage().startsWith("Error execute query " +
                    "'INSERT INTO users (id, name, pass) VALUES (2, 'Max', '456')'"));
        }
    }

    @Test
    public void testGetDataWrongTable() {
        try {
            manager.getTableData("wrongTable");
            fail();
        } catch (DatabaseManagerException e) {
            assertEquals("Error getting data from table 'wrongTable'", e.getMessage());
        }
    }

    @Test
    public void testGetSizeWrongTable() {
        try {
            manager.getTableSize("wrongTable");
            fail();
        } catch (DatabaseManagerException e) {
            assertEquals("Error getting size of table 'wrongTable'", e.getMessage());
        }
    }

    @Test
    public void testClearWrongTable() {
        try {
            manager.clearTable("wrongTable");
            fail();
        } catch (DatabaseManagerException e) {
            assertEquals("Error deleting data from table 'wrongTable'", e.getMessage());
        }
    }

    @Test
    public void testExistRecordWrongTable() {
        try {
            manager.existRecord("wrongTable", "wrongField", "wrongParameter");
            fail();
        } catch (DatabaseManagerException e) {
            assertEquals("Error getting record from table 'wrongTable' where wrongField = wrongParameter",
                    e.getMessage());
        }
    }

    @Test
    public void testCreatedRecordWrongTable() {
        try {
            DataSet dataSet = new DataSet();
            dataSet.put("field", "value");
            manager.createRecord("wrongTable", dataSet);
            fail();
        } catch (DatabaseManagerException e) {
            assertEquals("Error creating record in table 'wrongTable'. Input: {names: [field], values: [value]}",
                    e.getMessage());
        }
    }

    @Test
    public void testUpdateRecordWrongTable() {
        DataSet dataSet = new DataSet();
        dataSet.put("field", "value");
        final int id = 1;
        try {
            manager.updateRecord("wrongTable", id, dataSet);
            fail();
        } catch (DatabaseManagerException e) {
            assertEquals("Error update record in table 'wrongTable' where id = 1, " +
                    "input: {names: [field], values: [value]}", e.getMessage());
        }
    }

    @Test
    public void testDeleteRecordWrongTable() {
        try {
            final int id = 1;
            manager.deleteRecord("wrongTable", id);
            fail();
        } catch (DatabaseManagerException e) {
            assertEquals("Error deleting data from table 'wrongTable' where id = 1",
                    e.getMessage());
        }
    }
}
