package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.Set;

public class TableNames implements Command {
    private static final int NUMBER_OF_PARAMETERS = 1;
    private final View view;
    private final DatabaseManager manager;

    public TableNames(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return "list".equals(command);
    }

    @Override
    public void process(String command) {
        try {
            Set<String> tableNames = manager.getTableNames();
            view.write(tableNames.toString());
        } catch (SQLException e) {
            view.write(String.format("Error getting the list of tables by reason: %s", e.getMessage()));
        }
    }

    @Override
    public boolean validate(String command) {
        String[] data = command.split("\\|");
        if (data.length != NUMBER_OF_PARAMETERS) {
            throw new IllegalArgumentException(
                    String.format("Incorrect command format. The correct format: 'list',\n" +
                            "your command: %s", command));
        }
        return true;
    }

    @Override
    public String format() {
        return "list";
    }

    @Override
    public String description() {
        return "display list of tables";
    }

}
