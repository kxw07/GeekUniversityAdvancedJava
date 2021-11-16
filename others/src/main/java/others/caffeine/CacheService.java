package others.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class CacheService {
    private static final LoadingCache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .refreshAfterWrite(3, TimeUnit.SECONDS)
            .build(key -> getData());

    private static String getData() {
        System.out.println(Instant.now().toString() + ", Data is getting");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Instant.now().toString();
    }

    public static String get() {
        return cache.get("key");
    }
}
