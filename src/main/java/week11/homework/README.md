## 本週作業
1.（選做）按照課程內容，動手驗證 Hibernate 和 Mybatis 緩存。

2.（選做）使用 spring 或 guava cache，實現業務數據的查詢緩存。

3.（挑戰☆）編寫代碼，模擬緩存穿透，擊穿，雪崩。

4.（挑戰☆☆）自己動手設計一個簡單的 cache，實現過期策略。

5.（選做）命令行下練習操作 Redis 的各種基本數據結構和命令。

6.（選做）分別基於 jedis，RedisTemplate，Lettuce，Redission 實現 redis 基本操作的 demo，可以使用 spring-boot 集成上述工具。

7.（選做）spring 集成練習:
- 實現 update 方法，配合 @CachePut
- 實現 delete 方法，配合 @CacheEvict
- 將示例中的 spring 集成 Lettuce 改成 jedis 或 redisson

8.（必做）基於 Redis 封裝分佈式數據操作：
- 在 Java 中實現一個簡單的分佈式鎖；
- 在 Java 中實現一個分佈式計數器，模擬減庫存。

9.（必做）基於 Redis 的 PubSub 實現訂單異步處理

10.（挑戰☆）基於其他各類場景，設計並在示例代碼中實現簡單 demo：
- 實現分數排名或者排行榜；
- 實現全局 ID 生成；
- 基於 Bitmap 實現 id 去重；
- 基於 HLL 實現點擊量計數；
- 以 redis 作為數據庫，模擬使用 lua 腳本實現前面課程的外匯交易事務。

11.（挑戰☆☆）升級改造項目：
- 實現 guava cache 的 spring cache 適配；
- 替換 jackson 序列化為 fastjson 或者 fst，kryo；
- 對項目進行分析和性能調優。

12.（挑戰☆☆☆）以 redis 作為基礎實現上個模塊的自定義 rpc 的註冊中心。