package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

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
        if (validate(command)) {
            String[] data = command.split("\\|");
            String databaseName = data[1];
            manager.dropDatabase(databaseName);
            view.write(String.format("Database '%s' dropped successfully", databaseName));
        }
    }

    @Override
    public String format() {
        return "dropDatabase|DatabaseName";
    }

    @Override
    public String description() {
        return "drop database DatabaseName";
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
