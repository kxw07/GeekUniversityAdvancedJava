package homework.abstract_routing_datasource_09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    private SimpleService simpleService;

    @Autowired
    public SimpleController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @RequestMapping("/order/{orderId}")
    public Order test(@PathVariable String orderId) {
        return this.simpleService.getOrder(orderId);
    }

    @RequestMapping("/order/insert")
    public Order test() {
        return this.simpleService.insertOrder();
    }
}
