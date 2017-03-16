package ua.com.juja.sqlcmd.model;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class UserRoleTest {

    @Test
    public void testUserRole() throws Exception {
        Role expectedRole = Role.ROLE_ADMIN;
        User expectedUser = Mockito.mock(User.class);
        Integer expectedUserRoleId = 1;
        UserRole role = new UserRole();
        role.setRole(expectedRole);
        role.setUser(expectedUser);
        role.setUserRoleId(expectedUserRoleId);
        assertNotNull(role);
        assertEquals(expectedRole, role.getRole());
        assertEquals(expectedUser, role.getUser());
        assertEquals(expectedUserRoleId, role.getUserRoleId());
    }
}