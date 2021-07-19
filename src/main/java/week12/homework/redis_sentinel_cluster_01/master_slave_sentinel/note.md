1. redis-server redis-6379.conf
2. redis-server redis-6380.conf
3. redis-cli -h 127.0.0.1 -p 6379
4. redis-cli -h 127.0.0.1 -p 6380
   ```shell
   127.0.0.1:6380> slaveof 127.0.0.1 6379
   127.0.0.1:6380> set a 1
   (error) READONLY You can't write against a read only replica.
   ```
5. redis-sentinel redis-sentinel-0.conf
6. redis-sentinel redis-sentinel-1.conf
7. shutdown redis 6379
   - redis 6380 log:
   ```
   38908:S 19 Jul 2021 23:15:59.019 # Connection with master lost.
   38908:S 19 Jul 2021 23:15:59.019 * Caching the disconnected master state.
   38908:S 19 Jul 2021 23:15:59.019 * Reconnecting to MASTER 127.0.0.1:6379
   38908:S 19 Jul 2021 23:15:59.019 * MASTER <-> REPLICA sync started
   38908:S 19 Jul 2021 23:15:59.019 # Error condition on socket for SYNC: Connection refused
   38908:S 19 Jul 2021 23:15:59.485 * Connecting to MASTER 127.0.0.1:6379
   38908:S 19 Jul 2021 23:15:59.485 * MASTER <-> REPLICA sync started
   38908:S 19 Jul 2021 23:15:59.485 # Error condition on socket for SYNC: Connection refused
   38908:S 19 Jul 2021 23:16:00.522 * Connecting to MASTER 127.0.0.1:6379
   38908:S 19 Jul 2021 23:16:00.522 * MASTER <-> REPLICA sync started
   38908:S 19 Jul 2021 23:16:00.522 # Error condition on socket for SYNC: Connection refused
   38908:S 19 Jul 2021 23:16:01.556 * Connecting to MASTER 127.0.0.1:6379
   38908:S 19 Jul 2021 23:16:01.556 * MASTER <-> REPLICA sync started
   38908:S 19 Jul 2021 23:16:01.556 # Error condition on socket for SYNC: Connection refused
   38908:S 19 Jul 2021 23:16:02.584 * Connecting to MASTER 127.0.0.1:6379
   38908:S 19 Jul 2021 23:16:02.584 * MASTER <-> REPLICA sync started
   38908:S 19 Jul 2021 23:16:02.584 # Error condition on socket for SYNC: Connection refused
   38908:S 19 Jul 2021 23:16:03.616 * Connecting to MASTER 127.0.0.1:6379
   38908:S 19 Jul 2021 23:16:03.616 * MASTER <-> REPLICA sync started
   38908:S 19 Jul 2021 23:16:03.616 # Error condition on socket for SYNC: Connection refused
   38908:S 19 Jul 2021 23:16:04.653 * Connecting to MASTER 127.0.0.1:6379
   38908:S 19 Jul 2021 23:16:04.653 * MASTER <-> REPLICA sync started
   38908:S 19 Jul 2021 23:16:04.653 # Error condition on socket for SYNC: Connection refused
   38908:S 19 Jul 2021 23:16:05.695 * Connecting to MASTER 127.0.0.1:6379
   38908:S 19 Jul 2021 23:16:05.695 * MASTER <-> REPLICA sync started
   38908:S 19 Jul 2021 23:16:05.695 # Error condition on socket for SYNC: Connection refused
   38908:S 19 Jul 2021 23:16:06.727 * Connecting to MASTER 127.0.0.1:6379
   38908:S 19 Jul 2021 23:16:06.727 * MASTER <-> REPLICA sync started
   38908:S 19 Jul 2021 23:16:06.728 # Error condition on socket for SYNC: Connection refused
   38908:S 19 Jul 2021 23:16:07.761 * Connecting to MASTER 127.0.0.1:6379
   38908:S 19 Jul 2021 23:16:07.761 * MASTER <-> REPLICA sync started
   38908:S 19 Jul 2021 23:16:07.761 # Error condition on socket for SYNC: Connection refused
   38908:S 19 Jul 2021 23:16:08.784 * Connecting to MASTER 127.0.0.1:6379
   38908:S 19 Jul 2021 23:16:08.785 * MASTER <-> REPLICA sync started
   38908:S 19 Jul 2021 23:16:08.785 # Error condition on socket for SYNC: Connection refused
   38908:M 19 Jul 2021 23:16:09.367 * Discarding previously cached master state.
   38908:M 19 Jul 2021 23:16:09.367 # Setting secondary replication ID to 8e383feee369f49a85e2828eabecec3e59586e82, valid up to offset: 10122. New replication ID is 986e4574a4e2a0bc2475fd1f4b5415fee3d71f7c
   38908:M 19 Jul 2021 23:16:09.367 * MASTER MODE enabled (user request from 'id=6 addr=127.0.0.1:58576 laddr=127.0.0.1:6380 fd=10 name=sentinel-7b7cc4ea-cmd age=93 idle=0 flags=x db=0 sub=0 psub=0 multi=4 qbuf=202 qbuf-free=65328 argv-mem=4 obl=45 oll=0 omem=0 tot-mem=82980 events=r cmd=exec user=default redir=-1')
   38908:M 19 Jul 2021 23:16:09.369 # CONFIG REWRITE executed with success.
   ```
   - sentinel-0 log:
   ```
   40480:X 19 Jul 2021 23:14:36.611 # Sentinel ID is 7b7cc4ea85b55bfebbfe3bf592ecec3a266aed5a
   40480:X 19 Jul 2021 23:14:36.611 # +monitor master mymaster 127.0.0.1 6379 quorum 2
   40480:X 19 Jul 2021 23:14:36.613 * +slave slave 127.0.0.1:6380 127.0.0.1 6380 @ mymaster 127.0.0.1 6379
   40480:X 19 Jul 2021 23:15:07.302 * +sentinel sentinel 8a5120e2bc4bf08c7425eb5fda2ef65f841567c9 127.0.0.1 26380 @ mymaster 127.0.0.1 6379
   40480:X 19 Jul 2021 23:16:09.074 # +sdown master mymaster 127.0.0.1 6379
   40480:X 19 Jul 2021 23:16:09.141 # +odown master mymaster 127.0.0.1 6379 #quorum 2/2
   40480:X 19 Jul 2021 23:16:09.141 # +new-epoch 1
   40480:X 19 Jul 2021 23:16:09.141 # +try-failover master mymaster 127.0.0.1 6379
   40480:X 19 Jul 2021 23:16:09.147 # +vote-for-leader 7b7cc4ea85b55bfebbfe3bf592ecec3a266aed5a 1
   40480:X 19 Jul 2021 23:16:09.152 # 8a5120e2bc4bf08c7425eb5fda2ef65f841567c9 voted for 7b7cc4ea85b55bfebbfe3bf592ecec3a266aed5a 1
   40480:X 19 Jul 2021 23:16:09.218 # +elected-leader master mymaster 127.0.0.1 6379
   40480:X 19 Jul 2021 23:16:09.218 # +failover-state-select-slave master mymaster 127.0.0.1 6379
   40480:X 19 Jul 2021 23:16:09.305 # +selected-slave slave 127.0.0.1:6380 127.0.0.1 6380 @ mymaster 127.0.0.1 6379
   40480:X 19 Jul 2021 23:16:09.305 * +failover-state-send-slaveof-noone slave 127.0.0.1:6380 127.0.0.1 6380 @ mymaster 127.0.0.1 6379
   40480:X 19 Jul 2021 23:16:09.367 * +failover-state-wait-promotion slave 127.0.0.1:6380 127.0.0.1 6380 @ mymaster 127.0.0.1 6379
   40480:X 19 Jul 2021 23:16:10.126 # +promoted-slave slave 127.0.0.1:6380 127.0.0.1 6380 @ mymaster 127.0.0.1 6379
   40480:X 19 Jul 2021 23:16:10.126 # +failover-state-reconf-slaves master mymaster 127.0.0.1 6379
   40480:X 19 Jul 2021 23:16:10.226 # +failover-end master mymaster 127.0.0.1 6379
   40480:X 19 Jul 2021 23:16:10.226 # +switch-master mymaster 127.0.0.1 6379 127.0.0.1 6380
   40480:X 19 Jul 2021 23:16:10.226 * +slave slave 127.0.0.1:6379 127.0.0.1 6379 @ mymaster 127.0.0.1 6380
   40480:X 19 Jul 2021 23:16:20.298 # +sdown slave 127.0.0.1:6379 127.0.0.1 6379 @ mymaster 127.0.0.1 6380
   ```
   - sentinel-1 log:
   ```
   40801:X 19 Jul 2021 23:15:05.225 # Sentinel ID is 8a5120e2bc4bf08c7425eb5fda2ef65f841567c9
   40801:X 19 Jul 2021 23:15:05.225 # +monitor master mymaster 127.0.0.1 6379 quorum 2
   40801:X 19 Jul 2021 23:15:05.226 * +slave slave 127.0.0.1:6380 127.0.0.1 6380 @ mymaster 127.0.0.1 6379
   40801:X 19 Jul 2021 23:15:06.986 * +sentinel sentinel 7b7cc4ea85b55bfebbfe3bf592ecec3a266aed5a 127.0.0.1 26379 @ mymaster 127.0.0.1 6379
   40801:X 19 Jul 2021 23:16:09.073 # +sdown master mymaster 127.0.0.1 6379
   40801:X 19 Jul 2021 23:16:09.150 # +new-epoch 1
   40801:X 19 Jul 2021 23:16:09.152 # +vote-for-leader 7b7cc4ea85b55bfebbfe3bf592ecec3a266aed5a 1
   40801:X 19 Jul 2021 23:16:10.206 # +odown master mymaster 127.0.0.1 6379 #quorum 2/2
   40801:X 19 Jul 2021 23:16:10.206 # Next failover delay: I will not start a failover before Mon Jul 19 23:22:09 2021
   40801:X 19 Jul 2021 23:16:10.230 # +config-update-from sentinel 7b7cc4ea85b55bfebbfe3bf592ecec3a266aed5a 127.0.0.1 26379 @ mymaster 127.0.0.1 6379
   40801:X 19 Jul 2021 23:16:10.230 # +switch-master mymaster 127.0.0.1 6379 127.0.0.1 6380
   40801:X 19 Jul 2021 23:16:10.230 * +slave slave 127.0.0.1:6379 127.0.0.1 6379 @ mymaster 127.0.0.1 6380
   40801:X 19 Jul 2021 23:16:20.266 # +sdown slave 127.0.0.1:6379 127.0.0.1 6379 @ mymaster 127.0.0.1 6380
   ```
