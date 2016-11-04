package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class CreateTable implements Command {
    private final View view;
    private final DatabaseManager manager;

    public CreateTable(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("createTable|");
    }

    @Override
    public void process(String command) {
        if (validate(command)) {
            String[] data = command.split("\\|");
            String tableName = data[1];
            String query = data[2];
            manager.createTable(tableName, query);
            view.write(String.format("Table '%s' with query %s created successfully ", tableName, query));
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
        return "createTable|TableName|query text...";
    }

    @Override
    public String description() {
        return "create table TableName\n" +
                "\t\tExample: createTable|tableName|id SERIAL PRIMARY KEY, name varchar(45) NOT NULL,\n" +
                "\t\tpassword varchar(45) NOT NULL";
    }
}
