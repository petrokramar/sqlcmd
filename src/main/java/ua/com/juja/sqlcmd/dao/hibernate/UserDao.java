package ua.com.juja.sqlcmd.dao.hibernate;

import ua.com.juja.sqlcmd.model.User;

public interface UserDao {

    User create(User user);

    User update(User user);

    User findByUserName(String username);

}
