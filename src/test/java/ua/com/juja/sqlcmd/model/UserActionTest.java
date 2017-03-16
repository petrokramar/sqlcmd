package ua.com.juja.sqlcmd.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.*;

public class UserActionTest {

    @Test
    public void testUserAction() throws Exception {
        String expectedAction = "Test action";
        DatabaseConnection expectedConnection = Mockito.mock(DatabaseConnection.class);
        Date expectedDate = new Date();
        int expectedId = 1;
        User expectedUser = Mockito.mock(User.class);
        UserAction action = new UserAction();
        action.setAction(expectedAction);
        action.setDatabaseConnection(expectedConnection);
        action.setDate(expectedDate);
        action.setId(expectedId);
        action.setUser(expectedUser);
        assertNotNull(action);
        assertEquals(expectedAction, action.getAction());
        assertEquals(expectedConnection, action.getDatabaseConnection());
        assertEquals(expectedDate, action.getDate());
        assertEquals(expectedId, action.getId());
        assertEquals(expectedUser, action.getUser());
    }
}