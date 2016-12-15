package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DatabaseConnection;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserAction;

public interface LogService {

    User saveUser(User user);

    DatabaseConnection saveDatabaseConnection(DatabaseConnection connection);

    UserAction saveUserAction(String description);
}
