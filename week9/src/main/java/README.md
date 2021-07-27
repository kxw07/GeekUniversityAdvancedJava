## 本週作業
1.（選做）實現簡單的 Protocol Buffer/Thrift/gRPC(選任一個) 遠程調用 demo。

2.（選做）實現簡單的 WebService-Axis2/CXF 遠程調用 demo。

3.（必做）改造自定義 RPC 的程序，提交到 GitHub：
- 嘗試將服務端寫死查找接口實現類變成泛型和反射；
- 嘗試將客戶端動態代理改成 AOP，添加異常處理；
- 嘗試使用 Netty+HTTP 作為 client 端傳輸方式。

4.（選做☆☆））升級自定義 RPC 的程序：
- 嘗試使用壓測並分析優化 RPC 性能；
- 嘗試使用 Netty+TCP 作為兩端傳輸方式；
- 嘗試自定義二進制序列化；
- 嘗試壓測改進後的 RPC 並分析優化，有問題歡迎群裡討論；
- 嘗試將 fastjson 改成 xstream；
- 嘗試使用字節碼生成方式代替服務端反射。

5.（選做）按課程第二部分練習各個技術點的應用。

6.（選做）按 dubbo-samples 項目的各個 demo 學習具體功能使用。

7.（必做）結合 dubbo+hmily，實現一個 TCC 外匯交易處理，代碼提交到 GitHub:
- 用戶 A 的美元賬戶和人民幣賬戶都在 A 庫，使用 1 美元兌換 7 人民幣 ;
- 用戶 B 的美元賬戶和人民幣賬戶都在 B 庫，使用 7 人民幣兌換 1 美元 ;
- 設計賬戶表，凍結資產表，實現上述兩個本地事務的分佈式事務。

8.（挑戰☆☆）嘗試擴展 Dubbo
- 基於上次作業的自定義序列化，實現 Dubbo 的序列化擴展 ;
- 基於上次作業的自定義 RPC，實現 Dubbo 的 RPC 擴展 ;
- 在 Dubbo 的 filter 機制上，實現 REST 權限控制，可參考 dubbox;
- 實現一個自定義 Dubbo 的 Cluster/Loadbalance 擴展，如果一分鐘內調用某個服務 / 提供者超過 10 次，則拒絕提供服務直到下一分鐘 ;
- 整合 Dubbo+Sentinel，實現限流功能 ;
- 整合 Dubbo 與 Skywalking，實現全鏈路性能監控。