package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;

public class Delete implements Command {
    private static final int NUMBER_OF_PARAMETERS = 3;
    private final View view;
    private final DatabaseManager manager;

    public Delete(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("delete|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        if (validate(command)) {
            String tableName = data[1];
            int id = Integer.parseInt(data[2]);
            try {
                manager.delete(tableName, id);
                view.write(String.format("Record with id=%d in table '%s' deleted", id, tableName));
            } catch (SQLException e) {
                view.write(String.format("Error delete record in table '%s' by reason: %s", tableName, e.getMessage()));
            }
        }
    }

    @Override
    public boolean validate(String command) {
        String[] data = command.split("\\|");
        if (data.length != NUMBER_OF_PARAMETERS) {
            throw new IllegalArgumentException(
                    String.format("Incorrect command format. The correct format: 'delete|tableName|id',\n" +
                            "your command: %s", command));
        }
        return true;
    }

    @Override
    public String format() {
        return "delete|tableName|id";
    }

    @Override
    public String description() {
        return "delete record from table tableName by id";
    }
}
