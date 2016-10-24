package ua.com.juja.sqlcmd.controller.command;

import org.junit.Test;
import org.mockito.Mockito;
import ua.com.juja.sqlcmd.view.View;

import static org.junit.Assert.*;

public class ExitMockitoTest {

    private View view = Mockito.mock(View.class);

    @Test
    public void TestCanProcessExit(){
        Command command = new Exit(view);
        assertTrue(command.canProcess("exit"));
    }

    @Test
    public void TestCanProcessQwe(){
        Command command = new Exit(view);
        assertFalse(command.canProcess("qwe"));
    }

    @Test
    public void TestProcessExit_ThrowsExitException(){
        Command command = new Exit(view);
        try {
            command.process("exit");
            fail("Expected ExitException");
        }catch (ExitException e){

        }
        Mockito.verify(view).write("Good luck!");
    }
}
