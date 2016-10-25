package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;

public class Update implements Command {
    private static final int NUMBER_OF_PARAMETERS = 2;
    private View view;
    private DatabaseManager manager;

    public Update(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("update|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        if (validate(command)) {
            String tableName = data[1];
            int id = Integer.parseInt(data[2]);
            DataSet dataSet = new DataSet();
            for (int i = 3; i < data.length; i += 2) {
                String columnName = data[i];
                String value = data[i + 1];
                dataSet.put(columnName, value);
            }
            try {
                manager.update(tableName, id, dataSet);
                view.write(String.format("Record with id=%d in table '%s' updated with %s", id, tableName, dataSet));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean validate(String command) {
        String[] data = command.split("\\|");
        if (data.length % NUMBER_OF_PARAMETERS == 0) {
            throw new IllegalArgumentException(
                    String.format("Incorrect command format. " +
                            "The correct format: 'update|tableName|id|column1|value1|...columnN|valueN',\n" +
                            "your command: %s", command));
        }
        return true;
    }

    @Override
    public String format() {
        return "update|tableName|id|column1|value1|...columnN|valueN";
    }

    @Override
    public String description() {
        return "update record for table tableName by id";
    }
}
