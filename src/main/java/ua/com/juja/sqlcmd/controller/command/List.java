package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;

/**
 * Created by Peter on 20.09.2016.
 */
public class List implements Command{
    private View view;
    private DatabaseManager manager;

    public List(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return "list".equals(command);
    }

    @Override
    public void process(String command) {
        java.util.List<String> tableNames = null;
        try {
            tableNames = manager.getTableNames();
            view.write(tableNames.toString());
        } catch (SQLException e) {
            view.write(String.format("Ошибка получения списка таблиц по причине: %", e.getMessage()));
        }
    }
}
