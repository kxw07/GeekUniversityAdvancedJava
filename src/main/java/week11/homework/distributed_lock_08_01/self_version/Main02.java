package week11.homework.distributed_lock_08_01.self_version;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.connection.RedisConnection;

public class Main02 {
    private static final Logger logger = LogManager.getLogger(Main02.class);

    public static void main(String[] args) throws Exception {
        final MyLock lock01 = new MyLock("lock01");

        try (RedisConnection redisConnection = RedisUtil.getConnection()) {
            lock01.lock(redisConnection);

            logger.info("Lock successfully...");
        } catch (Exception e) {
            logger.error(e);
        }

        Thread.sleep(3_000);

        try (RedisConnection redisConnection = RedisUtil.getConnection()) {
            lock01.unlock(redisConnection);

            logger.info("UnLock successfully...");
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
