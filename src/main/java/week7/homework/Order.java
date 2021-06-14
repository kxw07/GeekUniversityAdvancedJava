package week7.homework;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private String order_id;
    private String user_id;
    private String remark;
    private double order_price;
    private String deliver_address;
    private int is_paid;
    private int is_canceled;
    private int is_delivered;
    private long create_time;
    private long update_time;
}
