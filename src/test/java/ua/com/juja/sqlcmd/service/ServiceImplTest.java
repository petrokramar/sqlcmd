package ua.com.juja.sqlcmd.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.juja.sqlcmd.dao.jpa.UserRepository;

/**
 * Created by peter.kramar on 08.12.2016.
 */
public class ServiceImplTest {
    @Autowired
    private UserRepository userRepository;

//    @Test
//    public void addUserTest(){
//        User user = new User();
//        user.setLogin("test");
//        user.setPassword("2222");
//        user.setEmail("1@1.com");
//        userRepository.save(user);
//    }



}
