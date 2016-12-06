package ua.com.juja.sqlcmd.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;
import ua.com.juja.sqlcmd.controller.PropertyHandler;

import java.sql.*;
import java.util.*;

@Component
public class PostgreJdbcTemplateSQLManager implements DatabaseManager {
    private final String DATABASE_JDBC_DRIVER = "jdbc:postgresql://";
    private Connection connection;
    private JdbcTemplate template;

    @Override
    public void connect(String databaseName, String userName, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DatabaseManagerException("PostgreSQL driver not found", e);
        }
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection(getJdbcUrl() + databaseName, userName, password);
            template = new JdbcTemplate(new SingleConnectionDataSource(connection, false));
        } catch (SQLException e) {
            connection = null;
            template = null;
            throw new DatabaseManagerException(
                    String.format("Failed to connect to database: %s, user: %s", databaseName, userName), e);
        }
    }

    private String getJdbcUrl() {
        PropertyHandler settings = PropertyHandler.getInstance();
        return String.format("%s%s:%s/", DATABASE_JDBC_DRIVER,
                settings.getProperty("database.server.name"),
                settings.getProperty("database.port"));
    }

    @Override
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseManagerException("Failed to disconnect from database", e);
            }
            connection = null;
            template = null;
        } else {
            throw new DatabaseManagerException("Disconnect failed. You are not connected to any database.");
        }
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }

    @Override
    public String currentDatabase() {
        return template.queryForObject("SELECT current_database()", String.class);
    }

    @Override
    public void createDatabase(String databaseName) {
        template.execute(String.format("CREATE DATABASE %s", databaseName));
    }

    @Override
    public void dropDatabase(String databaseName) {
        template.execute(String.format("DROP DATABASE IF EXISTS %s", databaseName));
    }

    @Override
    public Set<String> getDatabaseNames() {
        return new LinkedHashSet<>(template.query("SELECT datname AS db_name FROM pg_database " +
                        "WHERE datistemplate = false ORDER BY datname",
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getString("db_name");
                    }
                }
        ));
    }

    @Override
    public void createTable(String tableName, String query) {
        template.execute(String.format(" CREATE TABLE public.%s (%s)", tableName, query));
    }

    @Override
    public void dropTable(String tableName) {
        template.execute(String.format("DROP TABLE IF EXISTS public.%s", tableName));
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        return new LinkedHashSet<>(template.query(String.format("SELECT column_name FROM information_schema.columns" +
                        " WHERE  table_schema = 'public' and table_name = '%s'", tableName),
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getString("column_name");
                    }
                }
        ));
    }

    @Override
    //TODO tables without id
    public List<DataSet> getTableData(String tableName) {
        return template.query(String.format("SELECT * FROM %s ORDER BY id", tableName),
            new RowMapper<DataSet>() {
                public DataSet mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    DataSet dataSet = new DataSet();
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        dataSet.put(rsmd.getColumnName(i + 1), rs.getObject(i + 1));
                    }
                    return dataSet;
                }
            }
        );
    }

    @Override
    public int getTableSize(String tableName) {
        return template.queryForObject(String.format("SELECT COUNT(*) FROM %s", tableName),Integer.class);
    }

    @Override
    public Set<String> getTableNames() {
        return new LinkedHashSet<>(template.query("SELECT table_name FROM information_schema.tables" +
                " WHERE table_schema = 'public' AND table_type = 'BASE TABLE'",
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getString("table_name");
                    }
                }
        ));
    }

    @Override
    public void clearTable(String tableName) {
        template.execute(String.format("DELETE FROM %s", tableName));
    }

    @Override
    public boolean existRecord(String tableName, String field, String parameter) {
        return template.queryForObject(String.format(
                "SELECT COUNT(*) FROM %s WHERE %s = %s", tableName, field, parameter),Integer.class) != 0;
    }

    @Override
    public DataSet getRecordData(String tableName, int id) {
        DataSet dataSet = new DataSet();
        Map<String, Object> data = template.queryForMap(String.format(
                "SELECT * FROM %s WHERE id = %s", tableName, id));
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            dataSet.put(entry.getKey(), entry.getValue());
        }
        return dataSet;
    }

    @Override
    public void createRecord(String tableName, DataSet input) {
        String columns = "";
        for (String name : input.getNames()) {
            columns = columns + name + ",";
        }
        columns = columns.substring(0, columns.length() - 1);
        String values = "";
        for (Object value : input.getValues()) {
            values += "'" + value.toString() + "',";
        }
        values = values.substring(0, values.length() - 1);
        template.execute(String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, values));
    }

    @Override
    public void updateRecord(String tableName, int id, DataSet input) {
        String fields = "";
        for (Map.Entry<String, Object> entry: input.getData().entrySet()) {
            fields += String.format("%s ='%s' ", entry.getKey(), entry.getValue()) + ",";
        }
        fields = fields.substring(0, fields.length() - 1);
        template.execute(String.format("UPDATE %s SET %s WHERE id=%s", tableName, fields, id));
    }

    @Override
    public void deleteRecord(String tableName, int id) {
        template.execute(String.format("DELETE FROM %s WHERE id=%s", tableName, id));
    }

    @Override
    public List<DataSet> executeQuery(String query) {
        if (query.toLowerCase().startsWith("select")) {
            return template.query(query,
                    new RowMapper<DataSet>() {
                        public DataSet mapRow(ResultSet rs, int rowNum) throws SQLException {
                            ResultSetMetaData rsmd = rs.getMetaData();
                            DataSet dataSet = new DataSet();
                            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                                dataSet.put(rsmd.getColumnName(i + 1), rs.getObject(i + 1));
                            }
                            return dataSet;
                        }
                    }
            );
        } else {
            template.execute(query);
            return new ArrayList<>();
        }
    }
}
