package week11.homework.distributed_lock_08_01.redisson_version;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

public class Thread02 {
    private static final Logger logger = LogManager.getLogger(Thread02.class);

    public static void main(String[] args) {
        RedissonUtil redissonUtil = new RedissonUtil();
        RLock myLock = redissonUtil.getRedissonClient().getLock("myLock");
        myLock.lock(30, TimeUnit.SECONDS);
        logger.info("Thread02 get lock...");
    }
}
