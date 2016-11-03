package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.util.Set;

public class DatabaseNames implements Command {
    private final View view;
    private final DatabaseManager manager;

    public DatabaseNames(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return "databases".equals(command);
    }

    @Override
    public void process(String command) {
        Set<String> databaseNames = manager.getDatabasesNames();
        view.write(databaseNames.toString());
    }

    @Override
    public String format() {
        return "databases";
    }

    @Override
    public String description() {
        return "list of databases";
    }
}
