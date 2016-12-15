package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DatabaseConnection;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserAction;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DatabaseService {
    void connect(String databaseName, String userName, String password);

    boolean isConnected();
    String currentDatabase();
    Set<String> getDatabaseNames();
    void createDatabase(String databaseName);
    void dropDatabase(String databaseName);

    Set<String> getTableNames();
    void createTable(String tableName, String query);
    void clearTable(String tableName);
    void dropTable(String tableName);

    Set<String> getTableColumns(String tableName);
    List<List<String>> getTableData(String tableName);

    Map<String,String> getRecordData(String tableName, int id);
    void createRecord(String tableName, Map<String, String[]> parameters);
    void deleteRecord(String tableName, int id);
    void updateRecord(String tableName, int id, Map<String, String[]> parameters);

    List<List<String>> executeQuery(String query);
}
