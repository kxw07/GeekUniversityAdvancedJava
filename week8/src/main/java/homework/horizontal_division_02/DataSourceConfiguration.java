package homework.horizontal_division_02;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:13306/sharding_db");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");

        return hikariDataSource;
    }
}
