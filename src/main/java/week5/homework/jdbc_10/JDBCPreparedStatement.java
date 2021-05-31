package week5.homework.jdbc_10;


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

            String insertData = "INSERT INTO TEST_TABLE(NAME, PHONE) VALUES(?, ?);";
            preparedStatement = connection.prepareStatement(insertData);
            preparedStatement.setString(1, "John");
            preparedStatement.setString(2, "88888888");
            preparedStatement.addBatch();

            preparedStatement.setString(1, "Leo");
            preparedStatement.setString(2, "77777777");
            preparedStatement.addBatch();

            preparedStatement.setString(1, "Linda");
            preparedStatement.setString(2, "66666666");
            preparedStatement.addBatch();


            int[] numUpdates = preparedStatement.executeBatch();
            for (int i = 0; i < numUpdates.length; i++) {
                if (numUpdates[i] <= 0)
                    System.out.println("Execution " + i + ": unknown number of rows updated");
            }

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
