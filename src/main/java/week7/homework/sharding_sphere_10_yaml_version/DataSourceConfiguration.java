package week7.homework.sharding_sphere_10_yaml_version;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() throws SQLException {
        HikariDataSource master = new HikariDataSource();
        master.setJdbcUrl("jdbc:mysql://localhost:3316/java_course");
        master.setUsername("root");
        master.setMaximumPoolSize(3);

        HikariDataSource slave1 = new HikariDataSource();
        slave1.setJdbcUrl("jdbc:mysql://localhost:3326/java_course");
        slave1.setUsername("root");
        slave1.setMaximumPoolSize(3);

        HikariDataSource slave2 = new HikariDataSource();
        slave2.setJdbcUrl("jdbc:mysql://localhost:3326/java_course");
        slave2.setUsername("root");
        slave2.setMaximumPoolSize(3);

        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", master);
        dataSourceMap.put("slave1", slave1);
        dataSourceMap.put("slave2", slave2);


        List<ReplicaQueryDataSourceRuleConfiguration> configurations = new ArrayList<>();
        configurations.add(new ReplicaQueryDataSourceRuleConfiguration("ds", "master", Arrays.asList("slave1", "slave2"), "load_balancer"));

        Map<String, ShardingSphereAlgorithmConfiguration> loadBalancers = new HashMap<>();
        loadBalancers.put("load_balancer", new ShardingSphereAlgorithmConfiguration("ROUND_ROBIN", new Properties()));

        ReplicaQueryRuleConfiguration ruleConfiguration = new ReplicaQueryRuleConfiguration(configurations, loadBalancers);

        Properties properties = new Properties();
        properties.setProperty("sql-show", "true");

        return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Arrays.asList(ruleConfiguration), properties);
    }
}
