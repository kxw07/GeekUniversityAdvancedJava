package week7.homework.abstract_routing_datasource_09;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import week7.homework.abstract_routing_datasource_09.datasource.DynamicDataSource;

import javax.annotation.Resource;
import java.sql.Connection;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SimpleDao {

    @Resource
    private DynamicDataSource dynamicDataSource;

    public Order getOrder(String orderId) {
        String sql = " SELECT * FROM SHOP_ORDER WHERE ORDER_ID = :order_id";

        Map<String, String> paramMap = new HashMap();
        paramMap.put("order_id", orderId);

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dynamicDataSource);
        try {
            Connection conn = jdbcTemplate.getJdbcTemplate().getDataSource().getConnection();
            System.out.println("URL:" + conn.getMetaData().getURL());
            conn.close();
            return jdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<>(Order.class));
        } catch (Exception e) {
            System.out.println("query order error");
            e.printStackTrace();
            return Order.builder().build();
        }
    }

    public Order insertOrder() {
        long now = Instant.now().getEpochSecond();
        Order order = Order.builder()
                .order_id(String.valueOf(now))
                .user_id("1623650920341")
                .order_price(5d)
                .deliver_address("One Road")
                .is_paid(0)
                .is_delivered(0)
                .is_canceled(0)
                .create_time(now)
                .update_time(now)
                .build();

        String sql = " insert into SHOP_ORDER " +
                " (order_id, user_id, order_price, deliver_address, is_paid, is_delivered, is_canceled, create_time, update_time)" +
                " values " +
                " (:order_id, :user_id, :order_price, :deliver_address, :is_paid, :is_delivered, :is_canceled, :create_time, :update_time)";


        Map<String, Object> paramMap = new HashMap();
        paramMap.put("order_id", order.getOrder_id());
        paramMap.put("user_id", order.getUser_id());
        paramMap.put("order_price", order.getOrder_price());
        paramMap.put("deliver_address", order.getDeliver_address());
        paramMap.put("is_paid", order.getIs_paid());
        paramMap.put("is_delivered", order.getIs_delivered());
        paramMap.put("is_canceled", order.getIs_canceled());
        paramMap.put("create_time", order.getCreate_time());
        paramMap.put("update_time", order.getUpdate_time());

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dynamicDataSource);
        try {
            Connection conn = jdbcTemplate.getJdbcTemplate().getDataSource().getConnection();
            System.out.println("URL:" + conn.getMetaData().getURL());
            conn.close();

            jdbcTemplate.update(sql, paramMap);

            return order;
        } catch (Exception e) {
            System.out.println("query order error");
            e.printStackTrace();
            return Order.builder().build();
        }
    }
}
