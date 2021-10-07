package others.spring_practice.webflux;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TestService {
    public String getNormal() {
        return "Spring mvc";
    }

    public Mono<String> getWebFlux() {
        return Mono.just("Spring webflux");
    }
}
