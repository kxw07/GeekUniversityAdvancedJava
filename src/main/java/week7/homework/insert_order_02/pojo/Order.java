package week7.homework.insert_order_02.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Order {
    private String order_id;
    private String remark;
    private double order_price;
    private String user_id;
    private String user_name;
    private String email;
    private String phone;
    private String deliver_address;
    private int is_paid;
    private int is_delivered;
    private int is_canceled;
    private long create_time;
    private long update_time;

    private List<OrderDetail> orderDetailList;
}
