package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;

public class Connect implements Command {

    private static final int NUMBER_OF_PARAMETERS = 4;
    private final View view;
    private final DatabaseManager manager;

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
        if (validate(command)) {
            String[] data = command.split("\\|");
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
    }

    private boolean validate(String command) {
        String[] data = command.split("\\|");
        if (data.length != NUMBER_OF_PARAMETERS) {
            throw new IllegalArgumentException(
                    String.format("Invalid number of parameters separated by " +
                            "'|', expected %s, but there are: %s", NUMBER_OF_PARAMETERS, data.length));
        }
        return true;
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
