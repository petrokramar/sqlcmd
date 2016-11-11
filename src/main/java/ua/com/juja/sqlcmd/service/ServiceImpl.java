package ua.com.juja.sqlcmd.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ServiceImpl implements Service {
    @Override
    public List<String> commandList() {
        return Arrays.asList("help", "menu", "connect");
    }
}
