package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.view.View;

public class Unsupported implements Command{

    private View view;

    public Unsupported(View view) {
        this.view = view;
    }


    @Override
    public boolean canProcess(String command) {
        return true;
    }

    @Override
    public void process(String command) {
        view.write(String.format("Несуществующая команда: %s", command));
    }

    @Override
    public String format() {
        return "";
    }

    @Override
    public String description() {
        return "";
    }

}
