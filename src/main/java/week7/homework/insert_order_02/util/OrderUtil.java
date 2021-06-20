package week7.homework.insert_order_02.util;

import week7.homework.insert_order_02.pojo.Order;
import week7.homework.insert_order_02.pojo.OrderDetail;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderUtil {
    public static List<Order> prepareOrder(int orderSize) {
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
