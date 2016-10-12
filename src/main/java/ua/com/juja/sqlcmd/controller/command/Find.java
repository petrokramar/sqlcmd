package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by Peter on 20.09.2016.
 */
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
            printHeader(tableColumns);
            DataSet[] tableData = manager.getTableData(tableName);
            printTable(tableData);
        } catch (SQLException e) {
            view.write(String.format("Ошибка чтения данных из таблицы '%s' по причине: %", tableName, e.getMessage()));
        }
    }

    private void printTable(DataSet[] tableData) {
        for(DataSet row: tableData){
            printRow(row);
        }
    }

    private void printRow(DataSet row) {
        List<Object> values = row.getValues();
        String result = "|";
        for (Object value: values) {
            result += value + "|";
        }
        view.write(result);
    }

    private void printHeader(Set<String> tableColumns) {
        String result = "|";
        for (String name: tableColumns) {
            result += name + "|";
        }
        view.write("----------------------------");
        view.write(result);
        view.write("----------------------------");
    }

}
