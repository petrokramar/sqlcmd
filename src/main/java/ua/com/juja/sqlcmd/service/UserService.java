package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.User;

import java.util.List;

public interface UserService {

    User getUser(String name);

    User saveUser(User user);

    void deleteUser(User user);

    List<User> getUsers();

}
