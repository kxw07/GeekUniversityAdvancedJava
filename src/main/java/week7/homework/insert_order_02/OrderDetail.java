package week7.homework.insert_order_02;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetail {
    private String order_id;
    private String product_id;
    private double product_price_at_order;
    private int order_quantity;
    private long create_time;
    private long update_time;
}