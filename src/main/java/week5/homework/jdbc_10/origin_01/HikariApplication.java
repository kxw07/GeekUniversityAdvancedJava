package week5.homework.jdbc_10.origin_01;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class HikariApplication {

    public static void main(String[] args) throws SQLException {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariDataSource.setUsername("postgres");
        hikariDataSource.setPassword("aaaa1234");
        hikariDataSource.setMaximumPoolSize(5);

        Connection connection = hikariDataSource.getConnection();
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
