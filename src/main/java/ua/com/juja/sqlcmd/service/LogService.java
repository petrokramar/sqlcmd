package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DatabaseConnection;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserAction;

import java.util.List;

public interface LogService {

    User getUser(String name);

    User saveUser(User user);

    void deleteUser(User user);

    List<User> getUsers();

    DatabaseConnection saveDatabaseConnection(DatabaseConnection connection);

    UserAction saveUserAction(String description);

    List<UserAction> getUserActions();
}
