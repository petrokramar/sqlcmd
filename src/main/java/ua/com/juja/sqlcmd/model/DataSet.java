package ua.com.juja.sqlcmd.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataSet {
    private Map<String, Object> data = new LinkedHashMap<>();

    public void put(String name, Object value) {
        data.put(name, value);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public List<Object> getValues() {
        List<Object> result = new ArrayList<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    public List<String> getNames() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            result.add(entry.getKey());
        }
        return result;
    }

    public Object get(String name) {
        return data.get(name);
    }

    @Override
    public String toString() {
        return String.format("{names: %s, values: %s}", getNames(), getValues());
    }
}
