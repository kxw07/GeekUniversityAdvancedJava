1. slave
   ```
   # cluster-enabled yes
   ```
   
2. redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6381 \
   --cluster-replicas 1
   ```
   *** ERROR: Invalid configuration for cluster creation.
   *** Redis Cluster requires at least 3 master nodes.
   *** This is not possible with 2 nodes and 1 replicas per node.
   *** At least 6 nodes are required.
   ```
   
3. redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6380 \
   127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 \
   --cluster-replicas 1 