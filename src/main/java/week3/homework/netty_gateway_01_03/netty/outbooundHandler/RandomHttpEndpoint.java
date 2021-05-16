package week3.homework.netty_gateway_01_03.netty.outbooundHandler;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpoint {
    private final List<String> endpoint;

    public RandomHttpEndpoint(List<String> endpoint) {
        this.endpoint = endpoint;
    }

    public String getRandomEndpoint() {
        int size = endpoint.size();
        Random random = new Random(System.currentTimeMillis());
        return endpoint.get(random.nextInt(size));
    }
}
