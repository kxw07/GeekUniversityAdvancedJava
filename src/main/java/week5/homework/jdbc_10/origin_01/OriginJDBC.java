package week5.homework.jdbc_10.origin_01;


import java.sql.*;

public class OriginJDBC {
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
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            boolean executeResult = statement.execute("CREATE TABLE IF NOT EXISTS TEST_TABLE ( NAME varchar , PHONE varchar );");
            if (!executeResult) {
                throw new SQLException("SQL EXECUTE FAILED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void writeData() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            boolean executeResult = statement.execute("INSERT INTO TEST_TABLE(NAME, PHONE) VALUES('Jack', '0912-345678');");
            if (!executeResult) {
                throw new SQLException("SQL EXECUTE FAILED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readData() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT NAME, PHONE FROM TEST_TABLE;");
            while (rs.next()) {
                System.out.println("NAME: " + rs.getString("NAME"));
                System.out.println("PHONE: " + rs.getString("PHONE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void truncateTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            boolean executeResult = statement.execute("TRUNCATE TABLE TEST_TABLE;");
            if (!executeResult) {
                throw new SQLException("SQL EXECUTE FAILED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
