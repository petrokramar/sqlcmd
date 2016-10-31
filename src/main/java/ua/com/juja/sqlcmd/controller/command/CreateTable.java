package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by kramar on 31.10.16.
 */
public class CreateTable implements Command {
    private final View view;
    private final DatabaseManager manager;

    public CreateTable(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("createTable|");
    }

    @Override
    public void process(String command) {

    }

    @Override
    public String format() {
        return "createTable|TableName";
    }

    @Override
    public String description() {
        return "create table TableName";
    }
}
