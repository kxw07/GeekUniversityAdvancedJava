package week8.homework.xa_06;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class XAMain {

    private static DataSource dataSource = getDataSource();

    public static void main(String[] args) throws SQLException {
        preparePrimaryOrder();
        testXA();
    }

    public static void preparePrimaryOrder() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO shop_order (order_id, user_id) VALUES (?, ?)");
            ps.setLong(1, 123L);
            ps.setLong(2, 123L);
            ps.execute();
            conn.commit();
        }
    }

    public static void testXA() throws SQLException {
        TransactionTypeHolder.set(TransactionType.XA); // Support TransactionType.LOCAL, TransactionType.XA, TransactionType.BASE
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO shop_order (order_id, user_id) VALUES (?, ?)");
            ps.setLong(1, 456L); //order_id 456 would be rollback;
            ps.setLong(2,456L);
            ps.addBatch();
            ps.setLong(1, 123L); // duplicated order_id
            ps.setLong(2, 123L);
            ps.addBatch();
            ps.executeBatch();
            conn.commit();
        }

        TransactionTypeHolder.clear();
    }

    public static DataSource getDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:13306/sharding_db");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");
        hikariDataSource.setMaximumPoolSize(10);

        return hikariDataSource;
    }
}
