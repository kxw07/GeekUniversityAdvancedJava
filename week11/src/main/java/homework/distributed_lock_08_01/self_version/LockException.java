package homework.distributed_lock_08_01.self_version;

public class LockException extends Exception{
    public LockException(String message) {
        super(message);
    }
}
