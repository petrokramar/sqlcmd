package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by kramar on 31.10.16.
 */
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
