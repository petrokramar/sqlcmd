package ua.com.juja.sqlcmd.model;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Peter on 19.09.2016.
 */
public interface DatabaseManager {
    void connect(String database, String userName, String password) throws SQLException;

    DataSet[] getTableData(String tableName) throws SQLException;

    int getSize(String tableName) throws SQLException;

    Set<String> getTableNames() throws SQLException;

    void clear(String tableName) throws SQLException;

    void create(String tableName, DataSet input) throws SQLException;

    void update(String tableName, int id, DataSet input) throws SQLException;

    Set<String> getTableColumns(String tableName) throws SQLException;

    boolean isConnected();
}
