package ua.com.juja.sqlcmd.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Peter on 18.09.2016.
 */
public class DataSet {

    static class Data{//TODO почему статик?
        private String name;
        private Object value;

        public Data(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }
    }

    public Map<String, Object> data = new LinkedHashMap<>();

    public void put(String name, Object value) {
        data.put(name, value);
    }

    public List<Object> getValues(){
        List<Object> result = new ArrayList<>();
        for(Map.Entry<String, Object> entry: data.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }

    public List<String> getNames(){
        List<String> result = new ArrayList<>();
        for(Map.Entry<String, Object> entry: data.entrySet()){
            result.add(entry.getKey());
        }
        return result;
    }

    public Object get(String name) {
        return data.get(name);
    }

    public void updateFrom(DataSet newValue) {
        for(Map.Entry<String, Object> entry: data.entrySet()){
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public String toString() {
        return "{names: " + getNames() + ", values: " + getValues() + "}";
    }
}
