package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.view.View;

/**
 * Created by Peter on 20.09.2016.
 */
public class Help implements Command{

    private View view;
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    public Help(View view) {
        this.view = view;
    }


    @Override
    public boolean canProcess(String command) {
        return "help".equals(command);
    }

    @Override
    public void process(String command) {
        view.write("                                       Список команд");
        view.write("+---------------------------------------------------+--------------------------------------+");
        view.write("|                     команда                       |               описание               |");
        view.write("+---------------------------------------------------+--------------------------------------+");
        view.write("| connect|databaseName|userName|password            | подключение к базе данных            |");
        view.write("| list                                              | вывести список таблиц                |");
        view.write("| clear|tableName                                   | очистка таблицы tableName            |");
        view.write("| create|tableName|column1|value1|...columnN|valueN | создание записей таблицы tableName   |");
        view.write("| find|tableName                                    | вывести содержимое таблицы tableName |");
        view.write("| query|text...                                     | произвольный SQL запрос              |");
        view.write("| help                                              | помощь                               |");
        view.write("| exit                                              | выход                                |");
        view.write("+---------------------------------------------------+--------------------------------------+");
    }
}
