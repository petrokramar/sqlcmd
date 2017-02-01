package ua.com.juja.sqlcmd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.juja.sqlcmd.dao.jpa.UserRepository;
import ua.com.juja.sqlcmd.dao.jpa.UserRoleRepository;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserRole;
import ua.com.juja.sqlcmd.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUser(String username) {
        User user = userRepository.findByUsername(username);
//        if (user != null) {
//            for (UserRole role: user.getUserRoles()) {
//                role.setUser(null);
//            }
//        }
        return user;
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        userRoleRepository.deleteByUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
}
