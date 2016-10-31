package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;

public class ClearTable implements Command {
    private final View view;
    private final DatabaseManager manager;

    public ClearTable(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("clear|");
    }

    @Override
    public void process(String command) {
        if (validate(command) && confirm(command)) {
            String[] data = command.split("\\|");
            String tableName = data[1];
            manager.clear(tableName);
            view.write(String.format("Table '%s' is cleared", tableName));
        }
    }

    private boolean confirm(String command) {
        String[] data = command.split("\\|");
        String tableName = data[1];
        view.write(String.format("To confirm clearing table '%s' type 'yes'.", tableName));
        if ("yes".equals(view.read().trim())) {
            return true;
        } else {
            view.write(String.format("Clearing table '%s' cancelled.", tableName));
            return false;
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
        return "clear|tableName";
    }

    @Override
    public String description() {
        return "clearing table tableName";
    }
}
