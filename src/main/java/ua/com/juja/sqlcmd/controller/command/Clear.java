package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;

public class Clear implements Command {

    private View view;
    private DatabaseManager manager;

    public Clear(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }


    @Override
    public boolean canProcess(String command) {
        return command.startsWith("clear|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        if(data.length !=2){
            throw new IllegalArgumentException(String.format("Incorrect command format. The correct format: 'clear|tableName',\n" +
                    "your command: %s", command));
        }
        String tableName = data[1];
        view.write(String.format("To confirm clearing table '%s' type 'yes'.", tableName));
        if("yes".equals(view.read().trim())){
            try {
                manager.clear(tableName);
                view.write(String.format("Table '%s' is cleared", tableName));
            } catch (SQLException e) {
                view.write(String.format("Table clear error '%s' by reason: %s", tableName, e.getMessage()));
            }
        }else{
            view.write(String.format("Clearing table '%s' cancelled.", tableName));
        }
    }

    @Override
    public String format() {
        return "clear|tableName";
    }

    @Override
    public String description() {
        return "clearing table tableName";
    }

}
