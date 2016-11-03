package ua.com.juja.sqlcmd.controller.command;

import org.junit.Test;
import org.mockito.Mockito;
import ua.com.juja.sqlcmd.view.View;

import static org.junit.Assert.*;

public class ExitMockitoTest {

    private final View view = Mockito.mock(View.class);

    @Test
    public void testCanProcessExit() {
        Command command = new Exit(view);
        assertTrue(command.canProcess("exit"));
    }

    @Test
    public void testCanProcessQwe() {
        Command command = new Exit(view);
        assertFalse(command.canProcess("qwe"));
    }

    @Test
    public void testProcessExit_ThrowsExitException() {
        Command command = new Exit(view);
        try {
            command.process("exit");
            fail("Expected ExitException");
        } catch (ExitException e) {

        }
        Mockito.verify(view).write("Good luck!");
    }
}
