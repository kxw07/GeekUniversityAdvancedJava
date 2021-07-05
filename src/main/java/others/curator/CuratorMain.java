package others.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;

public class CuratorMain {
    private static final String ZK_ADDRESS = "localhost:2181";
    private static final String ZK_PATH = "/practice";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, new RetryNTimes(10, 5000));
        client.start();
        System.out.println("curator start successfully");
        Stat stat = new Stat();

        Assert.assertEquals(1, client.getChildren().forPath("/").size()); // default zookeeper config

        client.create().creatingParentsIfNeeded().forPath(ZK_PATH, "create".getBytes());
        Assert.assertEquals("create", new String(client.getData().storingStatIn(stat).forPath(ZK_PATH)));

        client.setData().forPath(ZK_PATH, "create2".getBytes());
        Assert.assertEquals("create2", new String(client.getData().storingStatIn(stat).forPath(ZK_PATH)));

        Assert.assertEquals(2, client.getChildren().forPath("/").size());


        client.create().creatingParentsIfNeeded().forPath(ZK_PATH + "/inside1");
        client.create().creatingParentsIfNeeded().forPath(ZK_PATH + "/inside2");
        client.create().creatingParentsIfNeeded().forPath(ZK_PATH + "/inside3");

        Assert.assertEquals(3, client.getChildren().forPath(ZK_PATH).size());

        client.delete().deletingChildrenIfNeeded().forPath(ZK_PATH);
        Assert.assertEquals(1, client.getChildren().forPath("/").size());
    }
}
