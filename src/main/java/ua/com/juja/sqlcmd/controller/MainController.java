package ua.com.juja.sqlcmd.controller;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.util.List;

/**
 * Created by Peter on 19.09.2016.
 */
public class MainController {

    private View view;
    private DatabaseManager manager;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    public void run(){
        connectToDb();
        
        while(true){
            view.write("Введите команду (help - помощь)");
            String command = view.read();
        
            if("list".equals(command)){
                doList();
            }else if(command.startsWith("find|")){
                doFind(command);
            }else if("help".equals(command)){
                doHelp();
            }else if("exit".equals(command)){
                view.write("До встречи!");
                System.exit(0);
            }else{
                view.write("Несуществующая команда; " + command);
            }
        }
    }

    private void doFind(String command) {
        String[] data = command.split("\\|");
        String tableName = data[1];
        List<String> tableColumns = manager.getTableColumns(tableName);
        printHeader(tableColumns);
        DataSet[] tableData = manager.getTableData(tableName);
        printTable(tableData);
    }

    private void printTable(DataSet[] tableData) {
        for(DataSet row: tableData){
            printRow(row);
        }
    }

    private void printRow(DataSet row) {
        Object[] values = row.getValues();
        String result = "|";
        for (Object value: values) {
            result += value + "|";
        }
        view.write(result);
    }

    private void printHeader(List<String> tableColumns) {
        String result = "|";
        for (String name: tableColumns) {
            result += name + "|";
        }
        view.write("----------------------------");
        view.write(result);
        view.write("----------------------------");
    }

    private void doHelp() {
        view.write("Список команд");
        view.write("list - вывести список таблиц");
        view.write("find|tableName - вывести содержимое таблицы tableName");
        view.write("help - помощь");
        view.write("exit - выход");
    }

    private void doList() {
        List<String> tableNames = manager.getTableNames();
        view.write(tableNames.toString());
    }

    private void connectToDb() {
        view.write("Привет!");
        while(true){
            view.write("Введите имя базы данных, имя пользователя и пароль в формате databaseName|userName|password.");
            try {
                String input = view.read();
                String[] data = input.split("\\|");
                if (data.length != 3) {
                    throw new IllegalArgumentException("Неверно количество параметров разделенных знаком '|', ожидается 3, но есть: " + data.length);
                }
                String databaseName = data[0];
                String userName = data[1];
                String password = data[2];
                manager.connect(databaseName, userName, password);
                break;
            }catch (Exception e){
                printError(e);
            }
        }
        view.write("Подключились.");
    }

    private void printError(Exception e) {
        String message = "Неудача.\n" + e.getClass().getSimpleName() + ": "+ e.getMessage();
        final Throwable cause = e.getCause();
        if(cause !=null){
            message += cause.getClass().getSimpleName() + ": " + cause.getMessage();
        }
        view.write("Причина: " + message);
        view.write("Повторите попытку.");
    }
}
