package others.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.RetryForever;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;

public class CuratorMain {
    private static final String ZK_ADDRESS = "localhost:2181";
    private static final String WORK_PATH = "/practice";
    private static final String WORK_PATH_CHILDREN = "/practice/child";
    private static final CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, new RetryForever(3000));

    public static void main(String[] args) throws Exception {
        client.start();
        System.out.println("zookeeper client curator start successfully");

        initWorkPath();
        addWatcher();

        setNodeCache();
        setPathCache();
        setTreeCache();
        setCuratorCache();

        normalOperation();
    }

    private static void initWorkPath() throws Exception {
        System.out.println("===== initWorkPath =====");
        client.create().forPath(WORK_PATH);
    }

    // 只會觸發一次
    public static void addWatcher() throws Exception {
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("watcher... watchedEvent: " + event);
            }
        };

        byte[] content = client.getData().usingWatcher(watcher).forPath(WORK_PATH);
        System.out.println("listen: " + new String(content));
    }


    public static void normalOperation() throws Exception {
        Stat stat = new Stat();

        Assert.assertEquals(0, client.getChildren().forPath(WORK_PATH).size());

        System.out.println("===== setData to WORK_PATH =====");
        client.setData().forPath(WORK_PATH, "work_path_data".getBytes());
        Assert.assertEquals("work_path_data", new String(client.getData().storingStatIn(stat).forPath(WORK_PATH)));

        System.out.println("===== create WORK_PATH_CHILDREN with data =====");
        client.create().creatingParentsIfNeeded().forPath(WORK_PATH_CHILDREN, "work_path_children".getBytes());
        Assert.assertEquals("work_path_children", new String(client.getData().storingStatIn(stat).forPath(WORK_PATH_CHILDREN)));

        System.out.println("===== setData WORK_PATH_CHILDREN =====");
        client.setData().forPath(WORK_PATH_CHILDREN, "work_path_children2".getBytes());
        Assert.assertEquals("work_path_children2", new String(client.getData().storingStatIn(stat).forPath(WORK_PATH_CHILDREN)));

        Assert.assertEquals(1, client.getChildren().forPath(WORK_PATH).size());

        System.out.println("===== create WORK_PATH_CHILDREN's children =====");
        client.create().creatingParentsIfNeeded().forPath(WORK_PATH_CHILDREN + "/inside1");
        client.create().creatingParentsIfNeeded().forPath(WORK_PATH_CHILDREN + "/inside2");
        client.create().creatingParentsIfNeeded().forPath(WORK_PATH_CHILDREN + "/inside3");

        Assert.assertEquals(3, client.getChildren().forPath(WORK_PATH_CHILDREN).size());

        System.out.println("===== delete WORK_PATH =====");
        client.delete().deletingChildrenIfNeeded().forPath(WORK_PATH);
        Assert.assertEquals(1, client.getChildren().forPath("/").size()); // default zookeeper config
    }

    public static void setNodeCache() {
        CuratorCache curatorCache = CuratorCache.build(client, WORK_PATH);
        CuratorCacheListener nodeCache = CuratorCacheListener.builder().forNodeCache(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("NodeCache receive nodeChanged...");
            }
        }).build();

        curatorCache.listenable().addListener(nodeCache);
        curatorCache.start();
    }

    public static void setPathCache() {
        CuratorCache curatorCache = CuratorCache.build(client, WORK_PATH);
        CuratorCacheListener pathCache = CuratorCacheListener.builder().forPathChildrenCache(WORK_PATH, client, new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("PathCache receive event: " + event);
            }
        }).build();

        curatorCache.listenable().addListener(pathCache);
        curatorCache.start();
    }

    public static void setTreeCache() {
        CuratorCache curatorCache = CuratorCache.build(client, WORK_PATH);
        CuratorCacheListener treeCache = CuratorCacheListener.builder().forTreeCache(client, new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println("TreeCache receive event: " + event);
            }
        }).build();

        curatorCache.listenable().addListener(treeCache);
        curatorCache.start();
    }

    public static void setCuratorCache() {
        CuratorCache curatorCache = CuratorCache.build(client, WORK_PATH);
        curatorCache.listenable().addListener(new CuratorCacheListener() {
            @Override
            public void event(Type type, ChildData oldData, ChildData data) {
                System.out.println("CuratorCache receive...");
                System.out.println("Type: " + type);
                System.out.println("oldData: " + oldData);
                System.out.println("newData: " + data);
            }
        });

        curatorCache.start();
    }
}
