package ua.com.juja.sqlcmd.dao.hibernate.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.com.juja.sqlcmd.config.AppConfig;
import ua.com.juja.sqlcmd.config.TestAppConfig;
import ua.com.juja.sqlcmd.dao.hibernate.UserDao;
import ua.com.juja.sqlcmd.dao.jpa.UserRepository;
import ua.com.juja.sqlcmd.model.User;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
@WebAppConfiguration
public class UserDaoImplTest {

//    @Autowired
//    UserRepository userRepository;

//    @Test
//    public void create() throws Exception {
//        User user = new User();
//        user.setName("user1");
//        user.setPassword("pass1");
//        user.setEmail("email1");
//        user.setEnabled(true);
//        userDao.create(user);
//    }

    @Test
    public void findByUserName() throws Exception {

    }

}