package ua.com.juja.sqlcmd.controller;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.InMemoryDatabaseManager;
import ua.com.juja.sqlcmd.model.JDBCDatabaseManager;
import ua.com.juja.sqlcmd.view.Console;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by Peter on 19.09.2016.
 */
public class MainController {
    public static void main(String[] args) {
        View view = new Console();
//        DatabaseManager databaseManager = new InMemoryDatabaseManager();
        DatabaseManager databaseManager = new JDBCDatabaseManager();

        view.write("Привет!");
        while(true){
            view.write("Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.");
            String input = view.read();
            String[] data = input.split("\\|");
            String databaseName = data[0];
            String userName = data[1];
            String password = data[2];
            try {
                databaseManager.connect(databaseName, userName, password);
                break;
            }catch (Exception e){
                view.write("Неудача.");
                String message = e.getMessage();
                if(e.getCause()!=null){
                    message += e.getCause().getMessage();
                }
                view.write("Причина: " + message);
                view.write("Повторите попытку.");
            }

        }
        view.write("Подключились.");
    }
}
