package week7.homework.insert_order_02.util;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceUtil {
    private final static HikariDataSource hikariDataSource;

    static {
        hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3316/java_course");
        hikariDataSource.setUsername("root");
        hikariDataSource.setMaximumPoolSize(30);
    }

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
