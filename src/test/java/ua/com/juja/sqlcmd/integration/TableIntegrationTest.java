package ua.com.juja.sqlcmd.integration;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.juja.sqlcmd.Main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static ua.com.juja.sqlcmd.TestUtilites.formatOutput;
import static ua.com.juja.sqlcmd.TestUtilites.getConnectParameters;

public class TableIntegrationTest {
    private static ConfigurableInputStream in;
    private static ByteArrayOutputStream out;
    private static String connectParameters;

    @BeforeClass
    public static void setup() {
        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
        connectParameters = getConnectParameters();
    }

    @Before
    public void clearIn() throws IOException {
        in.reset();
    }

    @Test
    public void testCreateAndDropTable() {
        in.add(connectParameters);
        in.add("dropTable|table1");
        in.add("yes");
        in.add("createTable|table1|zzz");
        in.add("createTable|table1|id SERIAL PRIMARY KEY, name varchar(45) NOT NULL, password varchar(45) NOT NULL");
        in.add("createTable|table1|id SERIAL PRIMARY KEY, name varchar(45) NOT NULL, password varchar(45) NOT NULL");
        in.add("tables");
        in.add("dropTable|table1|zzz");
        in.add("dropTable|table1");
        in.add("no");
        in.add("dropTable|table1");
        in.add("yes");
        in.add("tables");
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
                "Table 'table1' with query id SERIAL PRIMARY KEY, name varchar(45) NOT NULL, " +
                "password varchar(45) NOT NULL created successfully \n" +
                "Enter a command (help - list of commands):\n" +
                "Failure. Reason: Error creating table 'table1'. Query: id SERIAL PRIMARY KEY, " +
                "name varchar(45) NOT NULL, password varchar(45) NOT NULL\n" +
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
                "Good luck!\n", formatOutput(out));
    }
}
