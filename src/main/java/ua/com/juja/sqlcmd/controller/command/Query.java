package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.TableConstructor;
import ua.com.juja.sqlcmd.view.View;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Query implements Command {
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
            List<DataSet> tableData = manager.executeQuery(query);
            if (!tableData.isEmpty()) {
                Set<String> tableColumns = new LinkedHashSet<>(tableData.get(0).getNames());
                TableConstructor constructor = new TableConstructor(tableColumns, tableData);
                view.write(constructor.getTableString());
            } else {
                view.write("Query executed.");
            }
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

    @Override
    public String format() {
        return "query|text...";
    }

    @Override
    public String description() {
        return "custom SQL query";
    }
}