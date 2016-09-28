package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.sql.SQLException;

/**
 * Created by Peter on 20.09.2016.
 */
public class Connect implements Command {

    private View view;
    private DatabaseManager manager;

    private static final String COMMAND_SAMPLE = "connect|sqlcmd|postgres|123456";

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
        String[] data = command.split("\\|");
        if (data.length != count()) {
            throw new IllegalArgumentException(
                String.format("Неверно количество параметров разделенных знаком " +
                "'|', ожидается %s, но есть: %s", count(), data.length));
        }
        String databaseName = data[1];
        String userName = data[2];
        String password = data[3];
        try {
            manager.connect(databaseName, userName, password);
            view.write("Подключились.");
        } catch (SQLException e) {
            view.write(String.format("Ошибка подключения к базе данных '%s' по причине: %", databaseName, e.getMessage()));
        }
    }

    private int count() {
        return COMMAND_SAMPLE.split("\\|").length;
    }

}
