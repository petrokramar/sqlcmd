package ua.com.juja.sqlcmd.dao;

import ua.com.juja.sqlcmd.model.User;

public interface UserDao {

    User findByUserName(String username);

}
