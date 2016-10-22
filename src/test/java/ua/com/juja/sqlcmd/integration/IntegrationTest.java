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
                "(Полный список команд - help).\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testHelp() {
        in.add("help");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "(Полный список команд - help).\n" +
                "                                       Список команд\n" +
                "+---------------------------------------------------+--------------------------------------+\n" +
                "|                     команда                       |               описание               |\n" +
                "+---------------------------------------------------+--------------------------------------+\n" +
                "| connect|databaseName|userName|password            | подключение к базе данных            |\n" +
                "| list                                              | вывести список таблиц                |\n" +
                "| clear|tableName                                   | очистка таблицы tableName            |\n" +
                "| create|tableName|column1|value1|...columnN|valueN | создание записей таблицы tableName   |\n" +
                "| find|tableName                                    | вывести содержимое таблицы tableName |\n" +
                "| query|text...                                     | произвольный SQL запрос              |\n" +
                "| help                                              | помощь                               |\n" +
                "| exit                                              | выход                                |\n" +
                "+---------------------------------------------------+--------------------------------------+\n" +
                "Введите команду (help - помощь):\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testListWithoutConnect() {
        in.add("list");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "(Полный список команд - help).\n" +
                "Команда list не активна. Подключитесь к базе.\n" +
                "Введите команду (help - помощь):\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testFindWithoutConnect() {
        in.add("find|users");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "(Полный список команд - help).\n" +
                "Команда find|users не активна. Подключитесь к базе.\n" +
                "Введите команду (help - помощь):\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testUnsupported() {
        in.add("zzz");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "(Полный список команд - help).\n" +
                "Команда zzz не активна. Подключитесь к базе.\n" +
                "Введите команду (help - помощь):\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testUnsupportedAfterConnect() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("zzz");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "(Полный список команд - help).\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь):\n" +
                "Несуществующая команда: zzz\n" +
                "Введите команду (help - помощь):\n" +
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
                "(Полный список команд - help).\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь):\n" +
                "[users]\n" +
                "Введите команду (help - помощь):\n" +
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
                "(Полный список команд - help).\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь):\n" +
                "[users]\n" +
                "Введите команду (help - помощь):\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь):\n" +
                "[users]\n" +
                "Введите команду (help - помощь):\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testConnectWithError() {
        in.add("connect|sqlcmd");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "(Полный список команд - help).\n" +
                "Неудача. Причина: Неверно количество параметров разделенных знаком '|', ожидается 4, но есть: 2\n" +
                "Повторите попытку.\n" +
                "Введите команду (help - помощь):\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testFindUsersAfterConnect() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("clear|users");
        in.add("yes");
        in.add("find|users");
        in.add("exit");
        Main.main(new String[0]);
        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "(Полный список команд - help).\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь):\n" +
                "Для подтверждения очистки таблицы 'users' наберите 'yes'.\n" +
                "Таблица 'users' очищена\n" +
                "Введите команду (help - помощь):\n" +
                "+--+----+--------+\n" +
                "|id|name|password|\n" +
                "+--+----+--------+\n" +
                "Введите команду (help - помощь):\n" +
                "До встречи!\n", getData());
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

        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "(Полный список команд - help).\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь):\n" +
                "Для подтверждения очистки таблицы 'users' наберите 'yes'.\n" +
                "Таблица 'users' очищена\n" +
                "Введите команду (help - помощь):\n" +
                "Запись {names: [id, name, password], values: [10, Peter, 1111]} добавлена в таблицу 'users'\n" +
                "Введите команду (help - помощь):\n" +
                "Запись {names: [id, name, password], values: [11, Victor, 2222]} добавлена в таблицу 'users'\n" +
                "Введите команду (help - помощь):\n" +
                "+--+------+--------+\n" +
                "|id|name  |password|\n" +
                "+--+------+--------+\n" +
                "|10|Peter |1111    |\n" +
                "+--+------+--------+\n" +
                "|11|Victor|2222    |\n" +
                "+--+------+--------+\n" +
                "Введите команду (help - помощь):\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testCreateWithError() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("create|users|id|15|name");
        in.add("exit");
        Main.main(new String[0]);

        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "(Полный список команд - help).\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь):\n" +
                "Неудача. Причина: Необходимо четное число параметров в формате\n" +
                "'create|tableName|column1|value1|...columnN|valueN'. Получено 'create|users|id|15|name'.\n" +
                "Повторите попытку.\n" +
                "Введите команду (help - помощь):\n" +
                "До встречи!\n", getData());
    }

    @Test
    public void testClearWithError() {
        in.add("connect|sqlcmd|postgres|123456");
        in.add("clear|zzz|xxx");
        in.add("exit");
        Main.main(new String[0]);

        assertEquals("Привет!\n" +
                "Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.\n" +
                "(Полный список команд - help).\n" +
                "Подключились.\n" +
                "Введите команду (help - помощь):\n" +
                "Неудача. Причина: Неправильный формат команды. Должно быть 'clear|tableName',\n" +
                "а Вы ввели clear|zzz|xxx\n" +
                "Повторите попытку.\n" +
                "Введите команду (help - помощь):\n" +
                "До встречи!\n", getData());
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
