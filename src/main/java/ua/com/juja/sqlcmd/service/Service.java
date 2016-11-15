package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Service {
    Set<String> getDatabaseNames();
    Set<String> getTableNames();

    Set<String> getTableColumns(String tableName);
    List<List<String>> getTableData(String tableName);

    Map<String,String> getRecordData(String tableName, int id);
    void createRecord(String tableName, Map<String, String[]> parameters);
    void deleteRecord(String tableName, int id);
    void updateRecord(String tableName, int id, Map<String, String[]> parameters);
}
