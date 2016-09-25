package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by Peter on 25.09.2016.
 */
public class Create implements Command {

    private View view;
    private DatabaseManager manager;

    public Create(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }


    @Override
    public boolean canProcess(String command) {
        return command.startsWith("create|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        if(data.length %2 != 0){
            throw new IllegalArgumentException(String.format("Необходимо четное число параметров в формате\n" +
                "'create|tableName|column1|value1|...columnN|valueN'. Получено '%s'.",command));
        }
        String tableName = data[1];
        DataSet dataSet = new DataSet();
        for(int i = 2; i < data.length; i += 2){
            String columnName = data[i];
            String value = data[i+1];
            dataSet.put(columnName, value);
        }
        manager.create(tableName, dataSet);
        view.write(String.format("Запись %s добавлена в таблицу '%s'", dataSet, tableName));
    }
}
