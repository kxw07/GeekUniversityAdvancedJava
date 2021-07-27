package homework.insert_order_02.countdownlatch;

import com.google.common.collect.Lists;
import homework.insert_order_02.util.DataSourceUtil;
import homework.insert_order_02.util.OrderUtil;
import homework.insert_order_02.pojo.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InsertOrder {
    public static void main(String[] args) throws InterruptedException {
        List<Order> orderList = OrderUtil.prepareOrder(1_000_000);
        List<List<Order>> partitionList = Lists.partition(orderList, 20_000);

        long start = Instant.now().getEpochSecond();

        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch countDownLatch = new CountDownLatch(partitionList.size());
        for (int i = 0; i < partitionList.size(); i++) {
            executorService.submit(new InsertOrderTask(countDownLatch, partitionList.get(i)));
        }
        countDownLatch.await();

        // 10 partition list, thread pool 20, connection pool 20, cost time(s):57
        // 100 partition list, thread pool 20, connection pool 20, cost time(s):61
        // 20 partition list, thread pool 20, connection pool 20, cost time(s):55
        // 50 partition list, thread pool 30, connection pool 30, cost time(s):58
        // 50 partition list, thread pool 30, connection pool 30, rewriteBatchedStatements=true, cost time(s):17


        long end = Instant.now().getEpochSecond();
        System.out.println("Cost time(s):" + (end - start));

        executorService.shutdown();
    }

    public static void insert(List<Order> orderList) throws SQLException {
        Connection connection = DataSourceUtil.getConnection();

        try {
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
                ps1.addBatch();

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
                ps2.addBatch();

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
    }
}
