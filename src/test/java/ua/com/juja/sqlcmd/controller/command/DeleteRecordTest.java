package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DeleteRecordTest {
    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new DeleteRecord(view, manager);
    }

    @Test
    public void testDeleteRecord() {
        command.process("delete|users|33");
    }

    @Test
    public void testDeleteRecordWrongParameters() {
        try {
            command.process("delete|users|33|name");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect command format. The correct format: 'delete|tableName|id',\n" +
                    "your command: delete|users|33|name", e.getMessage());
        }
    }

    @Test
    public void testUpdateRecordWrongId() {
        try {
            command.process("delete|users|zzz");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect command format. Id not a number.\n" +
                    "Your id: zzz", e.getMessage());
        }
    }

    @Test
    public void testFormat() {
        assertEquals("delete|tableName|id",
                command.format());
    }

    @Test
    public void testDescription() {
        assertEquals("delete record from table tableName by id", command.description());
    }

    @Test
    public void testCanProcess() {
        assertTrue(command.canProcess("delete|users|33"));
    }

    @Test
    public void testCanProcessWrong() {
        assertFalse(command.canProcess("delet|users|33"));
    }
}