package week11.homework.pubsub_09;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.connection.RedisConnection;
import week11.homework.RedisUtil;

import java.time.Instant;

public class SendOrder {
    private static final Logger logger = LogManager.getLogger();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        RedisConnection redisConnection = RedisUtil.getConnection();
        redisConnection.publish("orderChannel".getBytes(), objectMapper.writeValueAsString(new Order(Instant.now().getEpochSecond())).getBytes());
    }
}
