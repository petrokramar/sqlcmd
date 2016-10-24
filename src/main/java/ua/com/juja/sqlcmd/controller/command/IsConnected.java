package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class IsConnected implements Command {

    private View view;
    private DatabaseManager manager;

    public IsConnected(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return !manager.isConnected();
    }

    @Override
    public void process(String command) {
        view.write(String.format("The command %s is not active. Connect to the database.", command));
    }

    @Override
    public String format() {
        return "";
    }

    @Override
    public String description() {
        return "";
    }

}
