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
        commands = new Command[] {
            new Connect(view, manager),
            commandHelp,
            new Exit(view),
            new IsConnected(view, manager),
            new Clear(view, manager),
            new Create(view, manager),
            new GetTableNames(view, manager),
            new Find(view, manager),
            new Query(view, manager),
            new Unsupported(view)
        };
        commandHelp.setCommands(commands);
    }

    public void run(){
        try {
            doWork();
        }catch (ExitException e){
        }
    }

    private void doWork() {
//        view.write("     _____    ____    _         _____   __  __   _____");
//        view.write("    / ____|  / __ \\  | |       / ____| |  \\/  | |  __ \\");
//        view.write("   | (___   | |  | | | |      | |      | \\  / | | |  | |");
//        view.write("    \\___ \\  | |  | | | |      | |      | |\\/| | | |  | |");
//        view.write("    ____) | | |__| | | |____  | |____  | |  | | | |__| |");
//        view.write("   |_____/   \\___\\_\\ |______|  \\_____| |_|  |_| |_____/");

        view.write("Привет!");
        view.write("Введите имя базы данных, имя пользователя и пароль в формате connect|databaseName|userName|password.");
        view.write("(Полный список команд - help).");
        while(true){
            String input = view.read();
            for(Command command: commands){
                try {
                    if(command.canProcess(input)){
                        command.process(input);
                        break;
                    }
                }catch (Exception e){
                    if(e instanceof ExitException){
                        throw e;
                    }
                    printError(e);
                    break;
                };
            }
            view.write("Введите команду (help - помощь):");
        }
    }

    private void printError(Exception e) {
        String message = e.getMessage();
        final Throwable cause = e.getCause();
        if(cause !=null){
            message += " " + cause.getMessage();
        }
        view.write(String.format("Неудача. Причина: %s", message));
        view.write("Повторите попытку.");
    }

}
