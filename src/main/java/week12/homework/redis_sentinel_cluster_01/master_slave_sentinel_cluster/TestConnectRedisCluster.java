package week12.homework.redis_sentinel_cluster_01.master_slave_sentinel_cluster;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class TestConnectRedisCluster {
    private static final Logger logger = LogManager.getLogger(TestConnectRedisCluster.class);

    public static void main(String[] args) {
        Config config = new Config();
        config.useClusterServers().addNodeAddress(
                "redis://127.0.0.1:6379",
                "redis://127.0.0.1:6380",
                "redis://127.0.0.1:6381",
                "redis://127.0.0.1:6382",
                "redis://127.0.0.1:6383",
                "redis://127.0.0.1:6384");

        RedissonClient client = Redisson.create(config);

        try {
            RMap<String, String> rMap =  client.getMap("test1");
            rMap.put("aaa", "111");
        } catch (Exception e) {
            logger.error("operation error", e);
        }

        client.shutdown();
    }
}
