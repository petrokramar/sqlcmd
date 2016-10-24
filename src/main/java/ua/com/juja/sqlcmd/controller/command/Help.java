package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.view.View;

public class Help implements Command{

    private Command[] commands;
    private View view;
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    public Help(View view) {
        this.view = view;
    }

    public void setCommands(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public boolean canProcess(String command) {
        return "help".equals(command);
    }

    @Override
    public void process(String command) {//TODO alphabete sorting
        view.write("Список команд");
        for(Command item: commands){
            if(!"".equals(item.format())){
                view.write("\t" + ANSI_BLUE + item.format());
                view.write("\t\t" + ANSI_RESET + item.description());
            }
        }
    }

    @Override
    public String format() {
        return "help";
    }

    @Override
    public String description() {
        return "помощь";
    }

}
