package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class QueryTest {
    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Query(view, manager);
    }

    @Test
    public void testQuery() {
        command.process("query|SELECT * FROM users");
    }

    @Test
    public void testQueryWrongParameters() {
        try {
            command.process("query|SELECT * FROM users|77");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect command format. The correct format: 'query|text...',\n" +
                    "your command: query|SELECT * FROM users|77", e.getMessage());
        }
    }
}
