package homework.distributed_counter_08_02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import homework.RedisUtil;

public class DecreaseTask implements Runnable {
    private static final Logger logger = LogManager.getLogger(DecreaseTask.class);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                long stack = RedisUtil.getConnection().decr("counter".getBytes());
                if (stack >= 0) {
                    logger.info("Get Stack successfully: " + stack);
                } else {
                    throw new Exception("No stack");
                }
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
