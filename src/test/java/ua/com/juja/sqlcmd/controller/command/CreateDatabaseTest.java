package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class CreateDatabaseTest {
    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new CreateDatabase(view, manager);
    }

    @Test
    public void testCreateDatabase() {
        command.process("createDatabase|testDatabase");
    }

    @Test
    public void testCreateDatabaseWrongParameters() {
        try {
            command.process("createDatabase|testDatabase|111");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect command format. The correct format: 'createDatabase|DatabaseName',\n" +
                    "your command: createDatabase|testDatabase|111", e.getMessage());
        }
    }
}
