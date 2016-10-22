package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class FindTest {

    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup(){
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Find(view, manager);
    }

    @Test
    public void testPrintTableData() throws SQLException {
        Set<String> list = new LinkedHashSet<>();
        list.add("id");
        list.add("name");
        list.add("password");
        when(manager.getTableColumns("users")).thenReturn(list);

        DataSet user1 = new DataSet();
        user1.put("id", 11);
        user1.put("name", "Petro");
        user1.put("password", "111122223333");

        DataSet user2 = new DataSet();
        user2.put("id", 12);
        user2.put("name", "Victor");
        user2.put("password", "22223333");

        List<DataSet> users = new ArrayList<>(Arrays.asList(user1, user2));
        when(manager.getTableData("users")).thenReturn(users);

        command.process("find|users");
        String expected = "[+--+------+------------+\n" +
                "|id|name  |password    |\n" +
                "+--+------+------------+\n" +
                "|11|Petro |111122223333|\n" +
                "+--+------+------------+\n" +
                "|12|Victor|22223333    |\n" +
                "+--+------+------------+]";
        print(expected);
    }

    @Test
    public void testPrintTableDataOneColumn() throws SQLException {
        Set<String> list = new LinkedHashSet<>();
        list.add("id");
        when(manager.getTableColumns("users")).thenReturn(list);

        DataSet user1 = new DataSet();
        user1.put("id", 11);

        DataSet user2 = new DataSet();
        user2.put("id", 12);

        List<DataSet> users = new ArrayList<>(Arrays.asList(user1, user2));
        when(manager.getTableData("users")).thenReturn(users);

        command.process("find|users");
        String expected = "[+--+\n" +
                "|id|\n" +
                "+--+\n" +
                "|11|\n" +
                "+--+\n" +
                "|12|\n" +
                "+--+]";
        print(expected);
    }

    @Test
    public void TestCanProcessFindWithParameters(){
        assertTrue(command.canProcess("find|users"));
    }

    @Test
    public void TestCanProcessFindWithoutParameters(){
        assertFalse(command.canProcess("find"));
    }

    @Test
    public void TestCanProcessQwe(){
        assertFalse(command.canProcess("qwe|user"));
    }

    @Test
    public void printEmptyTableData() throws SQLException {
        Set<String> list = new LinkedHashSet<>();
        list.add("id");
        list.add("name");
        list.add("password");
        when(manager.getTableColumns("users")).thenReturn(list);

        List<DataSet> users = new ArrayList<>();
        when(manager.getTableData("users")).thenReturn(users);

        command.process("find|users");
        String expected = "[+--+----+--------+\n" +
                "|id|name|password|\n" +
                "+--+----+--------+]";
        print(expected);

    }

    private void print(String expected) {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view, atLeastOnce()).write(captor.capture());
        assertEquals(
                expected,
                captor.getAllValues().toString());
    }

}
