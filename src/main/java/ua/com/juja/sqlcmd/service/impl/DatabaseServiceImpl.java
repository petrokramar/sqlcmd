package ua.com.juja.sqlcmd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.juja.sqlcmd.dao.DataSet;
import ua.com.juja.sqlcmd.dao.manager.DatabaseManager;
import ua.com.juja.sqlcmd.service.DatabaseService;

import java.util.*;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private DatabaseManager manager;

    @Override
    public void connect(String server, String port, String databaseName, String userName, String password) {
        manager.connect(server, port, databaseName, userName, password);
    }

    @Override
    public boolean isConnected() {
        return manager.isConnected();
    }

    @Override
    public String currentDatabase() {
        return manager.currentDatabase();
    }

    @Override
    public Set<String> getDatabaseNames() {
        return manager.getDatabaseNames();
    }

    @Override
    public void createDatabase(String databaseName) {
        //TODO check exist database
        manager.createDatabase(databaseName);
    }

    @Override
    public void dropDatabase(String databaseName) {
        manager.dropDatabase(databaseName);
    }

    @Override
    public Set<String> getTableNames() {
        return manager.getTableNames();
    }

    @Override
    public void createTable(String tableName, String query) {
        manager.createTable(tableName, query);
    }

    @Override
    public void clearTable(String tableName) {
        manager.clearTable(tableName);
    }

    @Override
    public void dropTable(String tableName) {
        manager.dropTable(tableName);
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        return manager.getTableColumns(tableName);
    }

    @Override
    public List<List<String>> getTableData(String tableName) {
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
        manager.deleteRecord(tableName, id);
    }

    @Override
    public void updateRecord(String tableName, int id, Map<String, String[]> parameters) {
        DataSet dataSet = new DataSet();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            if (!("tableName".equals(entry.getKey()) || "id".equals(entry.getKey()))) {
                dataSet.put(entry.getKey(), entry.getValue()[0]);
            }
        }
        manager.updateRecord(tableName, id, dataSet);
    }

    @Override
    public List<List<String>> executeQuery(String query) {
        List<List<String>> result = new ArrayList<>();
        List<DataSet> data = manager.executeQuery(query);
        if (!data.isEmpty()) {
            Set<String> columns = new LinkedHashSet<>(data.get(0).getNames());
            List<String> columnRow = new ArrayList<>();
            columnRow.addAll(columns);
            result.add(columnRow);
            for (DataSet set : data) {
                List<Object> row = set.getValues();
                List<String> tableRow = new ArrayList<>();
                for (Object o: row) {
                    tableRow.add(o.toString());
                }
                result.add(tableRow);
            }
        }
        return result;
    }
}
