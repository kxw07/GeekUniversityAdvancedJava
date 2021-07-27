package homework.xa_06;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XAMain {
    private static final Logger logger = LogManager.getLogger(XAMain.class);

    private static DataSource dataSource;

    public static void main(String[] args) throws SQLException, IOException {
        logger.info("test logger");
        System.out.println(LogManager.getContext().getLogger("Root").getLevel());
        dataSource = getDataSource();

        try {
            cleanUpDB();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            initDB();
            prepareDuplicatedOrderId();
            testXA();
            check();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void prepareDuplicatedOrderId() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO test_order (order_id, user_id) VALUES (?, ?)");
            ps.setLong(1, 123L);
            ps.setLong(2, 123L);
            ps.execute();
        }
    }

    public static void testXA() throws SQLException {
        TransactionTypeHolder.set(TransactionType.XA); // Support TransactionType.LOCAL, TransactionType.XA, TransactionType.BASE
        Connection conn = dataSource.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO test_order (order_id, user_id) VALUES (?, ?)");
            ps.setLong(1, 456L); //order_id 456 would be rollback;
            ps.setLong(2,456L);
            ps.executeUpdate();
            ps.setLong(1, 123L); // duplicated order_id
            ps.setLong(2, 123L);
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
            TransactionTypeHolder.clear();
        }
    }

    public static void check() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT order_id, user_id FROM test_order");
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();

            while (resultSet.next()) {
                System.out.println(resultSet.getLong(1) + ":" + resultSet.getLong(2));
            }
        }
    }

    public static void initDB() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("CREATE TABLE test_order (order_id bigint NOT NULL, user_id bigint DEFAULT NULL, PRIMARY KEY(order_id))");
            ps.execute();
        }
    }

    public static void cleanUpDB() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DROP TABLE test_order ");
            ps.execute();
        }
    }

    public static DataSource getDataSource() throws SQLException, IOException {
        Resource resource = new ClassPathResource("application-week8-06.yml");
        return YamlShardingSphereDataSourceFactory.createDataSource(resource.getFile());
    }
}
