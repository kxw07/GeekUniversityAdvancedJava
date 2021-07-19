## Week12 作業題目：

1.（必做）配置 redis 的主從復制，sentinel 高可用，Cluster 集群。

2.（選做）練習示例代碼裡下列類中的作業題:
08cache/redis/src/main/java/io/kimmking/cache/RedisApplication.java

3.（選做☆）練習 redission 的各種功能。

4.（選做☆☆）練習 hazelcast 的各種功能。

5.（選做☆☆☆）搭建 hazelcast 3 節點集群，寫入 100 萬數據到一個 map，模擬和演 示高可用。

6.（必做）搭建 ActiveMQ 服務，基於 JMS，寫代碼分別實現對於 queue 和 topic 的消息生產和消費，代碼提交到 github。

7.（選做）基於數據庫的訂單表，模擬消息隊列處理訂單：

一個程序往表裡寫新訂單，標記狀態為未處理 (status=0);
另一個程序每隔 100ms 定時從表裡讀取所有 status=0 的訂單，打印一下訂單數據，然後改成完成 status=1；
（挑戰☆）考慮失敗重試策略，考慮多個消費程序如何協作。
8.（選做）將上述訂單處理場景，改成使用 ActiveMQ 發送消息處理模式。

9.（選做）使用 java 代碼，創建一個 ActiveMQ Broker Server，並測試它。

10.（挑戰☆☆）搭建 ActiveMQ 的 network 集群和 master-slave 主從結構。

11.（挑戰☆☆☆）基於 ActiveMQ 的 MQTT 實現簡單的聊天功能或者 Android 消息推送。

12.（挑戰☆）創建一個 RabbitMQ，用 Java 代碼實現簡單的 AMQP 協議操作。

13.（挑戰☆☆）搭建 RabbitMQ 集群，重新實現前面的訂單處理。

14.（挑戰☆☆☆）使用 Apache Camel 打通上述 ActiveMQ 集群和 RabbitMQ 集群，實現所有寫入到 ActiveMQ 上的一個隊列 q24 的消息，自動轉發到 RabbitMQ。

15.（挑戰☆☆☆）壓測 ActiveMQ 和 RabbitMQ 的性能。