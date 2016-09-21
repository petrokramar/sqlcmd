package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by Peter on 20.09.2016.
 */
public class Connect implements Command {

    private View view;
    private DatabaseManager manager;

    public Connect(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("connect|");
    }

    @Override
    public void process(String command) {
        try {
            String[] data = command.split("\\|");
            if (data.length != 4) { //TODO refactor
                throw new IllegalArgumentException("Неверно количество параметров разделенных знаком '|', ожидается 4, но есть: " + data.length);
            }
            String databaseName = data[1];
            String userName = data[2];
            String password = data[3];
            manager.connect(databaseName, userName, password);
            view.write("Подключились.");
        }catch (Exception e){
            printError(e);
        }
    }

    private void printError(Exception e) {
        String message = e.getMessage();
        final Throwable cause = e.getCause();
        if(cause !=null){
            message += " " + cause.getMessage();
        }
        view.write("Неудача. Причина: " + message);
        view.write("Повторите попытку.");
    }

}
