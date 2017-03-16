package ua.com.juja.sqlcmd.model;

import org.junit.Test;
import org.mockito.Mockito;

import javax.jws.soap.SOAPBinding;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    @Test
    public void testUser() throws Exception {
        String expectedEmail = "email";
        boolean expectedEnabled = true;
        String expectedPassword = "password";
        String expectedPasswordConfirm = "confirm password";
        String expectedUsername = "username";
        Set<UserRole> expectedUserRoles = new HashSet<>();
        expectedUserRoles.add(Mockito.mock(UserRole.class));
        expectedUserRoles.add(Mockito.mock(UserRole.class));
        String expectedToString = "User{name='username'}";
        User user = new User();
        user.setEmail(expectedEmail);
        user.setEnabled(expectedEnabled);
        user.setPassword(expectedPassword);
        user.setPasswordConfirm(expectedPasswordConfirm);
        user.setUsername(expectedUsername);
        user.setUserRoles(expectedUserRoles);
        assertNotNull(user);
        assertEquals(expectedEmail, user.getEmail());
        assertEquals(expectedEnabled, user.isEnabled());
        assertEquals(expectedPassword, user.getPassword());
        assertEquals(expectedPasswordConfirm, user.getPasswordConfirm());
        assertEquals(expectedUsername, user.getUsername());
        assertEquals(expectedUserRoles, user.getUserRoles());
        assertEquals(expectedToString, user.toString());
    }
}