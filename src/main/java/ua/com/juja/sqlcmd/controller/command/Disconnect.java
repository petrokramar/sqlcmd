package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class Disconnect implements Command {
    private final View view;
    private final DatabaseManager manager;

    public Disconnect(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return "disconnect".equals(command);
    }

    @Override
    public void process(String command) {

    }

    @Override
    public String format() {
        return "disconnect|databaseName";
    }

    @Override
    public String description() {
        return "disconnect from database databaseName";
    }
}
