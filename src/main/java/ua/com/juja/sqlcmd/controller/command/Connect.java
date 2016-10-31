package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class Connect implements Command {
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
            manager.connect(databaseName, userName, password);
            view.write("Connection successful.");
        }
    }

    private boolean validate(String command) {
        String[] data = command.split("\\|");
        if (data.length != format().split("\\|").length) {
            throw new IllegalArgumentException(
                    String.format("Invalid number of parameters separated by " +
                            "'|', expected %s, but there are: %s", format().split("\\|").length, data.length));
        }
        return true;
    }

    @Override
    public String format() {
        return "connect|databaseName|userName|password";
    }

    @Override
    public String description() {
        return "connect to database databaseName";
    }
}
