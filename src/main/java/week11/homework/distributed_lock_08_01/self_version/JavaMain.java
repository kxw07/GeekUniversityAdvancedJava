package week11.homework.distributed_lock_08_01.self_version;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

public class JavaMain {
    private static final Logger logger = LogManager.getLogger(JavaMain.class);

    public static void main(String[] args) throws Exception {
        String machineName = "localMachine001";
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(new RedisStandaloneConfiguration("127.0.0.1", 6379));
        lettuceConnectionFactory.afterPropertiesSet();
        try (RedisConnection redisConnection = lettuceConnectionFactory.getConnection()) {
            if (redisConnection.setNX("myLock".getBytes(), machineName.getBytes())) {
                redisConnection.setEx("myLock".getBytes(), 30_000, machineName.getBytes());
            } else {
                throw new Exception("Get Lock Failed...");
            }
        } catch (Exception e) {
            logger.error(e);
        }

    }
}