8. restart redis 6379
   - redis 6379 log:
   ```
   41079:M 19 Jul 2021 23:21:34.280 # Server initialized
   41079:M 19 Jul 2021 23:21:34.281 * Loading RDB produced by version 6.2.4
   41079:M 19 Jul 2021 23:21:34.281 * RDB age 335 seconds
   41079:M 19 Jul 2021 23:21:34.281 * RDB memory usage when created 2.08 Mb
   41079:M 19 Jul 2021 23:21:34.281 * DB loaded from disk: 0.001 seconds
   41079:M 19 Jul 2021 23:21:34.281 * Ready to accept connections
   41079:S 19 Jul 2021 23:21:45.169 * Before turning into a replica, using my own master parameters to synthesize a cached master: I may be able to synchronize with the new master with just a partial transfer.
   41079:S 19 Jul 2021 23:21:45.169 * Connecting to MASTER 127.0.0.1:6380
   41079:S 19 Jul 2021 23:21:45.170 * MASTER <-> REPLICA sync started
   41079:S 19 Jul 2021 23:21:45.170 * REPLICAOF 127.0.0.1:6380 enabled (user request from 'id=3 addr=127.0.0.1:59960 laddr=127.0.0.1:6379 fd=8 name=sentinel-7b7cc4ea-cmd age=11 idle=0 flags=x db=0 sub=0 psub=0 multi=4 qbuf=196 qbuf-free=65334 argv-mem=4 obl=45 oll=0 omem=0 tot-mem=82980 events=r cmd=exec user=default redir=-1')
   41079:S 19 Jul 2021 23:21:45.177 # CONFIG REWRITE executed with success.
   41079:S 19 Jul 2021 23:21:45.177 * Non blocking connect for SYNC fired the event.
   41079:S 19 Jul 2021 23:21:45.177 * Master replied to PING, replication can continue...
   41079:S 19 Jul 2021 23:21:45.178 * Trying a partial resynchronization (request b5b7d66dfdf57ae35d90222e7c786ecce9655bae:1).
   41079:S 19 Jul 2021 23:21:45.180 * Full resync from master: 986e4574a4e2a0bc2475fd1f4b5415fee3d71f7c:53798
   41079:S 19 Jul 2021 23:21:45.180 * Discarding previously cached master state.
   41079:S 19 Jul 2021 23:21:45.270 * MASTER <-> REPLICA sync: receiving 209 bytes from master to disk
   41079:S 19 Jul 2021 23:21:45.270 * MASTER <-> REPLICA sync: Flushing old data
   41079:S 19 Jul 2021 23:21:45.270 * MASTER <-> REPLICA sync: Loading DB in memory
   41079:S 19 Jul 2021 23:21:45.271 * Loading RDB produced by version 6.2.4
   41079:S 19 Jul 2021 23:21:45.271 * RDB age 0 seconds
   41079:S 19 Jul 2021 23:21:45.271 * RDB memory usage when created 2.14 Mb
   41079:S 19 Jul 2021 23:21:45.271 * MASTER <-> REPLICA sync: Finished with success
   ```
   - redis 6380 log:
   ```
   38908:M 19 Jul 2021 23:21:45.178 * Replica 127.0.0.1:6379 asks for synchronization
   38908:M 19 Jul 2021 23:21:45.178 * Partial resynchronization not accepted: Replication ID mismatch (Replica asked for 'b5b7d66dfdf57ae35d90222e7c786ecce9655bae', my replication IDs are '986e4574a4e2a0bc2475fd1f4b5415fee3d71f7c' and '8e383feee369f49a85e2828eabecec3e59586e82')
   38908:M 19 Jul 2021 23:21:45.178 * Starting BGSAVE for SYNC with target: disk
   38908:M 19 Jul 2021 23:21:45.180 * Background saving started by pid 41089
   41089:C 19 Jul 2021 23:21:45.181 * DB saved on disk
   38908:M 19 Jul 2021 23:21:45.269 * Background saving terminated with success
   38908:M 19 Jul 2021 23:21:45.270 * Synchronization with replica 127.0.0.1:6379 succeeded
   ```
   
