package ua.com.juja.sqlcmd.integration;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.juja.sqlcmd.Main;
import ua.com.juja.sqlcmd.controller.PropertyHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class TableIntegrationTest {
    private static ConfigurableInputStream in;
    private static ByteArrayOutputStream out;
    private static final String CONNECT_PARAMETERS =
            String.format("connect|%s|%s|%s", PropertyHandler.getDatabaseName(),
                    PropertyHandler.getDatabaseUserName(), PropertyHandler.getDatabaseUserPassword());

    @BeforeClass
    public static void setup() {
        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @Before
    public void clearIn() throws IOException {
        in.reset();
    }

    @Test
    public void testCreateAndDropTable() {
        in.add(CONNECT_PARAMETERS);
        in.add("dropTable|table1");
        in.add("yes");
        in.add("createTable|table1|zzz");
        in.add("createTable|table1|id SERIAL PRIMARY KEY, name varchar(45) NOT NULL, password varchar(45) NOT NULL");
        in.add("createTable|table1|id SERIAL PRIMARY KEY, name varchar(45) NOT NULL, password varchar(45) NOT NULL");
        in.add("list");
        in.add("dropTable|table1|zzz");
        in.add("dropTable|table1");
        in.add("no");
        in.add("dropTable|table1");
        in.add("yes");
        in.add("list");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "Connection successful.\n" +
                "Enter a command (help - list of commands):\n" +
                "To confirm drop table 'table1' type 'yes'.\n" +
                "Table 'table1' dropped successfully\n" +
                "Enter a command (help - list of commands):\n" +
                "Failure. Reason: Error creating table 'table1'. Query: zzz\n" +
                "Try again.\n" +
                "Enter a command (help - list of commands):\n" +
                "Table 'table1' with query id SERIAL PRIMARY KEY, name varchar(45) NOT NULL, password varchar(45) NOT NULL created successfully \n" +
                "Enter a command (help - list of commands):\n" +
                "Failure. Reason: Error creating table 'table1'. Query: id SERIAL PRIMARY KEY, name varchar(45) NOT NULL, password varchar(45) NOT NULL\n" +
                "Try again.\n" +
                "Enter a command (help - list of commands):\n" +
                "[table1, users]\n" +
                "Enter a command (help - list of commands):\n" +
                "Failure. Reason: Incorrect command format. The correct format: 'dropTable|TableName',\n" +
                "your command: dropTable|table1|zzz\n" +
                "Try again.\n" +
                "Enter a command (help - list of commands):\n" +
                "To confirm drop table 'table1' type 'yes'.\n" +
                "Drop table 'table1' cancelled.\n" +
                "Enter a command (help - list of commands):\n" +
                "To confirm drop table 'table1' type 'yes'.\n" +
                "Table 'table1' dropped successfully\n" +
                "Enter a command (help - list of commands):\n" +
                "[users]\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    private String getData() {
        try {
            String result = new String(out.toByteArray(), "UTF-8").replaceAll("\r\n", "\n");
            out.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

}
