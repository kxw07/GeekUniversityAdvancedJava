package others.spring_practice.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async
    public void threadSleep() throws InterruptedException {
        System.out.println("bbb");
        Thread.sleep(5000L);
    }
}
