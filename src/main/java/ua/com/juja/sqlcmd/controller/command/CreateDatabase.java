package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class CreateDatabase implements Command {
    private final View view;
    private final DatabaseManager manager;

    public CreateDatabase(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("createDatabase|");
    }

    @Override
    public void process(String command) {
        if (validate(command)) {
            String[] data = command.split("\\|");
            String databaseName = data[1];
            manager.createDatabase(databaseName);
            view.write(String.format("Database '%s' created successfully", databaseName));
        }
    }

    @Override
    public String format() {
        return "createDatabase|DatabaseName";
    }

    @Override
    public String description() {
        return "create database DatabaseName";
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
}

