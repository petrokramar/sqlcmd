package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UpdateRecordTest {
    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new UpdateRecord(view, manager);
    }

    @Test
    public void testUpdateRecord() {
        command.process("update|users|55|name|Max|passsword|4321");
    }

    @Test
    public void testUpdateRecordWrongParameters() {
        try {
            command.process("update|users|55|name|Max|passsword");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect command format. The correct format: " +
                    "'update|tableName|id|column1|value1|...columnN|valueN',\n" +
                    "your command: update|users|55|name|Max|passsword", e.getMessage());
        }
    }

    @Test
    public void testUpdateRecordWrongId() {
        try {
            command.process("update|users|zzz|name|Max|passsword|1234");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect command format. Id not a number.\n" +
                    "Your id: zzz", e.getMessage());
        }
    }

    @Test
    public void testFormat() {
        assertEquals("update|tableName|id|column1|value1|...columnN|valueN",
                command.format());
    }

    @Test
    public void testDescription() {
        assertEquals("update record for table tableName by id", command.description());
    }

    @Test
    public void testCanProcess() {
        assertTrue(command.canProcess("update|users|55|name|Max|passsword|4321"));
    }

    @Test
    public void testCanProcessWrong() {
        assertFalse(command.canProcess("list"));
    }
}
