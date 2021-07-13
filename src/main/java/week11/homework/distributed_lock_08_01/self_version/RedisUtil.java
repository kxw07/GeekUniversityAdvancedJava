package week11.homework.distributed_lock_08_01.self_version;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

public class RedisUtil {
    private static LettuceConnectionFactory lettuceConnectionFactory;

    static {
        lettuceConnectionFactory = new LettuceConnectionFactory(new RedisStandaloneConfiguration("127.0.0.1", 6379));
        lettuceConnectionFactory.afterPropertiesSet();
    }

    private RedisUtil() {

    }

    public static RedisConnection getConnection() {
        return lettuceConnectionFactory.getConnection();
    }
}
