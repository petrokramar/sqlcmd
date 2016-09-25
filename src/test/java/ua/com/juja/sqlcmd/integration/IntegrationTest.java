package ua.com.juja.sqlcmd.integration;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.juja.sqlcmd.controller.Main;
import ua.com.juja.sqlcmd.controller.command.ExitException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Peter on 21.09.2016.
 */
public class IntegrationTest {

    private static ConfigurableInputStream in;//TODO может убрать статику?
    private static ByteArrayOutputStream out;

    @BeforeClass
    public static void setup(){
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
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testHelp() {
        in.add("help");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "Список команд\n" +
                "connect|databaseName|userName|password - подключение к базе данных\n" +
                "list - вывести список таблиц\n" +
                "find|tableName - вывести содержимое таблицы tableName\n" +
                "help - помощь\n" +
                "exit - выход\n" +
                "Введите команду (help - помощь)\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testListWithoutConnect() {
        in.add("list");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "Команда list не активна. Подключитесь к базе.\n" +
                "Введите команду (help - помощь)\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testFindWithoutConnect() {
        in.add("find|users");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "Команда find|users не активна. Подключитесь к базе.\n" +
                "Введите команду (help - помощь)\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testUnsupported() {//TODO
        in.add("zzz");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "Команда zzz не активна. Подключитесь к базе.\n" +
                "Введите команду (help - помощь)\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testUnsupportedAfterConnect() {//TODO
        in.add("connect|sqlcmd|postgres|123456");
        in.add("zzz");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь)\n" +
                "Несуществующая команда; zzz\n" +
                "Введите команду (help - помощь)\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testListAfterConnect() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("list");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь)\n" +
                "[users]\n" +
                "Введите команду (help - помощь)\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testConnectAfterConnect() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("list");
        in.add("connect|sqlcmd|postgres|123456");
        in.add("list");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь)\n" +
                "[users]\n" +
                "Введите команду (help - помощь)\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь)\n" +
                "[users]\n" +
                "Введите команду (help - помощь)\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testFindUsersAfterConnect() {//TODO
        in.add("connect|sqlcmd|postgres|123456");
        in.add("find|users");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь)\n" +
                "----------------------------\n" +
                "|id|name|password|\n" +
                "----------------------------\n" +
                "|1|John2|pass2|\n" +
                "Введите команду (help - помощь)\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testFindNotExistAfterConnect() {//TODO
//        in.add("connect|sqlcmd|postgres|123456");
//        in.add("find|notexist");
//        in.add("exit");
//        Main.main(new String[0]);
//        assertEquals("Привет!\n" +
//                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
//                "Подключились.\n" +
//                "Введите команду (help - помощь)\n" +
//                "[users]\n" +
//                "Введите команду (help - помощь)\n" +
//                "До встречи!\n", getData());
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
