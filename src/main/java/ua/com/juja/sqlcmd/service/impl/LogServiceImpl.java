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
import ua.com.juja.sqlcmd.dao.jpa.UserRoleRepository;
import ua.com.juja.sqlcmd.dao.manager.DatabaseManager;
import ua.com.juja.sqlcmd.model.*;
import ua.com.juja.sqlcmd.service.LogService;

import java.util.*;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private DatabaseManager manager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private UserActionRepository userActionRepository;

    @Autowired
    UserDao userDao;

    @Autowired
    UserActionDao userActionDao;

    @Override
    public User getUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            for (UserRole role: user.getUserRoles()) {
                role.setUser(null);
            }
        }
        return user;
    }

    @Override
    @Transactional
    public User saveUser(User user) {
//        setUserRoles(user);
//        userRoleRepository.deleteByUser(user);
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
    public void deleteUserRoles(User user) {
        userRoleRepository.deleteByUser(user);
    }

//    @Override
//    public User setUserRoles(User user) {
//        userRoleRepository.deleteByUser(user);
//        //TODO remove null
//        if (user.getRoleNames() != null) {
//            Set<UserRole> userRoles = new HashSet<>();
//            for (String name: user.getRoleNames()) {
//                UserRole role = new UserRole();
//                role.setUser(user);
//                role.setRole(Role.valueOf(name));
//                userRoles.add(role);
//            }
//            user.setUserRoles(userRoles);
//        }
//        return user;
//    }

//    @Override
//    public DatabaseConnection saveDatabaseConnection(DatabaseConnection connection) {
//        return connectionRepository.save(connection);
//    }

    @Override
    @Transactional
    public UserAction saveUserAction(String description) {
        User user = getActiveUser();
        DatabaseConnection connection = getDatabaseConnection();
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

    private User getActiveUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user;
        if (auth == null) {
            user = new User();
            user.setUsername("empty");
            user.setPassword("empty");
            user.setEmail("empty");
        } else {
            String username = auth.getName();
            user = userRepository.findByUsername(username);
            if (user == null) {
                user = new User();
                user.setUsername("empty");
                user.setPassword("empty");
                user.setEmail("empty");
            }
        }
        return user;
    }

    private DatabaseConnection getDatabaseConnection() {
        DatabaseConnection connection = manager.getDatabaseConnection();
        if (connection == null) {
            connection =  connectionRepository.findByDatabaseNameAndUserName(
                    "empty", "empty");
            if (connection == null) {
                connection = new DatabaseConnection();
                connection.setDatabaseName("empty");
                connection.setUserName("empty");
                //TODO remove next line
            connectionRepository.save(connection);
            }
        } else {
            String databaseName = connection.getDatabaseName();
            String databaseUserName = connection.getUserName();
            connection =  connectionRepository.findByDatabaseNameAndUserName(
                    databaseName, databaseUserName);
            if (connection == null) {
                connection = new DatabaseConnection();
                connection.setDatabaseName(databaseName);
                connection.setUserName(databaseUserName);
                //TODO remove next line
                connectionRepository.save(connection);
            }
        }
        return connection;
    }

    @Override
    public List<UserAction> getUserActions() {
        List<UserAction> actions = (List<UserAction>) userActionRepository.findAll();
        for (UserAction action: actions) {
            action.getUser().setUserRoles(new HashSet<UserRole>());
        }
        return actions;
    }

    @Override
    public void deleteUserActions() {
        userActionRepository.deleteAll();
    }
}
