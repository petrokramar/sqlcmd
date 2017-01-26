package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DatabaseConnection;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserAction;

import java.util.List;

public interface LogService {

    UserAction saveUserAction(String description);

    List<UserAction> getUserActions();

    void deleteUserActions();

}
