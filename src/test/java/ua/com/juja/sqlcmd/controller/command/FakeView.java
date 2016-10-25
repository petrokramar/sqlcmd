package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.view.View;

public class FakeView implements View {

    private String messages = "";
    private String input = null;

    public void adrrRead(String input) {
        this.input = input;
    }

    @Override
    public void write(String message) {
        messages += message + "\n";
    }

    @Override
    public String read() {
        if (this.input == null) {
            throw new IllegalStateException("Input не проинициализирован.");
        }
        String result = this.input;
        this.input = null;
        return result;
    }

    public String getContent() {
        return messages;
    }
}
