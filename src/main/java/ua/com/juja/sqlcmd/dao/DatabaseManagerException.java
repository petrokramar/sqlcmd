package ua.com.juja.sqlcmd.dao;

class DatabaseManagerException extends RuntimeException {
    DatabaseManagerException(String message) {
        super(message);
    }

    DatabaseManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
