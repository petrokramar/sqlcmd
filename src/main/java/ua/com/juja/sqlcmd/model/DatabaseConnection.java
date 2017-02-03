package ua.com.juja.sqlcmd.model;

import javax.persistence.*;

@Entity
@Table(name = "connections")
public class DatabaseConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "server")
    private String server;

    @Column(name = "port")
    private String port;

    @Column(name = "db_name")
    private String databaseName;

    @Column(name = "db_user_name")
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "DatabaseConnection{" +
                "database='" + databaseName + '\'' +
                ", user='" + userName + '\'' +
                '}';
    }
}
