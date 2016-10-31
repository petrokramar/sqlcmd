package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.TableConstructor;
import ua.com.juja.sqlcmd.view.View;

import java.util.List;
import java.util.Set;

public class TableData implements Command {
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
            Set<String> tableColumns = manager.getTableColumns(tableName);
            List<DataSet> tableData = manager.getTableData(tableName);
            TableConstructor constructor = new TableConstructor(tableColumns, tableData);
            view.write(constructor.getTableString());
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
        return "find|tableName";
    }

    @Override
    public String description() {
        return "display the contents of the table tableName";
    }
}
