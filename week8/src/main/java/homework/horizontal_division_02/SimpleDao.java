package homework.horizontal_division_02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.Instant;
import java.util.List;

@Repository
public class SimpleDao {

    @Autowired
    private DataSource dataSource;

    public List<Order> query() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

        String sql = "SELECT * FROM SHOP_ORDER";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
    }

    public void insert() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

        String sql = "INSERT INTO SHOP_ORDER (user_id) VALUES (?)";

        jdbcTemplate.update(sql, Instant.now().getEpochSecond());
    }

    public List<Order> queryByOrderId(long orderId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

        String sql = "SELECT * FROM SHOP_ORDER WHERE order_id = ? ";

        return jdbcTemplate.query(sql, new Long[]{orderId}, new BeanPropertyRowMapper<>(Order.class));
    }

    public void delete() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

        String sql = "DELETE FROM SHOP_ORDER";

        jdbcTemplate.execute(sql);
    }

    public List<Order> queryByUserId(long userId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

        String sql = "SELECT * FROM SHOP_ORDER WHERE user_id = ? ";

        return jdbcTemplate.query(sql, new Long[]{userId}, new BeanPropertyRowMapper<>(Order.class));
    }
}
