package ua.com.juja.sqlcmd.model;

import java.util.*;

public class InMemoryDatabaseManager implements DatabaseManager {

    public static final String TABLE_NAME = "users"; // TODO implement multitables

    private List<DataSet> data = new LinkedList<>();

    @Override
    public List<DataSet> getTableData(String tableName) {
        validateTable(tableName);
        return data;
    }

    @Override
    public int getSize(String tableName) {
        return data.size();
    }

    private void validateTable(String tableName) {
        if (!"users".equals(tableName)) {
            throw new UnsupportedOperationException(String.format("Only for 'user' table, but you try to work with: %s", tableName));
        }
    }

    @Override
    public Set<String> getTableNames() {
        Set<String> list = new LinkedHashSet<>();
        list.add(TABLE_NAME);
        return list;
    }

    @Override
    public void connect(String database, String userName, String password) {
        // do nothing
    }

    @Override
    public void clear(String tableName) {
        validateTable(tableName);
        data = new LinkedList<>();
    }

    @Override
    public void create(String tableName, DataSet input) {
        validateTable(tableName);
        data.add(input);
    }

    @Override
    public void update(String tableName, int id, DataSet newValue) {
        validateTable(tableName);
        DataSet dataSet = data.get(id-1);
        dataSet.updateFrom(newValue);
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        Set<String> list = new LinkedHashSet<>();
        list.add("id");
        list.add("name");
        list.add("password");
        return list;
    }

    @Override
    public boolean isConnected() {
        return true;
    }

}
