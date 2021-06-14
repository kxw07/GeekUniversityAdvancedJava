package week7.homework;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class InsertOrder_02 {
    public static void main(String[] args) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3316/java_course");
        hikariDataSource.setUsername("root");
        hikariDataSource.setMaximumPoolSize(3);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
        try {
            String sql = "SELECT * FROM SHOP_ORDER";
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
            System.out.println(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
