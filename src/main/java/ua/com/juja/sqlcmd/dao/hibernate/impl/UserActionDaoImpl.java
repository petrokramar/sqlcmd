package ua.com.juja.sqlcmd.dao.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.juja.sqlcmd.dao.hibernate.UserActionDao;
import ua.com.juja.sqlcmd.model.UserAction;

@Repository
public class UserActionDaoImpl implements UserActionDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserAction create(UserAction action){
        Session session = sessionFactory.getCurrentSession();
        session.save(action);
        return action;
    }
}

