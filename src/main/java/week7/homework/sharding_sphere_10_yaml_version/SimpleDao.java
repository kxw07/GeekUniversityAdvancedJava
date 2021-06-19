package week7.homework.sharding_sphere_10_yaml_version;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SimpleDao {

    @Resource
    private DataSource dataSource;

    public List<User> query() throws SQLException, IOException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

        String sql = "SELECT * FROM SHOP_USER";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
