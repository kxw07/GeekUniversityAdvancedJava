package others.spring_practice.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/kai")
public class WebfluxController {

    private final WebfluxService webfluxService;

    public WebfluxController(WebfluxService webfluxService) {
        this.webfluxService = webfluxService;
    }

    @GetMapping("/mvc")
    public String getNormal() {
        return webfluxService.getNormal();
    }

    @GetMapping("/webflux")
    public Mono<String> getWebFlux() {
        return webfluxService.getWebFlux();
    }

}
