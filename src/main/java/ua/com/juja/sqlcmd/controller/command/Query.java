package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

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
        view.write("Пока ничего не делаем. Только для SQL.");
    }

    @Override
    public String format() {
        return "query|text...";
    }

    @Override
    public String description() {
        return "произвольный SQL запрос";
    }

}