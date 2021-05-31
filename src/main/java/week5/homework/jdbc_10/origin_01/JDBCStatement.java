package week5.homework.jdbc_10.origin_01;


import java.sql.*;

public class JDBCStatement {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "aaaa1234";

    public static void main(String[] args) {
        createTable();
        writeData();
        readData();
//        truncateTable();
    }

    public static void createTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement();) {
            statement.execute("CREATE TABLE IF NOT EXISTS TEST_TABLE ( NAME varchar , PHONE varchar );");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void writeData() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement();) {
            statement.execute("INSERT INTO TEST_TABLE(NAME, PHONE) VALUES('Jack', '0912-345678');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readData() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement();) {
            ResultSet rs = statement.executeQuery("SELECT NAME, PHONE FROM TEST_TABLE;");
            while (rs.next()) {
                System.out.println("NAME: " + rs.getString("NAME"));
                System.out.println("PHONE: " + rs.getString("PHONE"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void truncateTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement();) {
            statement.execute("TRUNCATE TABLE TEST_TABLE;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
