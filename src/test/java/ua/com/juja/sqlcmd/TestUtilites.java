package ua.com.juja.sqlcmd;

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
}
