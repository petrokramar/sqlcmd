package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.Set;

public class GetTableNames implements Command{
    private View view;
    private DatabaseManager manager;

    public GetTableNames(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return "list".equals(command);
    }

    @Override
    public void process(String command) {
        try {
            Set<String> tableNames = manager.getTableNames();
            view.write(tableNames.toString());
        } catch (SQLException e) {
            view.write(String.format("Error getting the list of tables by reason: %", e.getMessage()));
        }
    }

    @Override
    public String format() {
        return "list";
    }

    @Override
    public String description() {
        return "display list of tables";
    }

}
