package ua.com.juja.sqlcmd.controller.command;

/**
 * Created by Peter on 20.09.2016.
 */
public interface Command {

    boolean canProcess(String command);

    void process(String command);
}
