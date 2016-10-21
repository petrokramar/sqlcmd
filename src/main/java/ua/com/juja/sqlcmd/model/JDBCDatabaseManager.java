package ua.com.juja.sqlcmd.model;

import java.sql.*;
import java.util.*;

/**
 * Created by Peter on 17.09.2016.
 */
public class JDBCDatabaseManager implements DatabaseManager {

    public static final String DATABASE_URL = "jdbc:postgresql://localhost:8757/";
    private Connection connection;

    @Override
    public void connect(String database, String userName, String password) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Не найден драйвер PostgreSQL", e);
        }
        try {
            if(connection != null){
                connection.close();
            }
            connection = DriverManager.getConnection(
                        DATABASE_URL + database, userName, password);
        } catch (SQLException e) {
            connection = null;
            throw new SQLException(String.format("Не удалось подключиться к базе данных: %s, пользователь: %s",database, userName), e);
        }
    }

    @Override
    public List<DataSet> getTableData(String tableName) {
        List<DataSet> result = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName))
        {
            ResultSetMetaData rsmd = rs.getMetaData();
            while ((rs.next())) {
                DataSet dataSet = new DataSet();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    dataSet.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                result.add(dataSet);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
    }

    @Override
    public int getSize(String tableName) {
        int size = 0;
        try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM " + tableName))
        {
            rs.next();
            size = rs.getInt(1);
            return size;
        } catch (SQLException e) {
            e.printStackTrace();
            return size;
        }
    }

    @Override
    public Set<String> getTableNames(){
        Set<String> tables = new LinkedHashSet<>();
        try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT table_name FROM information_schema.tables" +
                " WHERE table_schema = 'public' AND table_type = 'BASE TABLE'"))
        {
            while ((rs.next())) {
                tables.add(rs.getString("table_name"));
            }
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
            return tables;
        }
    }

    @Override
    public void clear(String tableName) throws SQLException{
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + tableName);
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    @Override
    public void create(String tableName, DataSet input) {
        try(Statement statement = connection.createStatement()) {
            String tableNames = "";
            for(String name: input.getNames()){
                tableNames = tableNames + name + ",";
            }
            tableNames = tableNames.substring(0,tableNames.length() - 1);
            String values = "";
            for(Object value: input.getValues()){
                values += "'" + value.toString() + "',";
            }
            values = values.substring(0,values.length() - 1);
            statement.executeUpdate("INSERT INTO " + tableName + " (" + tableNames + ") VALUES (" + values + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String tableName, int id, DataSet input) {
        String fields = "";
        for(String name: input.getNames()){
            fields += String.format("%s =? ", name) + ",";
        }
        fields = fields.substring(0, fields.length() - 1);
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + tableName + " SET " + fields + " WHERE id=?")) {
            int index = 1;
            for(Object value: input.getValues()){
                preparedStatement.setObject(index++, value);
            }
            preparedStatement.setInt(index, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        Set<String> columns = new LinkedHashSet<>();
        try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT column_name FROM information_schema.columns" +
                " WHERE  table_schema = 'public' and table_name = '" + tableName + "'")){
            while ((rs.next())) {
                columns.add(rs.getString("column_name"));
            }
            return columns;
        } catch (SQLException e) {
            e.printStackTrace();
            return columns;
        }
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }
}
