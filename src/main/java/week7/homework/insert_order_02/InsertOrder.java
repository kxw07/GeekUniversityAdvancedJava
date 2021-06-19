package week7.homework.insert_order_02;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertOrder {
    public static void main(String[] args) {
        try {
//            insertSingle(); // Cost time(s):606
            insertBatch(); // One executeBatch Cost time(s):161. Every 1000 executeBatch once, Cost time(s):152
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertSingle() throws SQLException {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3316/java_course");
        hikariDataSource.setUsername("root");
        hikariDataSource.setMaximumPoolSize(3);

        List<Order> orderList = prepareOrder(1000000);

        long start = Instant.now().getEpochSecond();
        for (int idx = 0; idx < orderList.size(); idx++) {
            Connection connection = null;

            try {
                connection = hikariDataSource.getConnection();
                connection.setAutoCommit(false);

                String order_sql = " insert into SHOP_ORDER " +
                        " (order_id, order_price, user_id, user_name, deliver_address, is_paid, is_delivered, is_canceled, create_time, update_time)" +
                        " values " +
                        " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

                PreparedStatement ps1 = connection.prepareStatement(order_sql);
                ps1.setString(1, orderList.get(idx).getOrder_id());
                ps1.setDouble(2, orderList.get(idx).getOrder_price());
                ps1.setString(3, orderList.get(idx).getUser_id());
                ps1.setString(4, orderList.get(idx).getUser_name());
                ps1.setString(5, orderList.get(idx).getDeliver_address());
                ps1.setInt(6, orderList.get(idx).getIs_paid());
                ps1.setInt(7, orderList.get(idx).getIs_delivered());
                ps1.setInt(8, orderList.get(idx).getIs_canceled());
                ps1.setLong(9, orderList.get(idx).getCreate_time());
                ps1.setLong(10, orderList.get(idx).getUpdate_time());

                ps1.execute();

                String orderDetail_sql = " insert into SHOP_ORDER_DETAIL " +
                        " (order_id, product_id, product_price_at_order, order_quantity, create_time, update_time) " +
                        " values " +
                        " (?, ?, ?, ?, ?, ?) ";

                PreparedStatement ps2 = connection.prepareStatement(orderDetail_sql);
                ps2.setString(1, orderList.get(idx).getOrderDetailList().get(0).getOrder_id());
                ps2.setString(2, orderList.get(idx).getOrderDetailList().get(0).getProduct_id());
                ps2.setDouble(3, orderList.get(idx).getOrderDetailList().get(0).getProduct_price_at_order());
                ps2.setInt(4, orderList.get(idx).getOrderDetailList().get(0).getOrder_quantity());
                ps2.setLong(5, orderList.get(idx).getOrderDetailList().get(0).getCreate_time());
                ps2.setLong(6, orderList.get(idx).getOrderDetailList().get(0).getUpdate_time());

                ps2.execute();

                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
        long end = Instant.now().getEpochSecond();
        System.out.println("Cost time(s):" + (end - start));
    }

    private static void insertBatch() throws SQLException {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3316/java_course");
        hikariDataSource.setUsername("root");
        hikariDataSource.setMaximumPoolSize(3);

        List<Order> orderList = prepareOrder(1000000);

        long start = Instant.now().getEpochSecond();

        Connection connection = null;

        try {
            connection = hikariDataSource.getConnection();
            connection.setAutoCommit(false);

            String order_sql = " insert into SHOP_ORDER " +
                    " (order_id, order_price, user_id, user_name, deliver_address, is_paid, is_delivered, is_canceled, create_time, update_time)" +
                    " values " +
                    " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            PreparedStatement ps1 = connection.prepareStatement(order_sql);
            for (int idx = 0; idx < orderList.size(); idx++) {
                ps1.setString(1, orderList.get(idx).getOrder_id());
                ps1.setDouble(2, orderList.get(idx).getOrder_price());
                ps1.setString(3, orderList.get(idx).getUser_id());
                ps1.setString(4, orderList.get(idx).getUser_name());
                ps1.setString(5, orderList.get(idx).getDeliver_address());
                ps1.setInt(6, orderList.get(idx).getIs_paid());
                ps1.setInt(7, orderList.get(idx).getIs_delivered());
                ps1.setInt(8, orderList.get(idx).getIs_canceled());
                ps1.setLong(9, orderList.get(idx).getCreate_time());
                ps1.setLong(10, orderList.get(idx).getUpdate_time());

                if (idx != 0 || idx != orderList.size()) {
                    ps1.addBatch();
                }

                if (idx % 1000 == 0) {
                    ps1.executeBatch();
                }
            }
            ps1.executeBatch();

            String orderDetail_sql = " insert into SHOP_ORDER_DETAIL " +
                    " (order_id, product_id, product_price_at_order, order_quantity, create_time, update_time) " +
                    " values " +
                    " (?, ?, ?, ?, ?, ?) ";

            PreparedStatement ps2 = connection.prepareStatement(orderDetail_sql);
            for (int idx = 0; idx < orderList.size(); idx++) {
                ps2.setString(1, orderList.get(idx).getOrderDetailList().get(0).getOrder_id());
                ps2.setString(2, orderList.get(idx).getOrderDetailList().get(0).getProduct_id());
                ps2.setDouble(3, orderList.get(idx).getOrderDetailList().get(0).getProduct_price_at_order());
                ps2.setInt(4, orderList.get(idx).getOrderDetailList().get(0).getOrder_quantity());
                ps2.setLong(5, orderList.get(idx).getOrderDetailList().get(0).getCreate_time());
                ps2.setLong(6, orderList.get(idx).getOrderDetailList().get(0).getUpdate_time());

                if (idx != 0 || idx != orderList.size()) {
                    ps2.addBatch();
                }

                if (idx % 1000 == 0) {
                    ps2.executeBatch();
                }
            }

            ps2.executeBatch();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        long end = Instant.now().getEpochSecond();
        System.out.println("Cost time(s):" + (end - start));
    }

    private static List<Order> prepareOrder(int orderSize) {
        List<Order> orderList = new ArrayList<>();
        for (int i = 1; i <= orderSize; i++) {
            orderList.add(createOrder(i));
        }

        return orderList;
    }

    private static Order createOrder(int index) {
        long now = Instant.now().getEpochSecond();

        OrderDetail orderDetail = OrderDetail.builder()
                .order_id(String.valueOf(index))
                .product_id("life_tool_0001")
                .product_price_at_order(10d)
                .order_quantity(1)
                .create_time(now)
                .update_time(now)
                .build();

        return Order.builder()
                .order_id(String.valueOf(index))
                .order_price(5d)
                .user_id("1623650920341")
                .user_name("test_user_01")
                .deliver_address("One Road")
                .is_paid(0)
                .is_delivered(0)
                .is_canceled(0)
                .create_time(now)
                .update_time(now)
                .orderDetailList(Arrays.asList(orderDetail))
                .build();
    }
}