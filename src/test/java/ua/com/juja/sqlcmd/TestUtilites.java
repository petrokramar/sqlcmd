package ua.com.juja.sqlcmd;

import ua.com.juja.sqlcmd.controller.PropertyHandler;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class TestUtilites {
    public static String formatOutput(ByteArrayOutputStream out) {
        try {
            String result = new String(out.toByteArray(), "UTF-8").replaceAll("\r\n", "\n");
            out.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

    public static String getConnectParameters() {
        final PropertyHandler settings = PropertyHandler.getInstance();
        return String.format("connect|%s|%s|%s", settings.getProperty("database.name"),
                settings.getProperty("database.user.name"), settings.getProperty("database.user.password"));
    }
}
