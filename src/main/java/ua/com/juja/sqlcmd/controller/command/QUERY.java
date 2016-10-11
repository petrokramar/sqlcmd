package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by kramar on 11.10.16.
 */
public class Query implements Command {

    private View view;
    private DatabaseManager manager;

    public Query(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("query|");
    }

    @Override
    public void process(String command) {
        //TODO
        view.write("Пока ничего не делаем");
    }
}