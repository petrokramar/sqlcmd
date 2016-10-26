package ua.com.juja.sqlcmd.model;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public abstract class DatabaseManagerTest {

    protected DatabaseManager manager;

    @Before
    public void setup() throws SQLException {
        manager = getDatabaseManager();
        manager.connect("sqlcmd", "postgres", "123456");
        manager.clear("users");
    }

    protected abstract DatabaseManager getDatabaseManager();

    @Test
    public void testGetTableNames() throws SQLException, ClassNotFoundException {
        Set<String> testTables = new LinkedHashSet<>();
        testTables.add("users");
        Set<String> tables = manager.getTableNames();
        assertEquals(testTables, tables);
    }

    @Test
    public void testGetTableColumnNames() throws SQLException, ClassNotFoundException {
        Set<String> testColumns = new LinkedHashSet<>();
        testColumns.add("id");
        testColumns.add("name");
        testColumns.add("password");
        Set<String> columns = manager.getTableColumns("users");
        assertEquals(testColumns, columns);
    }

    @Test
    public void testGetTableData() throws SQLException {
        DataSet input = new DataSet();
        input.put("id", 1);
        input.put("name", "John");
        input.put("password", "pass");
        manager.create("users", input);

        List<DataSet> users = manager.getTableData("users");
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
        manager.create("users", input);

        DataSet newValue = new DataSet();
        newValue.put("name", "John2");
        newValue.put("password", "pass2");
        manager.update("users", 1, newValue);

        List<DataSet> users = manager.getTableData("users");
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
        manager.create("users", input);

        manager.delete("users", 1);

        List<DataSet> users = manager.getTableData("users");
        assertEquals(0, users.size());
    }

    @Test
    public void testGetSize() throws SQLException {
        assertEquals(0, manager.getSize("users"));

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

        assertEquals(2, manager.getSize("users"));
    }

    @Test
    public void testDatabaseManagerIsConnected() {
        assertEquals(true, manager.isConnected());
    }


}
