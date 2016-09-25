package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by Peter on 25.09.2016.
 */
public class Clear implements Command {

    private View view;
    private DatabaseManager manager;

    public Clear(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }


    @Override
    public boolean canProcess(String command) {
        return command.startsWith("clear|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        if(data.length !=2){
            throw new IllegalArgumentException("Неправильный формат команды. Должно быть 'clear|tableName',\n" +
                    "а Вы ввели " + command);
        }
        String tableName = data[1];
        manager.clear(tableName);
        view.write(String.format("Таблица '%s' очищена", tableName));
    }
}
