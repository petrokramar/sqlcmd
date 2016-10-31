package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class UpdateRecord implements Command {
    private final View view;
    private final DatabaseManager manager;

    public UpdateRecord(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("update|");
    }

    @Override
    public void process(String command) {
        if (validate(command)) {
            String[] data = command.split("\\|");
            String tableName = data[1];
            int id;
            try {
                id = Integer.parseInt(data[2]);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        String.format("Incorrect command format. Id not a number.\n" +
                                "Your id: %s", data[2]));
            }
            DataSet dataSet = new DataSet();
            for (int i = 3; i < data.length; i += 2) {
                String columnName = data[i];
                String value = data[i + 1];
                dataSet.put(columnName, value);
            }
            if (manager.existRecord(tableName, "id", data[2])) {
                manager.update(tableName, id, dataSet);
                view.write(String.format("Record with id=%d in table '%s' updated with %s", id, tableName, dataSet));
            } else {
                view.write(String.format("Record with id=%d in table '%s' not exist", id, tableName));
            }
        }
    }

    private boolean validate(String command) {
        String[] data = command.split("\\|");
        if (data.length % 2 == 0) {
            throw new IllegalArgumentException(
                    String.format("Incorrect command format. " +
                            "The correct format: '%s',\n" +
                            "your command: %s", format(), command));
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
