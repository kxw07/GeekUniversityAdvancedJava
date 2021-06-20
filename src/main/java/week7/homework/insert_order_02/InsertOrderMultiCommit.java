package week7.homework.insert_order_02;

import week7.homework.insert_order_02.pojo.Order;
import week7.homework.insert_order_02.util.DataSourceUtil;
import week7.homework.insert_order_02.util.OrderUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

public class InsertOrderMultiCommit {
    public static void main(String[] args) {
        try {
            List<Order> orderList = OrderUtil.prepareOrder(1000000);

            long start = Instant.now().getEpochSecond();

            insert(orderList); // Cost time(s):606

            long end = Instant.now().getEpochSecond();
            System.out.println("Cost time(s):" + (end - start));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insert(List<Order> orderList) throws SQLException {
        for (int idx = 0; idx < orderList.size(); idx++) {
            Connection connection = DataSourceUtil.getConnection();

            try {
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
    }
}
