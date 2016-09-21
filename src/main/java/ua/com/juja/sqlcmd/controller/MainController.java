package ua.com.juja.sqlcmd.controller;

import ua.com.juja.sqlcmd.controller.command.*;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by Peter on 19.09.2016.
 */
public class MainController {

    private final Command[] commands;
    private View view;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        commands = new Command[] {
            new Connect(view, manager),
            new Help(view),
            new Exit(view),
            new IsConnected(view, manager),
            new List(view, manager),
            new Find(view, manager),
            new Unsupported(view)
        };
    }

    public void run(){
        view.write("Привет!");
        view.write("Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.");
        while(true){
            String input = view.read();
            for(Command command: commands){
                if(command.canProcess(input)){
                    command.process(input);
                    break;
                };
            }
            view.write("Введите команду (help - помощь)");
        }
    }

}
