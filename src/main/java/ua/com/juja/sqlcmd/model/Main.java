package ua.com.juja.sqlcmd.model;

/**
 * Created by Peter on 17.09.2016.
 */
public class Main {

//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        Class.forName("org.postgresql.Driver");
//        Connection connection = DriverManager.getConnection(
//                    "jdbc:postgresql://localhost:8757/sqlcmd", "postgres", "123456");
//
//
//        String sql= "INSERT INTO users (name, password) VALUES ('Petro3', '77')";
//        update(connection, sql);
//
//        readAll(connection);
//
//        Statement statement;
//        ResultSet rs;
//
//        statement = connection.createStatement();
//        statement.executeUpdate("DELETE FROM users WHERE name='Petro3'");
//        statement.close();
//
//        statement = connection.createStatement();
//        statement.executeUpdate("INSERT INTO users (name, password) VALUES ('Petro4', '77')");
//        statement.close();
//
//        readAll(connection);
//
//        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET password=? WHERE name=?");
//        preparedStatement.setString(1,"333");
//        preparedStatement.setString(2,"Petro4");
//        preparedStatement.executeUpdate();
//        preparedStatement.close();
//
//        readAll(connection);
//
//        connection.close();
//
//    }
//
//    private static void readAll(Connection connection) throws SQLException {
//        Statement statement = connection.createStatement();
//        ResultSet rs = statement.executeQuery("SELECT * FROM users");
//        while ((rs.next())) {
//            System.out.println("name: " + rs.getString("name") + " password: " + rs.getString("password"));
//        }
//        System.out.println("________________");
//        rs.close();
//        statement.close();
//    }
//
//    private static void update(Connection connection, String sql) throws SQLException {
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(sql);
//        statement.close();
//    }
}
