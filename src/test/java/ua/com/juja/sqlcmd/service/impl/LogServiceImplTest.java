package ua.com.juja.sqlcmd.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.com.juja.sqlcmd.config.TestAppConfig;
import ua.com.juja.sqlcmd.model.DatabaseConnection;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserAction;
import ua.com.juja.sqlcmd.service.LogService;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
@WebAppConfiguration
public class LogServiceImplTest {
    private User user;
    @Autowired
    LogService service;

    @Before
    public void setup(){
        user = new User();
        user.setName("test user");
        user.setEmail("test_email@test.com");
        user.setPassword("123");
        user.setEnabled(true);
    }

    @Test
    public void testGetUser() throws Exception {
        service.saveUser(user);
        User dbUser = service.getUser("test user");
        assertEquals(user.getName(), dbUser.getName());
        service.deleteUser(user);
    }

    @Test
    public void testUser() throws Exception {
        int beginUserSize = service.getUsers().size();
        service.saveUser(user);
        int endUserSize = service.getUsers().size();
        assertEquals(1, endUserSize-beginUserSize);
        service.deleteUser(user);
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