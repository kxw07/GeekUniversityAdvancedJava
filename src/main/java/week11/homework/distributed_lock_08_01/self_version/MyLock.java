package week11.homework.distributed_lock_08_01.self_version;

public class MyLock {
    private String lockName;

    public MyLock(String lockName) {
        this.lockName = lockName;
    }

    public boolean lock() {
       return false;
    }

    public void addExpireTime() {

    }

    public void unlock() {

    }
}
