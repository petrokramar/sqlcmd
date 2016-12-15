package ua.com.juja.sqlcmd.dao.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.juja.sqlcmd.dao.hibernate.UserDao;
import ua.com.juja.sqlcmd.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User create(User user){
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return user;
    }

    @Override
    public User update(User user){
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        return user;
    }

    @Override
    public User findByUserName(String username) {
        List<User> users = new ArrayList<User>();
        users = sessionFactory.getCurrentSession()
                .createQuery("from User where username=?")
                .setParameter(0, username)
                .list();
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
}