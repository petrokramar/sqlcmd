package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DatabaseManager;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ServiceImpl implements Service {

    @Override
    public Set<String> getDatabaseNames(DatabaseManager databaseManager) {
        return databaseManager.getDatabaseNames();
    }

    @Override
    public List<String> commandList() {
        return Arrays.asList("help", "menu", "connect");
    }
}
