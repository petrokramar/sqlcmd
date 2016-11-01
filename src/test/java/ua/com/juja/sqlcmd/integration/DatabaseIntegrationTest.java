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

public class DatabaseIntegrationTest {
    private static ConfigurableInputStream in;
    private static ByteArrayOutputStream out;
    private static String CONNECT_PARAMETERS =
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
    public void testCreateAndDropDatabase() {
        in.add(CONNECT_PARAMETERS);
        in.add("dropDatabase|db1");
        in.add("yes");
        in.add("dropDatabase|db2");
        in.add("yes");
        in.add("createDatabase|db1|zzz");
        in.add("createDatabase|db1");
        in.add("dropDatabase|db1|zzz");
        in.add("dropDatabase|db2");
        in.add("yes");
        in.add("dropDatabase|db1");
        in.add("ye");
        in.add("dropDatabase|db1");
        in.add("yes");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "Connection successful.\n" +
                "Enter a command (help - list of commands):\n" +
                "To confirm drop database 'db1' type 'yes'.\n" +
                "Database 'db1' not exist. Operation cancelled.\n" +
                "Enter a command (help - list of commands):\n" +
                "To confirm drop database 'db2' type 'yes'.\n" +
                "Database 'db2' not exist. Operation cancelled.\n" +
                "Enter a command (help - list of commands):\n" +
                "Failure. Reason: Incorrect command format. The correct format: 'createDatabase|DatabaseName',\n" +
                "your command: createDatabase|db1|zzz\n" +
                "Try again.\n" +
                "Enter a command (help - list of commands):\n" +
                "Database 'db1' created successfully\n" +
                "Enter a command (help - list of commands):\n" +
                "Failure. Reason: Incorrect command format. The correct format: 'dropDatabase|DatabaseName',\n" +
                "your command: dropDatabase|db1|zzz\n" +
                "Try again.\n" +
                "Enter a command (help - list of commands):\n" +
                "To confirm drop database 'db2' type 'yes'.\n" +
                "Database 'db2' not exist. Operation cancelled.\n" +
                "Enter a command (help - list of commands):\n" +
                "To confirm drop database 'db1' type 'yes'.\n" +
                "Drop database 'db1' cancelled.\n" +
                "Enter a command (help - list of commands):\n" +
                "To confirm drop database 'db1' type 'yes'.\n" +
                "Database 'db1' dropped successfully\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    public String getData() {
        try {
            String result = new String(out.toByteArray(), "UTF-8").replaceAll("\r\n", "\n");
            out.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

}
