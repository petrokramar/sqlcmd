package ua.com.juja.sqlcmd.dao.manager;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.controller.PropertyHandler;
import ua.com.juja.sqlcmd.dao.DataSet;
import ua.com.juja.sqlcmd.dao.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public abstract class DatabaseManagerTest {
    protected static final String TEST_TABLE_NAME = "users_test";
    protected DatabaseManager manager;

    @Before
    public void setup() throws SQLException {
        manager = getDatabaseManager();
        final PropertyHandler settings = PropertyHandler.getInstance();
        manager.connect(TEST_TABLE_NAME, settings.getProperty("database.user.name"),
                settings.getProperty("database.user.password"));
        manager.clearTable(TEST_TABLE_NAME);
    }

    protected abstract DatabaseManager getDatabaseManager();

    @Test
    public void testGetTableNames() throws SQLException, ClassNotFoundException {
        Set<String> testTables = new LinkedHashSet<>();
        testTables.add("users");
        testTables.add(TEST_TABLE_NAME);
        Set<String> tables = manager.getTableNames();
        assertEquals(testTables, tables);
    }

    @Test
    public void testGetTableColumnNames() throws SQLException, ClassNotFoundException {
        Set<String> testColumns = new LinkedHashSet<>();
        testColumns.add("id");
        testColumns.add("name");
        testColumns.add("password");
        Set<String> columns = manager.getTableColumns(TEST_TABLE_NAME);
        assertEquals(testColumns, columns);
    }

    @Test
    public void testGetTableData() throws SQLException {
        DataSet input = new DataSet();
        input.put("id", 1);
        input.put("name", "John");
        input.put("password", "pass");
        manager.createRecord(TEST_TABLE_NAME, input);

        List<DataSet> users = manager.getTableData(TEST_TABLE_NAME);
        assertEquals(1, users.size());

        DataSet user = users.get(0);
        assertEquals("[id, name, password]", user.getNames().toString());
        assertEquals("[1, John, pass]", user.getValues().toString());
    }

    @Test
    public void testUpdateTableData() throws SQLException {
        DataSet input = new DataSet();
        input.put("id", 1);
        input.put("name", "John");
        input.put("password", "pass");
        manager.createRecord(TEST_TABLE_NAME, input);

        DataSet newValue = new DataSet();
        newValue.put("name", "John2");
        newValue.put("password", "pass2");
        manager.updateRecord(TEST_TABLE_NAME, 1, newValue);

        List<DataSet> users = manager.getTableData(TEST_TABLE_NAME);
        assertEquals(1, users.size());

        DataSet user = users.get(0);
        assertEquals("[id, name, password]", user.getNames().toString());
        assertEquals("[1, John2, pass2]", user.getValues().toString());
    }

    @Test
    public void testDeleteTableData() throws SQLException {
        DataSet input = new DataSet();
        input.put("id", 1);
        input.put("name", "John");
        input.put("password", "pass");
        manager.createRecord(TEST_TABLE_NAME, input);

        manager.deleteRecord(TEST_TABLE_NAME, 1);

        List<DataSet> users = manager.getTableData(TEST_TABLE_NAME);
        assertEquals(0, users.size());
    }

    @Test
    public void testGetSize() throws SQLException {
        assertEquals(0, manager.getTableSize(TEST_TABLE_NAME));

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

        assertEquals(2, manager.getTableSize(TEST_TABLE_NAME));
    }

    @Test
    public void testDatabaseManagerIsConnected() {
        assertEquals(true, manager.isConnected());
    }
}
