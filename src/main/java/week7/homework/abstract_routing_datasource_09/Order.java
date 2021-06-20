package week7.homework.abstract_routing_datasource_09;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import week7.homework.insert_order_02.pojo.OrderDetail;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String order_id;
    private String user_id;
    private String remark;
    private double order_price;
    private String deliver_address;
    private int is_paid;
    private int is_delivered;
    private int is_canceled;
    private long create_time;
    private long update_time;

    private List<OrderDetail> orderDetailList;
}
