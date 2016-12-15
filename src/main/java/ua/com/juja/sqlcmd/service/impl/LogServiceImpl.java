package ua.com.juja.sqlcmd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.juja.sqlcmd.dao.hibernate.UserActionDao;
import ua.com.juja.sqlcmd.dao.hibernate.UserDao;
import ua.com.juja.sqlcmd.dao.jpa.ConnectionRepository;
import ua.com.juja.sqlcmd.dao.jpa.UserActionRepository;
import ua.com.juja.sqlcmd.dao.jpa.UserRepository;
import ua.com.juja.sqlcmd.dao.manager.DatabaseManager;
import ua.com.juja.sqlcmd.model.DatabaseConnection;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserAction;
import ua.com.juja.sqlcmd.service.LogService;

import java.util.*;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private DatabaseManager manager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private UserActionRepository userActionRepository;

    @Autowired
    UserDao userDao;

    @Autowired
    UserActionDao userActionDao;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public DatabaseConnection saveDatabaseConnection(DatabaseConnection connection) {
        return connectionRepository.save(connection);
    }

    @Override
    @Transactional
    public UserAction saveUserAction(String description) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user;
        user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername("empty");
            user.setPassword("empty");
            user.setEmail("empty");
        }
        DatabaseConnection connection = manager.getDatabaseConnection();
        if (connection == null) {
            connection = new DatabaseConnection();
            connection.setDatabaseName("empty");
            connection.setUserName("empty");
        } else {
            String databaseName = connection.getDatabaseName();
            String databaseUserName = connection.getUserName();
            connection =  connectionRepository.findByDatabaseNameAndUserName(
                    databaseName, databaseUserName);
            if (connection == null) {
                connection = new DatabaseConnection();
                connection.setDatabaseName(databaseName);
                connection.setUserName(databaseUserName);
            }
        }
        UserAction action = new UserAction();
        action.setUser(user);
        action.setDatabaseConnection(connection);
        action.setAction(description);
        action.setDate(new Date());

        //JPA
        return userActionRepository.save(action);

        //Hibernate
//        return userActionDao.create(action);
    }
}
