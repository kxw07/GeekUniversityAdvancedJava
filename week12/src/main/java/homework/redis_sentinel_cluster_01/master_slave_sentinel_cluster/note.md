1. redis.conf
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
   ```
   >>> Performing hash slots allocation on 6 nodes...
   Master[0] -> Slots 0 - 5460
   Master[1] -> Slots 5461 - 10922
   Master[2] -> Slots 10923 - 16383
   Adding replica 127.0.0.1:6383 to 127.0.0.1:6379
   Adding replica 127.0.0.1:6384 to 127.0.0.1:6380
   Adding replica 127.0.0.1:6382 to 127.0.0.1:6381
   >>> Trying to optimize slaves allocation for anti-affinity
   [WARNING] Some slaves are in the same host as their master
   M: 435afe3c37f74dc237f287bb746d393e5aa63b09 127.0.0.1:6379
   slots:[0-5460] (5461 slots) master
   M: 3c4083144ca6956f6af29e5b4353ae3c38a4299a 127.0.0.1:6380
   slots:[5461-10922] (5462 slots) master
   M: 1ecef48cca2b6c9f829dc48076cf71ec77ea90ef 127.0.0.1:6381
   slots:[10923-16383] (5461 slots) master
   S: 707f49b604d2c277d78ab247c8faa346d0ad46f3 127.0.0.1:6382
   replicates 1ecef48cca2b6c9f829dc48076cf71ec77ea90ef
   S: ba77db88ccd13e64506dbf2fd2170e2ecb0435d1 127.0.0.1:6383
   replicates 435afe3c37f74dc237f287bb746d393e5aa63b09
   S: a95a58872a8901358e8acbbf7937600e96b23126 127.0.0.1:6384
   replicates 3c4083144ca6956f6af29e5b4353ae3c38a4299a
   Can I set the above configuration? (type 'yes' to accept): yes
   >>> Nodes configuration updated
   >>> Assign a different config epoch to each node
   >>> Sending CLUSTER MEET messages to join the cluster
   Waiting for the cluster to join
   .
   >>> Performing Cluster Check (using node 127.0.0.1:6379)
   M: 435afe3c37f74dc237f287bb746d393e5aa63b09 127.0.0.1:6379
   slots:[0-5460] (5461 slots) master
   1 additional replica(s)
   S: ba77db88ccd13e64506dbf2fd2170e2ecb0435d1 127.0.0.1:6383
   slots: (0 slots) slave
   replicates 435afe3c37f74dc237f287bb746d393e5aa63b09
   M: 3c4083144ca6956f6af29e5b4353ae3c38a4299a 127.0.0.1:6380
   slots:[5461-10922] (5462 slots) master
   1 additional replica(s)
   M: 1ecef48cca2b6c9f829dc48076cf71ec77ea90ef 127.0.0.1:6381
   slots:[10923-16383] (5461 slots) master
   1 additional replica(s)
   S: a95a58872a8901358e8acbbf7937600e96b23126 127.0.0.1:6384
   slots: (0 slots) slave
   replicates 3c4083144ca6956f6af29e5b4353ae3c38a4299a
   S: 707f49b604d2c277d78ab247c8faa346d0ad46f3 127.0.0.1:6382
   slots: (0 slots) slave
   replicates 1ecef48cca2b6c9f829dc48076cf71ec77ea90ef
   [OK] All nodes agree about slots configuration.
   >>> Check for open slots...
   >>> Check slots coverage...
   [OK] All 16384 slots covered.
   ```
   
4. 127.0.0.1:6379> cluster info
   ```
   cluster_state:ok
   cluster_slots_assigned:16384
   cluster_slots_ok:16384
   cluster_slots_pfail:0
   cluster_slots_fail:0
   cluster_known_nodes:6
   cluster_size:3
   cluster_current_epoch:6
   cluster_my_epoch:1
   cluster_stats_messages_ping_sent:54
   cluster_stats_messages_pong_sent:61
   cluster_stats_messages_sent:115
   cluster_stats_messages_ping_received:56
   cluster_stats_messages_pong_received:54
   cluster_stats_messages_meet_received:5
   cluster_stats_messages_received:115
   ```
   
5. 127.0.0.1:26379> info
   ```
   # Sentinel
   sentinel_masters:3
   sentinel_tilt:0
   sentinel_running_scripts:0
   sentinel_scripts_queue_length:0
   sentinel_simulate_failure_flags:0
   master0:name=mymaster3,status=ok,address=127.0.0.1:6381,slaves=1,sentinels=3
   master1:name=mymaster2,status=ok,address=127.0.0.1:6380,slaves=1,sentinels=3
   master2:name=mymaster1,status=ok,address=127.0.0.1:6379,slaves=1,sentinels=3
   ```
   
