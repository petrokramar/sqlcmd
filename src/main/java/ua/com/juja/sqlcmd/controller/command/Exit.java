package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.view.View;

public class Exit implements Command{

    private View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return "exit".equals(command);
    }

    @Override
    public void process(String command) {
        view.write("Good luck!");
        throw new ExitException();
    }

    @Override
    public String format() {
        return "exit";
    }

    @Override
    public String description() {
        return "exit";
    }

}
