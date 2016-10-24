package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;

public class Connect implements Command {

    private View view;
    private DatabaseManager manager;

    private static final String COMMAND_SAMPLE = "connect|sqlcmd|postgres|123456";//TODO

    public Connect(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("connect|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        if (data.length != count()) {
            throw new IllegalArgumentException(
                String.format("Invalid number of parameters separated by " +
                "'|', expected %s, but there are: %s", count(), data.length));
        }
        String databaseName = data[1];
        String userName = data[2];
        String password = data[3];
        try {
            manager.connect(databaseName, userName, password);
            view.write("Connection successful.");
        } catch (SQLException e) {
            view.write(String.format("Failed to connect to database '%s' by reason: %s",
                databaseName, e.getMessage()));
        }
    }

    private int count() {
        return COMMAND_SAMPLE.split("\\|").length;
    }

    @Override
    public String format() {
        return "connect|databaseName|userName|password";
    }

    @Override
    public String description() {
        return "connect to database";
    }

}
