package homework.sharding_sphere_10_java_version;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SimpleDao {

    private DataSource dataSource;

    public SimpleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> query() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

        String sql = "SELECT * FROM SHOP_USER";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
