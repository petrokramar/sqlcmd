package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class DropDatabaseTest {
    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new DropDatabase(view, manager);
    }

    @Test
    public void testCreateDatabase() {
        command.process("dropDatabase|testDatabase");
    }

    @Test
    public void testCreateDatabaseWrongParameters() {
        try {
            command.process("dropDatabase|testDatabase|111");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect command format. The correct format: 'dropDatabase|DatabaseName',\n" +
                    "your command: dropDatabase|testDatabase|111", e.getMessage());
        }

    }
}
