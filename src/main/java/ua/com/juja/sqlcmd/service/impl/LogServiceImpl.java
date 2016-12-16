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
import ua.com.juja.sqlcmd.model.UserRole;
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
    public User getUser(String name) {
        User user = userRepository.findByName(name);
        for (UserRole role: user.getUserRoles()) {
            role.setUser(null);
        }
        return user;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
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
        user = userRepository.findByName(username);
        if (user == null) {
            user = new User();
            user.setName("empty");
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

    @Override
    public List<UserAction> getUserActions() {
        List<UserAction> actions = (List<UserAction>) userActionRepository.findAll();
        for (UserAction action: actions) {
            action.getUser().setUserRoles(new HashSet<UserRole>());
        }
        return actions;
    }
}
