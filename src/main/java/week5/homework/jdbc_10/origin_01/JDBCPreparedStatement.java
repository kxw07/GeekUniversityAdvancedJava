package week5.homework.jdbc_10.origin_01;


import java.sql.*;

public class JDBCPreparedStatement {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "aaaa1234";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            String createTable = "CREATE TABLE IF NOT EXISTS TEST_TABLE ( NAME varchar , PHONE varchar );";
            preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();

            String insertData = "INSERT INTO TEST_TABLE(NAME, PHONE) VALUES(?, ?);";
            preparedStatement = connection.prepareStatement(insertData);
            preparedStatement.setString(1, "John");
            preparedStatement.setString(2, "88888888");
            preparedStatement.execute();

            connection.commit();

            connection.setAutoCommit(true);
            String readData = "SELECT NAME, PHONE FROM TEST_TABLE;";
            preparedStatement = connection.prepareStatement(readData);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("NAME: " + rs.getString("NAME"));
                System.out.println("PHONE: " + rs.getString("PHONE"));
            }
            rs.close();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.close();
            preparedStatement.close();
        }
    }
}
