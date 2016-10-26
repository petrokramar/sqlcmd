package ua.com.juja.sqlcmd.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class DataSetTest {

    @Test
    public void testGet(){
        DataSet data = new DataSet();
        data.put("one", "first");
        data.put("two", "second");
        assertEquals("first", data.get("one"));
        assertEquals("second", data.get("two"));
    }
}
