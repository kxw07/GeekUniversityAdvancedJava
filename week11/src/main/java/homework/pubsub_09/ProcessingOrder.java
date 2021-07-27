package homework.pubsub_09;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import homework.RedisUtil;

public class ProcessingOrder {
    private static final Logger logger = LogManager.getLogger();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        RedisConnection redisConnection = RedisUtil.getConnection();
        redisConnection.subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] bytes) {
                try {
                    Order order = objectMapper.readValue(new String(message.getBody()), Order.class);
                    logger.info("order:" + order);
                } catch (Exception e) {
                    logger.error("Deserialize order error.", e);
                }
            }
        }, "orderChannel".getBytes());

        while (true) {

        }
    }
}
