package week11.homework.distributed_lock_08_01.self_version;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class LockTimeExtendScheduler {
    private static final Set<String> lockKeys = new HashSet<>();
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    private LockTimeExtendScheduler() {

    }

    public static void addLockKey(String lock) {
        lockKeys.add(lock);
    }

    public static void removeLockKey(String lock) {
        lockKeys.remove(lock);
    }
}
