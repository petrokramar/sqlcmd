package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.TableConstructor;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class TableData implements Command {
    private static final int NUMBER_OF_PARAMETERS = 2;
    private final View view;
    private final DatabaseManager manager;

    public TableData(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("find|");
    }

    @Override
    public void process(String command) {
        if (validate(command)) {
            String[] data = command.split("\\|");
            String tableName = data[1];
            try {
                Set<String> tableColumns = manager.getTableColumns(tableName);
                List<DataSet> tableData = manager.getTableData(tableName);
                TableConstructor constructor = new TableConstructor(tableColumns, tableData);
                view.write(constructor.getTableString());
            } catch (SQLException e) {
                view.write(String.format("Error reading data from a table '%s' by reason: %s",
                        tableName, e.getMessage()));
            }
        }
    }

    private boolean validate(String command) {
        String[] data = command.split("\\|");
        if (data.length != NUMBER_OF_PARAMETERS) {
            throw new IllegalArgumentException(
                    String.format("Incorrect command format. The correct format: 'find|tableName',\n" +
                            "your command: %s", command));
        }
        return true;
    }

    @Override
    public String format() {
        return "find|tableName";
    }

    @Override
    public String description() {
        return "display the contents of the table tableName";
    }

}
