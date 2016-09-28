package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Peter on 26.09.2016.
 */
public class ClearTest {

    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup(){
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Clear(view, manager);
    }

    @Test
    public void testClearTable() throws SQLException {
        command.process("clear|users");
        verify(manager).clear("users");
        verify(view).write("Таблица 'users' очищена");
    }

    @Test
    public void testClearTableErrorOneParameter(){
        try {
            command.process("clear");
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Неправильный формат команды. Должно быть 'clear|tableName',\n" +
                    "а Вы ввели clear", e.getMessage());
        }
    }

    @Test
    public void testClearTableErrorThreeParameters(){
        try {
            command.process("clear|users|zzz");
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Неправильный формат команды. Должно быть 'clear|tableName',\n" +
                    "а Вы ввели clear|users|zzz", e.getMessage());
        }
    }

    @Test
    public void TestCanProcessClearWithParameters(){
        assertTrue(command.canProcess("clear|users"));
    }

    @Test
    public void TestCanProcessClearWithoutParameters(){
        assertFalse(command.canProcess("clear"));
    }

}
