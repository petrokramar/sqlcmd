package ua.com.juja.sqlcmd.model;

import java.util.List;

/**
 * Created by Peter on 19.09.2016.
 */
public interface DatabaseManager {
    void connect(String database, String userName, String password);

    DataSet[] getTableData(String tableName);

    int getSize(String tableName);

    List getTableNames();

    void clear(String tableName);

    void create(String tableName, DataSet input);

    void update(String tableName, int id, DataSet input);

    List<String> getTableColumns(String tableName);

    boolean isConnected();
}
