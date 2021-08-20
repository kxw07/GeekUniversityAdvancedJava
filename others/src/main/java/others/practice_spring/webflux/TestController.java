package others.practice_spring.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/kai")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/mvc")
    public String getNormal() {
        return testService.getNormal();
    }

    @GetMapping("/webflux")
    public Mono<String> getWebFlux() {
        return testService.getWebFlux();
    }

}
