package week8.homework.xa_06;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.ReadwriteSplittingRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.rule.ReadwriteSplittingDataSourceRuleConfiguration;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class XAMain {

    private static DataSource dataSource;

    public static void main(String[] args) throws SQLException {
        dataSource = getDataSource();

        initDB();
        try {
            preparePrimaryOrder();
            testXA();
        } finally {
            check();
            cleanUpDB();
        }
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

    public static void check() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT order_id, user_id FROM shop_order");
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();

            while (resultSet.next()) {
                System.out.println(resultSet.getLong(0));
                System.out.println(resultSet.getLong(1));
            }
        }
    }

    public static void initDB() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("CREATE TABLE shop_order (order_id bigint NOT NULL, user_id bigint DEFAULT NULL, PRIMARY KEY(order_id))");
            ps.execute();
        }
    }

    public static void cleanUpDB() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DROP TABLE shop_order ");
            ps.execute();
        }
    }

//    public static DataSource getDataSource() throws SQLException {
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:13306/sharding_db");
//        hikariDataSource.setUsername("root");
//        hikariDataSource.setPassword("root");
//        hikariDataSource.setMaximumPoolSize(10);
//
//        Map<String, ShardingSphereAlgorithmConfiguration> loadBalancers = new HashMap<>();
//        loadBalancers.put("load_balancer", new ShardingSphereAlgorithmConfiguration("ROUND_ROBIN", new Properties()));
//        ReadwriteSplittingRuleConfiguration ruleConfig = new ReadwriteSplittingRuleConfiguration(Collections.emptyList(), loadBalancers);
//
//        return ShardingSphereDataSourceFactory.createDataSource(hikariDataSource, Collections.singleton(ruleConfig), new Properties());
//    }

    public static DataSource getDataSource() throws SQLException {
        HikariDataSource master = new HikariDataSource();
        master.setJdbcUrl("jdbc:mysql://localhost:13306/sharding_db?characterEncoding=utf8");
        master.setUsername("root");
        master.setPassword("root");

        HikariDataSource slave1 = new HikariDataSource();
        slave1.setJdbcUrl("jdbc:mysql://localhost:13306/sharding_db?characterEncoding=utf8");
        slave1.setUsername("root");
        slave1.setPassword("root");

        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", master);
        dataSourceMap.put("slave1", slave1);


        ReadwriteSplittingDataSourceRuleConfiguration dataSourceConfig = new ReadwriteSplittingDataSourceRuleConfiguration(
                "ds", "", "master", Arrays.asList("slave1"), "load_balancer");

        Map<String, ShardingSphereAlgorithmConfiguration> loadBalancers = new HashMap<>();
        loadBalancers.put("load_balancer", new ShardingSphereAlgorithmConfiguration("ROUND_ROBIN", new Properties()));

        Properties properties = new Properties();
        properties.setProperty("sql-show", "true");

        ReadwriteSplittingRuleConfiguration ruleConfig = new ReadwriteSplittingRuleConfiguration(Collections.singleton(dataSourceConfig), loadBalancers);
        return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(ruleConfig), properties);
    }
}
