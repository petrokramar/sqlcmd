package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.DatabaseManagerException;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;

public class DeleteRecord implements Command {
    private final View view;
    private final DatabaseManager manager;

    public DeleteRecord(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("delete|");
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
            if (manager.existRecord(tableName, "id", data[2])) {
                manager.delete(tableName, id);
                view.write(String.format("Record with id=%d in table '%s' deleted", id, tableName));
            } else {
                view.write(String.format("Record with id=%d in table '%s' not exist", id, tableName));
            }
        }
    }

    private boolean validate(String command) {
        String[] data = command.split("\\|");
        if (data.length != format().split("\\|").length) {
            throw new IllegalArgumentException(
                    String.format("Incorrect command format. The correct format: '%s',\n" +
                            "your command: %s", format(), command));
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
