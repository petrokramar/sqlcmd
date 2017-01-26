package ua.com.juja.sqlcmd.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.com.juja.sqlcmd.config.TestAppConfig;
import ua.com.juja.sqlcmd.model.*;
import ua.com.juja.sqlcmd.service.LogService;
import ua.com.juja.sqlcmd.service.UserService;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
@WebAppConfiguration
public class LogServiceImplTest {
    private User user;

    @Autowired
    LogService service;

    @Autowired
    UserService userService;

    @Before
    public void setup(){
        user = new User();
        user.setUsername("test user333");
        user.setEmail("test_email333@test.com");
        user.setPassword("123456");
        user.setEnabled(true);

//        UserRole role = new UserRole();
//        role.setUser(user);
//        role.setRole(Role.ROLE_USER);
//        Set<UserRole> userRoles = new HashSet<>();
//        userRoles.add(role);
//        user.setUserRoles(userRoles);
    }

    @Test
    @Transactional
    public void testGetUser() throws Exception {
//        service.setUserRoles(user);
//        UserRole role = new UserRole();
//        role.setUser(user);
//        role.setRole(Role.ROLE_USER);
//        Set<UserRole> userRoles = new HashSet<>();
//        userRoles.add(role);
//        user.setUserRoles(userRoles);
        userService.saveUser(user);
//        service.deleteUserRoles(user);
//        user.setEmail("test_email888@test.com");
//        service.saveUser(user);
//        service.saveUser(user);
//        User dbUser = service.getUser("test user");
//        assertEquals(user.getName(), dbUser.getName());
//        service.deleteUser(user);
    }

    @Test
    public void testUser() throws Exception {
        int beginUserSize = userService.getUsers().size();
        userService.saveUser(user);
        int endUserSize = userService.getUsers().size();
        assertEquals(1, endUserSize-beginUserSize);
        userService.deleteUser(user);
    }

    @Test
    //TODO do not work
    public void testUserAction() throws Exception {
        int beginUserActionSize = service.getUserActions().size();
        service.saveUserAction("test user action");
        int endUserActionSize = service.getUserActions().size();
        assertEquals(1, endUserActionSize-beginUserActionSize);
    }

    @Test
    public void deleteUserActions() throws Exception {
        service.deleteUserActions();
        assertEquals(0, service.getUserActions().size());
    }

}