package others.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.Assert;

import java.util.concurrent.TimeUnit;

public class CaffeineMain {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        expireAfterWrite();
        loadingCacheWithRefreshAfterWrite();
    }

    private static void loadingCacheWithRefreshAfterWrite() throws InterruptedException {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .refreshAfterWrite(3, TimeUnit.SECONDS)
                .maximumSize(100)
                .build(key -> loadingCache());

        Assert.assertEquals("value1", cache.get("key2"));

        Thread.sleep(4000);
        Assert.assertEquals("value1", cache.get("key2"));

        Thread.sleep(4000);
        Assert.assertEquals("value2", cache.get("key2"));
    }

    private static String loadingCache() {
        return "value" + ++counter;
    }


    public static void expireAfterWrite() throws InterruptedException {
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .maximumSize(100)
                .build();

        cache.put("key1", "value1");
        Assert.assertEquals("value1", cache.getIfPresent("key1"));

        Thread.sleep(3000);
        Assert.assertNull(cache.getIfPresent("key1"));
    }
}
