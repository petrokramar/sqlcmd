package ua.com.juja.sqlcmd.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface DatabaseManager {
    void connect(String database, String userName, String password);

    void disconnect(String database);

    boolean isConnected();

    void createDatabase(String databaseName);

    void dropDatabase(String databaseName);

    void createTable(String tableName, String query);

    void dropTable(String tableName);

    List<DataSet> getTableData(String tableName);

    int getSize(String tableName);

    Set<String> getTableNames();

    void clear(String tableName);

    boolean existRecord(String tableName, String field, String parameter);

    void create(String tableName, DataSet input);

    void update(String tableName, int id, DataSet input);

    void delete(String tableName, int id);

    Set<String> getTableColumns(String tableName);

    List<DataSet> executeQuery(String query);
}
