package homework.horizontal_division_02;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SimpleController {

    private SimpleService simpleService;

    public SimpleController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @RequestMapping("/query")
    public List<Order> query() {
        return this.simpleService.query();
    }

    @RequestMapping("/query/order/{orderId}")
    public List<Order> queryByOrderId(@PathVariable long orderId) {
        return this.simpleService.queryByOrderId(orderId);
    }

    @RequestMapping("/query/user/{userId}")
    public List<Order> queryByUserId(@PathVariable long userId) {
        return this.simpleService.queryByUserId(userId);
    }

    @RequestMapping("/insert")
    public String insert() {
        try {
            this.simpleService.insert();
            return "insert successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "insert failed.";
        }
    }

    @RequestMapping("/delete")
    public String delete() {
        try {
            this.simpleService.delete();
            return "delete successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "detele failed.";
        }
    }
}
