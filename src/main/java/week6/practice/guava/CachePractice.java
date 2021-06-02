package week6.practice.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Assert;

import java.util.concurrent.TimeUnit;

public class CachePractice {
    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3).expireAfterWrite(3, TimeUnit.SECONDS).build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");
        Assert.assertNull(cache.getIfPresent("key1"));
        Assert.assertEquals("value4", cache.getIfPresent("key4"));

        Thread.sleep(3_000);
        Assert.assertNull(cache.getIfPresent("key4"));
    }
}
