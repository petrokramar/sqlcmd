package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.TableConstructor;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class Find implements Command{
    private View view;
    private DatabaseManager manager;

    public Find(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("find|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        String tableName = data[1];
        try {
            Set<String> tableColumns = manager.getTableColumns(tableName);
            List<DataSet> tableData = manager.getTableData(tableName);
            TableConstructor constructor = new TableConstructor(tableColumns, tableData);
            view.write(constructor.getTableString());
        } catch (SQLException e) {
            view.write(String.format("Ошибка чтения данных из таблицы '%s' по причине: %", tableName, e.getMessage()));
        }
    }

    @Override
    public String format() {
        return "find|tableName";
    }

    @Override
    public String description() {
        return "вывести содержимое таблицы tableName";
    }

}
