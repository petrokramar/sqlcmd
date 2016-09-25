package ua.com.juja.sqlcmd.view;

import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Peter on 19.09.2016.
 */
public class Console implements View {
    @Override
    public void write(String message) {
//        System.out.println("abcd");
        System.out.println(message);
//            System.out.println(message.getBytes());
    }

    @Override
    public String read() {
        try {
            Scanner scanner = new Scanner(System.in);
//            String line = scanner.nextLine();
//            scanner.close();
//            return line;
            return scanner.nextLine();
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
