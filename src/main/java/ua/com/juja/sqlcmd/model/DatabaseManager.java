package ua.com.juja.sqlcmd.model;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Peter on 19.09.2016.
 */
public interface DatabaseManager {
    void connect(String database, String userName, String password) throws SQLException;

    DataSet[] getTableData(String tableName) throws SQLException;

    int getSize(String tableName) throws SQLException;

    List getTableNames() throws SQLException;

    void clear(String tableName) throws SQLException; //TODO переспросить пользователя

    void create(String tableName, DataSet input) throws SQLException;

    void update(String tableName, int id, DataSet input) throws SQLException;

    List<String> getTableColumns(String tableName) throws SQLException;

    boolean isConnected();
}
