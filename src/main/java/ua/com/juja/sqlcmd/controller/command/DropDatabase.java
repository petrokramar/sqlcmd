package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.util.Set;

public class DropDatabase implements Command {
    private final View view;
    private final DatabaseManager manager;

    public DropDatabase(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("dropDatabase|");
    }

    @Override
    public void process(String command) {
        if (validate(command) && confirm(command)) {
            String[] data = command.split("\\|");
            String databaseName = data[1];
            if (databaseExist(databaseName)) {
                manager.dropDatabase(databaseName);
                view.write(String.format("Database '%s' dropped successfully", databaseName));
            } else {
                view.write(String.format("Database '%s' not exist. Operation cancelled.", databaseName));
            }
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

    private boolean confirm(String command) {
        String[] data = command.split("\\|");
        String databaseName = data[1];
        view.write(String.format("To confirm drop database '%s' type 'yes'.", databaseName));
        if ("yes".equals(view.read().trim())) {
            return true;
        } else {
            view.write(String.format("Drop database '%s' cancelled.", databaseName));
            return false;
        }
    }

    private boolean databaseExist(String databaseName) {
        Set<String> databases = manager.getDatabaseNames();
        return databases.contains(databaseName);
    }

    @Override
    public String format() {
        return "dropDatabase|DatabaseName";
    }

    @Override
    public String description() {
        return "drop database DatabaseName";
    }
}
