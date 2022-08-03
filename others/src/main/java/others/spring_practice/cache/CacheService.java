package others.spring_practice.cache;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    @SneakyThrows
    @Cacheable(cacheNames="user", key="#id")
    public String find(String id) {
        Thread.sleep(3000);
        return "result";
    }

    @CacheEvict(cacheNames="user", key="#id")
    public String evict(String id) {
        return "success";
    }

    @SneakyThrows
    @CachePut(cacheNames = "user", key="#id")
    public String put(String id) {
        Thread.sleep(3000);
        return "put";
    }
}
