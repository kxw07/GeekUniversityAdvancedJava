package homework.distributed_lock_08_01.self_version;

import org.springframework.data.redis.connection.RedisConnection;

import java.time.Instant;

public class MyLock {
    private final String lockName;
    private boolean isLocked;

    public MyLock(String lockName) {
        this.lockName = lockName;
    }

    public boolean lock(RedisConnection redisConnection) throws LockException {
        if(this.isLocked) {
            throw new LockException("Lock is locked.");
        }

        if (redisConnection.setNX(this.lockName.getBytes(), String.valueOf(Instant.now().getEpochSecond()).getBytes())) {
            this.isLocked = true;
            LockTimeExtendScheduler.addLockKey(this.lockName);
            return true;
        } else {
            throw new LockException("Try lock Failed.");
        }
    }

    public boolean unlock(RedisConnection redisConnection) throws LockException {
        if (!this.isLocked) {
            throw new LockException("Lock is unlocked.");
        }

        if (redisConnection.del(this.lockName.getBytes()) != 0) {
            this.isLocked = false;
            LockTimeExtendScheduler.removeLockKey(this.lockName);
            return true;
        } else {
            throw new LockException("Try unlock Failed.");
        }

    }
}
