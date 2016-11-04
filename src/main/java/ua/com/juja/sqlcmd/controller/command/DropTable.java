package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class DropTable implements Command {
    private final View view;
    private final DatabaseManager manager;

    public DropTable(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("dropTable|");
    }

    @Override
    public void process(String command) {
        if (validate(command) && confirm(command)) {
            String[] data = command.split("\\|");
            String tableName = data[1];
            manager.dropTable(tableName);
            view.write(String.format("Table '%s' dropped successfully", tableName));
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
        String tableName = data[1];
        view.write(String.format("To confirm drop table '%s' type 'yes'.", tableName));
        if ("yes".equals(view.read().trim())) {
            return true;
        } else {
            view.write(String.format("Drop table '%s' cancelled.", tableName));
            return false;
        }
    }

    @Override
    public String format() {
        return "dropTable|TableName";
    }

    @Override
    public String description() {
        return "drop table TableName";
    }
}
