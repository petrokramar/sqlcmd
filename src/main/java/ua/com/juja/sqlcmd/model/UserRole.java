package ua.com.juja.sqlcmd.model;

import javax.persistence.*;

public class UserRole {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
