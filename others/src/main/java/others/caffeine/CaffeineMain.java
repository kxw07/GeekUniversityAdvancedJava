package others.caffeine;

import com.github.benmanes.caffeine.cache.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

public class CaffeineMain {
    private static final Logger logger = LogManager.getLogger(CaffeineMain.class);
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        expireAfterWrite();
        loadingCacheWithRefreshAfterWrite();
    }

    private static void loadingCacheWithRefreshAfterWrite() throws InterruptedException {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .refreshAfterWrite(3, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(@Nullable String key, @Nullable String value, @NonNull RemovalCause removalCause) {
                        logger.info("removalListener... key:" + key + ", value:" + value);
                    }
                })
                .maximumSize(100)
                .build(key -> loadingCache());

        logger.info("cache.get(\"key2\"):" + cache.get("key2"));
//        Assert.assertEquals("value1", cache.get("key2"));

        logger.info("Sleep 3s");
        Thread.sleep(3000);
        logger.info("cache.get(\"key2\"):" + cache.get("key2"));
//        Assert.assertEquals("value1", cache.get("key2"));
//        Assert.assertEquals("value1", cache.get("key2"));

        logger.info("Sleep 5s");
        Thread.sleep(5000);
        logger.info("cache.get(\"key2\"):" + cache.get("key2"));
//        Assert.assertEquals("value2", cache.get("key2"));

        Thread.sleep(3000);
    }

    private static String loadingCache() throws InterruptedException {
        logger.info("loadingCache...");
        Thread.sleep(1000);
        return "value" + ++counter;
    }


    public static void expireAfterWrite() throws InterruptedException {
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .maximumSize(100)
                .build();

        cache.put("key1", "value1");
        Assert.isTrue("value1".equals(cache.getIfPresent("key1")), "value not equal");

        Thread.sleep(3000);
        Assert.isNull(cache.getIfPresent("key1"), "value not null");
    }
}
