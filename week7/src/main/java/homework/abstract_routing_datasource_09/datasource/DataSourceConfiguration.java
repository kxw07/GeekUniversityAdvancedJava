package homework.abstract_routing_datasource_09.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.write")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.read")
    public DataSource readDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource(DataSource writeDataSource, DataSource readDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceName.WRITE, writeDataSource);
        targetDataSources.put(DataSourceName.READ, readDataSource);

        return new DynamicDataSource(writeDataSource, targetDataSources);
    }
}
