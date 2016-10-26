package ua.com.juja.sqlcmd.integration;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.juja.sqlcmd.Main;
import ua.com.juja.sqlcmd.controller.command.ExitException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private static ConfigurableInputStream in;//TODO может убрать статику?
    private static ByteArrayOutputStream out;

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
    public void testExit() {
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "Good luck!\n", getData());
    }

    @Test
    public void testHelp() {
        in.add("help");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "---------- List of commands -----------\n" +
                "\t\u001B[34mclear|tableName\n" +
                "\t\t\u001B[0mclearing table tableName\n" +
                "\t\u001B[34mconnect|databaseName|userName|password\n" +
                "\t\t\u001B[0mconnect to database\n" +
                "\t\u001B[34mcreate|tableName|column1|value1|...columnN|valueN\n" +
                "\t\t\u001B[0mcreating record for table tableName\n" +
                "\t\u001B[34mdelete|tableName|id\n" +
                "\t\t\u001B[0mdelete record from table tableName by id\n" +
                "\t\u001B[34mexit\n" +
                "\t\t\u001B[0mexit\n" +
                "\t\u001B[34mfind|tableName\n" +
                "\t\t\u001B[0mdisplay the contents of the table tableName\n" +
                "\t\u001B[34mhelp\n" +
                "\t\t\u001B[0mhelp\n" +
                "\t\u001B[34mlist\n" +
                "\t\t\u001B[0mdisplay list of tables\n" +
                "\t\u001B[34mquery|text...\n" +
                "\t\t\u001B[0mcustom SQL query\n" +
                "\t\u001B[34mupdate|tableName|id|column1|value1|...columnN|valueN\n" +
                "\t\t\u001B[0mupdate record for table tableName by id\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    @Test
    public void testListWithoutConnect() {
        in.add("list");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "The command list is not active. Connect to the database.\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    @Test
    public void testFindWithoutConnect() {
        in.add("find|users");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "The command find|users is not active. Connect to the database.\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    @Test
    public void testUnsupported() {
        in.add("zzz");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "The command zzz is not active. Connect to the database.\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    @Test
    public void testUnsupportedAfterConnect() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("zzz");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "Connection successful.\n" +
                "Enter a command (help - list of commands):\n" +
                "The command does not exist: zzz\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    @Test
    public void testListAfterConnect() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("list");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "Connection successful.\n" +
                "Enter a command (help - list of commands):\n" +
                "[users]\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    @Test
    public void testConnectAfterConnect() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("list");
        in.add("connect|sqlcmd|postgres|123456");
        in.add("list");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "Connection successful.\n" +
                "Enter a command (help - list of commands):\n" +
                "[users]\n" +
                "Enter a command (help - list of commands):\n" +
                "Connection successful.\n" +
                "Enter a command (help - list of commands):\n" +
                "[users]\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    @Test
    public void testConnectWithError() {
        in.add("connect|sqlcmd");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "Failure. Reason: Invalid number of parameters separated by '|', expected 4, but there are: 2\n" +
                "Try again.\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    @Test
    public void testFindUsersAfterConnect() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("clear|users");
        in.add("yes");
        in.add("find|users");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "Connection successful.\n" +
                "Enter a command (help - list of commands):\n" +
                "To confirm clearing table 'users' type 'yes'.\n" +
                "Table 'users' is cleared\n" +
                "Enter a command (help - list of commands):\n" +
                "+--+----+--------+\n" +
                "|id|name|password|\n" +
                "+--+----+--------+\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    @Test
    public void testFindAfterConnectWithData() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("clear|users");
        in.add("yes");
        in.add("create|users|id|10|name|Peter|password|1111");
        in.add("create|users|id|11|name|Victor|password|2222");
        in.add("find|users");
        in.add("exit");
        Main.main(new String[0]);

        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "Connection successful.\n" +
                "Enter a command (help - list of commands):\n" +
                "To confirm clearing table 'users' type 'yes'.\n" +
                "Table 'users' is cleared\n" +
                "Enter a command (help - list of commands):\n" +
                "Record {names: [id, name, password], values: [10, Peter, 1111]} added to the table 'users'\n" +
                "Enter a command (help - list of commands):\n" +
                "Record {names: [id, name, password], values: [11, Victor, 2222]} added to the table 'users'\n" +
                "Enter a command (help - list of commands):\n" +
                "+--+------+--------+\n" +
                "|id|name  |password|\n" +
                "+--+------+--------+\n" +
                "|10|Peter |1111    |\n" +
                "+--+------+--------+\n" +
                "|11|Victor|2222    |\n" +
                "+--+------+--------+\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    @Test
    public void testCreateWithError() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("create|users|id|15|name");
        in.add("exit");
        Main.main(new String[0]);

        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "Connection successful.\n" +
                "Enter a command (help - list of commands):\n" +
                "Failure. Reason: Need even number of parameters in format\n" +
                "'create|tableName|column1|value1|...columnN|valueN'. Received 'create|users|id|15|name'.\n" +
                "Try again.\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

    @Test
    public void testClearWithError() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("clear|zzz|xxx");
        in.add("exit");
        Main.main(new String[0]);

        assertEquals("Hello!\n" +
                "Enter the database name, user name and password in format connect|databaseName|userName|password.\n" +
                "(Full list of commands - help).\n" +
                "Connection successful.\n" +
                "Enter a command (help - list of commands):\n" +
                "Failure. Reason: Incorrect command format. The correct format: 'clear|tableName',\n" +
                "your command: clear|zzz|xxx\n" +
                "Try again.\n" +
                "Enter a command (help - list of commands):\n" +
                "Good luck!\n", getData());
    }

//    @Test//TODO
//    public void testClearWrongTable() {
//        in.add("connect|sqlcmd|postgres|123456");
//        in.add("clear|zzz");
//        in.add("exit");
//        Main.main(new String[0]);
//
//        assertEquals("Привет!\n" +
//                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
//                "Подключились.\n" +
//                "Введите команду (help - помощь)\n" +
//                "Неудача. Причина: Неправильный формат команды. Должно быть 'clear|tableName',\n" +
//                "а Вы ввели clear|zzz|xxx\n" +
//                "Повторите попытку.\n" +
//                "Несуществующая команда: clear|zzz|xxx\n" +
//                "Введите команду (help - помощь)\n" +
//                "До встречи!\n" +
//                "Неудача. Причина: null\n" +
//                "Повторите попытку.\n" +
//                "Несуществующая команда: exit\n" +
//                "Введите команду (help - помощь)\n" +
//                "До встречи!\n", getData());
//    }

//    @Test//TODO
//    public void testFindNotExistAfterConnect() {
//        in.add("connect|sqlcmd|postgres|123456");
//        in.add("find|notexist");
//        in.add("exit");
//        Main.main(new String[0]);
//        assertEquals("Привет!\n" +
//                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
//                "(Полный список команд - help).\n" +
//                "Подключились.\n" +
//                "Введите команду (help - помощь):\n" +
//                "----------------------------\n" +
//                "|\n" +
//                "----------------------------\n" +
//                "Введите команду (help - помощь):\n" +
//                "До встречи!\n", getData());
//    }

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
