package others.spring_practice.aspect;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AspectController {

    @GetMapping("/aspect/{name}")
    public String get(@PathVariable String name) {
        return name;
    }
}
