package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.TableConstructor;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Query implements Command {
    private static final int NUMBER_OF_PARAMETERS = 2;
    private final View view;
    private final DatabaseManager manager;

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
        if (validate(command)) {
            String[] data = command.split("\\|");
            String query = data[1];
            try {
                List<DataSet> tableData = manager.executeQuery(query);
                if (!tableData.isEmpty()) {
                    Set<String> tableColumns = new LinkedHashSet<>(tableData.get(0).getNames());
                    TableConstructor constructor = new TableConstructor(tableColumns, tableData);
                    view.write(constructor.getTableString());
                } else {
                    view.write("Query executed.");
                }
            } catch (SQLException e) {
                view.write(String.format("Error execute query '%s' by reason: %s", query, e.getMessage()));
            }
        }
    }

    private boolean validate(String command) {
        String[] data = command.split("\\|");
        if (data.length != NUMBER_OF_PARAMETERS) {
            throw new IllegalArgumentException(
                    String.format("Incorrect command format. The correct format: 'query|text...',\n" +
                            "your command: %s", command));
        }
        return true;
    }

    @Override
    public String format() {
        return "query|text...";
    }

    @Override
    public String description() {
        return "custom SQL query";
    }
}