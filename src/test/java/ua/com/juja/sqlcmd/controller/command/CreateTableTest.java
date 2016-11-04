package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class CreateTableTest {
    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new CreateTable(view, manager);
    }

    @Test
    public void testCreateTable() {
        command.process("createTable|usersTest|id SERIAL PRIMARY KEY, name varchar(45) NOT NULL, " +
                "password varchar(45) NOT NULL");
    }

    @Test
    public void testCreateTableWrongParameters() {
        try {
            command.process("createTable|usersTest|name varchar(45)|111");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect command format. The correct format: 'createTable|TableName|query text...',\n" +
                    "your command: createTable|usersTest|name varchar(45)|111", e.getMessage());
        }
    }
}
