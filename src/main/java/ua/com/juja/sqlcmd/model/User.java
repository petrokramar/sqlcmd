package ua.com.juja.sqlcmd.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username", unique = true,
            nullable = false, length = 45)
    private String username;

    @Column(name = "password",
            nullable = false, length = 45)
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(name = "email",
            nullable = false, length = 45)
    private String email;

    @Column(name = "enabled", nullable = false)
    //TODO not work
//    @Type(type = "integer")
    private boolean enabled;

    //TODO Lazy
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user",cascade = CascadeType.ALL)
    private Set<UserRole> userRoles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                '}';
    }
}
