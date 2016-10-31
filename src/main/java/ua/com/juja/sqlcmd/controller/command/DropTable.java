package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by kramar on 31.10.16.
 */
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
