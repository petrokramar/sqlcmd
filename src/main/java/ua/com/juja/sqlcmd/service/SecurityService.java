package ua.com.juja.sqlcmd.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
