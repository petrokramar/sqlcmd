package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.view.View;

/**
 * Created by Peter on 20.09.2016.
 */
public class Help implements Command{

    private View view;

    public Help(View view) {
        this.view = view;
    }


    @Override
    public boolean canProcess(String command) {
        return "help".equals(command);
    }

    @Override
    public void process(String command) {
        view.write("Список команд");
        view.write("connect|databaseName|userName|password - подключение к базе данных");
        view.write("list - вывести список таблиц");
        view.write("clear|tableName - очистка таблицы tableName"); //TODO переспросить пользователя
        view.write("create|tableName|column1|value1|....columnN|valueN - создание записей таблицы tableName");
        view.write("find|tableName - вывести содержимое таблицы tableName");
        view.write("help - помощь");
        view.write("exit - выход");
    }
}
