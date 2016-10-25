package ua.com.juja.sqlcmd.controller;

import ua.com.juja.sqlcmd.controller.command.*;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class MainController {

    private final Command[] commands;
    private View view;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        Help commandHelp = new Help(view);
        commands = new Command[]{
                new Connect(view, manager),
                commandHelp,
                new Exit(view),
                new IsConnected(view, manager),
                new Clear(view, manager),
                new Create(view, manager),
                new Update(view, manager),
                new Delete(view, manager),
                new TableNames(view, manager),
                new TableData(view, manager),
                new Query(view, manager),
                new Unsupported(view)
        };
        commandHelp.setCommands(commands);
    }

    public void run() {
        try {
            doWork();
        } catch (ExitException e) {
        }
    }

    private void doWork() {
//        view.write("     _____    ____    _         _____   __  __   _____");
//        view.write("    / ____|  / __ \\  | |       / ____| |  \\/  | |  __ \\");
//        view.write("   | (___   | |  | | | |      | |      | \\  / | | |  | |");
//        view.write("    \\___ \\  | |  | | | |      | |      | |\\/| | | |  | |");
//        view.write("    ____) | | |__| | | |____  | |____  | |  | | | |__| |");
//        view.write("   |_____/   \\___\\_\\ |______|  \\_____| |_|  |_| |_____/");

        view.write("Hello!");
        view.write("Enter the database name, user name and password in format connect|databaseName|userName|password.");
        view.write("(Full list of commands - help).");
        while (true) {
            String input = view.read();
            for (Command command : commands) {
                try {
                    if (command.canProcess(input)) {
                        command.process(input);
                        break;
                    }
                } catch (Exception e) {
                    if (e instanceof ExitException) {
                        throw e;
                    }
                    printError(e);
                    break;
                }
                ;
            }
            view.write("Enter a command (help - list of commands):");
        }
    }

    private void printError(Exception e) {
        String message = e.getMessage();
        final Throwable cause = e.getCause();
        if (cause != null) {
            message += " " + cause.getMessage();
        }
        view.write(String.format("Failure. Reason: %s", message));
        view.write("Try again.");
    }

}
