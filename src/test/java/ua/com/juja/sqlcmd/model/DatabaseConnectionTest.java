package ua.com.juja.sqlcmd.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by peter.kramar on 15.03.2017.
 */
public class DatabaseConnectionTest {

    @Test
    public void testDatabaseConnection() throws Exception {
        String expectedDatabaseName = "dbname";
        int expectedId = 1;
        String expectedPort = "5432";
        String expectedServer = "localhost";
        String expectedUserName = "postgres";
        String expectedToString = "DatabaseConnection{database='dbname', user='postgres'}";
        DatabaseConnection connection = new DatabaseConnection();
        connection.setDatabaseName(expectedDatabaseName);
        connection.setId(expectedId);
        connection.setPort(expectedPort);
        connection.setServer(expectedServer);
        connection.setUserName(expectedUserName);
        assertNotNull(connection);
        assertEquals(expectedDatabaseName, connection.getDatabaseName());
        assertEquals(expectedId, connection.getId());
        assertEquals(expectedPort, connection.getPort());
        assertEquals(expectedServer, connection.getServer());
        assertEquals(expectedUserName, connection.getUserName());
        assertEquals(expectedToString, connection.toString());
    }
}