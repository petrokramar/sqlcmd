package ua.com.juja.sqlcmd.model;

import java.util.*;

/**
 * Created by indigo on 25.08.2015.
 */
public class InMemoryDatabaseManager implements DatabaseManager {

    public static final String TABLE_NAME = "users"; // TODO implement multitables

    private DataSet[] data = new DataSet[1000];
    private int freeIndex = 0;

    @Override
    public DataSet[] getTableData(String tableName) {
        validateTable(tableName);

        return Arrays.copyOf(data, freeIndex);
    }

    @Override
    public int getSize(String tableName) {
        return freeIndex;
    }

    private void validateTable(String tableName) {
        if (!"users".equals(tableName)) {
            throw new UnsupportedOperationException("Only for 'user' table, but you try to work with: " + tableName);
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

        data = new DataSet[1000];
        freeIndex = 0;
    }

    @Override
    public void create(String tableName, DataSet input) {
        validateTable(tableName);

        data[freeIndex] = input;
        freeIndex++;
    }

    @Override
    public void update(String tableName, int id, DataSet newValue) {
        validateTable(tableName);

//        for (int index = 0; index < newValue.freeIndex; index++) {
//            if (data[index].get("id") == id) {
//                data[index].updateFrom(newValue);
//            }
//        }
        data[id-1].updateFrom(newValue);
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
