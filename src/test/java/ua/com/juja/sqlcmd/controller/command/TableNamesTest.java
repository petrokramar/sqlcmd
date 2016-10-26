package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TableNamesTest {

    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new TableNames(view, manager);
    }

    @Test
    public void testValidate() throws SQLException {
        assertTrue(command.validate("list"));
   }

    @Test
    public void testValidateWrong() throws SQLException {
        try {
            command.validate("list|users");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect command format. The correct format: 'list',\n" +
                    "your command: list|users", e.getMessage());
        }
    }

}