package homework.insert_order_02.countdownlatch;

import homework.insert_order_02.pojo.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class InsertOrderTask implements Runnable {
    private final CountDownLatch countDownLatch;
    private List<Order> orderList;

    public InsertOrderTask(CountDownLatch countDownLatch, List<Order> orderList) {
        this.countDownLatch = countDownLatch;
        this.orderList = orderList;
    }

    @Override
    public void run() {
        try {
            try {
                InsertOrder.insert(orderList); // Cost time(s):66
            } catch (SQLException e) {
                e.printStackTrace();
            }

            this.countDownLatch.countDown();
            System.out.println("My task is done:" + Thread.currentThread().getName());
        } catch (Exception e) {
            System.out.println("Task run error:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
