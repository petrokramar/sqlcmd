package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.PostgreSQLManager;

import java.lang.reflect.Array;
import java.util.*;

public class ServiceImpl implements Service {

    private DatabaseManager manager = new PostgreSQLManager();

    @Override
    public void connect(String databaseName, String userName, String password) {
        manager.connect(databaseName, userName, password);
    }

    @Override
    public Set<String> getDatabaseNames() {
        manager.connect("sqlcmd", "postgres", "123456");
        return manager.getDatabaseNames();
    }

    @Override
    public void createDatabase(String databaseName) {
        manager.createDatabase(databaseName);
    }

    @Override
    public void dropDatabase(String databaseName) {
        manager.dropDatabase(databaseName);
    }

    @Override
    public Set<String> getTableNames() {
        manager.connect("sqlcmd", "postgres", "123456");
        return manager.getTableNames();
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        manager.connect("sqlcmd", "postgres", "123456");
        return manager.getTableColumns(tableName);
    }

    @Override
    public List<List<String>> getTableData(String tableName) {
        manager.connect("sqlcmd", "postgres", "123456");
        Set<String> columns = manager.getTableColumns(tableName);
        List<DataSet> data = manager.getTableData(tableName);
        List<List<String>> tableData = new ArrayList<>();
        for (DataSet set : data) {
            List<Object> row = set.getValues();
            List<String> tableRow = new ArrayList<>();
            for (Object o: row) {
                tableRow.add(o.toString());
            }
            tableData.add(tableRow);
        }
        return tableData;
    }

    @Override
    public Map<String, String> getRecordData(String tableName, int id) {
        manager.connect("sqlcmd", "postgres", "123456");
        DataSet set = manager.getRecordData(tableName, id);
        Map<String, Object> data = set.getData();
        Map<String, String> record = new TreeMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            record.put(entry.getKey(), entry.getValue().toString());
        }
        record.remove("id");
        return record;
    }

    @Override
    public void createRecord(String tableName, Map<String, String[]> parameters) {
        manager.connect("sqlcmd", "postgres", "123456");
        DataSet dataSet = new DataSet();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            if (!("tableName".equals(entry.getKey()) || "id".equals(entry.getKey()))) {
                dataSet.put(entry.getKey(), entry.getValue()[0]);
            }
        }
        manager.createRecord(tableName, dataSet);
    }

    @Override
    public void deleteRecord(String tableName, int id) {
        manager.connect("sqlcmd", "postgres", "123456");
        manager.deleteRecord(tableName, id);
    }

    @Override
    public void updateRecord(String tableName, int id, Map<String, String[]> parameters) {
        manager.connect("sqlcmd", "postgres", "123456");
        DataSet dataSet = new DataSet();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            if (!("tableName".equals(entry.getKey()) || "id".equals(entry.getKey()))) {
                dataSet.put(entry.getKey(), entry.getValue()[0]);
            }
        }
        manager.updateRecord(tableName, id, dataSet);
    }

}
