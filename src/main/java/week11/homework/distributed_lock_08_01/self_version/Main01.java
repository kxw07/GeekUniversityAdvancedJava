package week11.homework.distributed_lock_08_01.self_version;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

public class Main01 {
    private static final Logger logger = LogManager.getLogger(Main01.class);

    public static void main(String[] args) throws Exception {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(new RedisStandaloneConfiguration("127.0.0.1", 6379));
        lettuceConnectionFactory.afterPropertiesSet();

        final MyLock lock01 = new MyLock("lock01");

        try (RedisConnection redisConnection = lettuceConnectionFactory.getConnection()) {
            lock01.lock(redisConnection);

            logger.info("Lock successfully...");
        } catch (Exception e) {
            logger.error(e);
        }

        Thread.sleep(30000);

        try (RedisConnection redisConnection = lettuceConnectionFactory.getConnection()) {
            lock01.unlock(redisConnection);

            logger.info("UnLock successfully...");
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
