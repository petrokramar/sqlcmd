package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.view.View;

import java.util.Map;
import java.util.TreeMap;

public class Help implements Command {

    private Command[] commands;
    private final View view;
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    public Help(View view) {
        this.view = view;
    }

    public void setCommands(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public boolean canProcess(String command) {
        return "help".equals(command);
    }

    @Override
    public void process(String command) {
        Map<String, String> sortedCommands = new TreeMap<>();
        for (Command item : commands) {
            if (!"".equals(item.format())) {
                sortedCommands.put(item.format(), item.description());
            }
        }
        view.write("---------- List of commands -----------");
        for (Map.Entry entry : sortedCommands.entrySet()) {
            view.write("\t" + ANSI_BLUE + entry.getKey());
            view.write("\t\t" + ANSI_RESET + entry.getValue());
        }
    }

    @Override
    public String format() {
        return "help";
    }

    @Override
    public String description() {
        return "help";
    }

}
