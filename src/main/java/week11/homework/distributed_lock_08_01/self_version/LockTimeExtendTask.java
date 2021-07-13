package week11.homework.distributed_lock_08_01.self_version;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.util.Set;

public class LockTimeExtendTask implements Runnable {
    private static final Logger logger = LogManager.getLogger(LockTimeExtendTask.class);

    private Set<String> lockKeys;

    public LockTimeExtendTask(Set<String> lockKeys) {
        this.lockKeys = lockKeys;
    }

    @Override
    public void run() {
        try {
            lockKeys.forEach(lockKey -> {
                logger.info("Extend lockKey expire time:" + lockKey);
                RedisUtil.getConnection().setEx(lockKey.getBytes(), 15, String.valueOf(Instant.now().getEpochSecond()).getBytes());
            });
        } catch (Exception e) {
            logger.error("Extend lockKey expire time error.", e);
        }
    }
}
