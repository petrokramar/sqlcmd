package ua.com.juja.sqlcmd.model;

/**
 * Created by kramar on 31.10.16.
 */
public class DatabaseManagerException extends RuntimeException {
    DatabaseManagerException(String message) {
        super(message);
    }

    DatabaseManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
