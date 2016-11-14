package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DatabaseManager;

import java.util.List;
import java.util.Set;

public interface Service {
    Set<String> getDatabaseNames(DatabaseManager databaseManager);
    List<String> commandList();
}
