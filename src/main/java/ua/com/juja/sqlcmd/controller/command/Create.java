package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;

public class Create implements Command {

    private static final int NUMBER_OF_PARAMETERS = 2;
    private final View view;
    private final DatabaseManager manager;

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
        if (validate(command)) {
            String tableName = data[1];
            DataSet dataSet = new DataSet();
            for (int i = 2; i < data.length; i += 2) {
                String columnName = data[i];
                String value = data[i + 1];
                dataSet.put(columnName, value);
            }
            try {
                manager.create(tableName, dataSet);
                view.write(String.format("Record %s added to the table '%s'", dataSet, tableName));
            } catch (SQLException e) {
                view.write(String.format("Error create record in table '%s' by reason: %s", tableName, e.getMessage()));
            }
        }
    }

    @Override
    public boolean validate(String command) {
        String[] data = command.split("\\|");
        if (data.length % NUMBER_OF_PARAMETERS != 0) {
            throw new IllegalArgumentException(String.format("Need even number of parameters in format\n" +
                    "'create|tableName|column1|value1|...columnN|valueN'. Received '%s'.", command));
        }
        return true;
    }

    @Override
    public String format() {
        return "create|tableName|column1|value1|...columnN|valueN";
    }

    @Override
    public String description() {
        return "creating record for table tableName";
    }

}
