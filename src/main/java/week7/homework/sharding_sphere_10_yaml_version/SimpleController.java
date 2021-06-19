package week7.homework.sharding_sphere_10_yaml_version;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class SimpleController {

    private SimpleService simpleService;

    public SimpleController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @RequestMapping("/test")
    public List<User> test() throws SQLException, IOException {
        return this.simpleService.query();
    }
}
