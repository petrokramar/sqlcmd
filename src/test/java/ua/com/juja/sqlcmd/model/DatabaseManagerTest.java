package ua.com.juja.sqlcmd.model;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Peter on 17.09.2016.
 */
public abstract class DatabaseManagerTest {

    private DatabaseManager manager;

    @Before
    public void setup(){
        manager = getDatabaseManager();
        manager.connect("sqlcmd", "postgres", "123456");
    }

    protected abstract DatabaseManager getDatabaseManager();

    @Test
    public void testGetTableNames() throws SQLException, ClassNotFoundException {
        List<String> testTables = new ArrayList<>();
        testTables.add("users");
        List<String> tables = manager.getTableNames();
        assertEquals(testTables,tables);
    }

    @Test
    public void testGetTableColumnNames() throws SQLException, ClassNotFoundException {
        List<String> testColumns = new ArrayList<>();
        testColumns.add("id");
        testColumns.add("name");
        testColumns.add("password");
        List<String> columns = manager.getTableColumns("users");
        assertEquals(testColumns,columns);
    }
    @Test
    public void testGetTableData(){
        manager.clear("users");

        DataSet input = new DataSet();
        input.put("id", 1);
        input.put("name", "John");
        input.put("password", "pass");
        manager.create("users", input);

        DataSet[] users = manager.getTableData("users");
        assertEquals(1, users.length);

        DataSet user = users[0];
        assertEquals("[id, name, password]", Arrays.toString(user.getNames()));
        assertEquals("[1, John, pass]",Arrays.toString(user.getValues()));
    }

    @Test
    public void testUpdateTableData(){
        manager.clear("users");

        DataSet input = new DataSet();
        input.put("id", 1);
        input.put("name", "John");
        input.put("password", "pass");
        manager.create("users", input);

        DataSet newValue = new DataSet();
        newValue.put("name", "John2");
        newValue.put("password", "pass2");
        manager.update("users", 1, newValue);

        DataSet[] users = manager.getTableData("users");
        assertEquals(1, users.length);

        DataSet user = users[0];
        assertEquals("[id, name, password]", Arrays.toString(user.getNames()));
        assertEquals("[1, John2, pass2]",Arrays.toString(user.getValues()));
    }


}
