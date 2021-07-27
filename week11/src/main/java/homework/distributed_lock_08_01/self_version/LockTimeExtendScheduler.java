package homework.distributed_lock_08_01.self_version;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LockTimeExtendScheduler {
    private static final Set<String> lockKeys = new HashSet<>();
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    static {
        scheduledExecutorService.scheduleAtFixedRate(new LockTimeExtendTask(lockKeys), 10, 10, TimeUnit.SECONDS);
    }

    private LockTimeExtendScheduler() {

    }

    public static void addLockKey(String lock) {
        lockKeys.add(lock);
    }

    public static void removeLockKey(String lock) {
        lockKeys.remove(lock);
    }

    public static void shutdown() {
        scheduledExecutorService.shutdown();
    }
}
