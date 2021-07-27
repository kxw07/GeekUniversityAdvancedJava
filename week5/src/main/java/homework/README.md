## 本週作業
1.（選做）使 Java 裡的動態代理，實現一個簡單的 AOP。

2.（必做）寫代碼實現 Spring Bean 的裝配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。

3.（選做）實現一個 Spring XML 自定義配置，配置一組 Bean，例如：Student/Klass/School。

4.（選做，會添加到高手附加題） 

- 4.1 （挑戰）講網關的 frontend/backend/filter/router 線程池都改造成 Spring 配置方式；

- 4.2 （挑戰）基於 AOP 改造 Netty 網關，filter 和 router 使用 AOP 方式實現；

- 4.3 （中級挑戰）基於前述改造，將網關請求前後端分離，中級使用 JMS 傳遞消息；

- 4.4 （中級挑戰）嘗試使用 ByteBuddy 實現一個簡單的基於類的 AOP；

- 4.5 （超級挑戰）嘗試使用 ByteBuddy 與 Instrument 實現一個簡單 JavaAgent 實現無侵入下的 AOP。

5.（選做）總結一下，單例的各種寫法，比較它們的優劣。

6.（選做）maven/spring 的 profile 機制，都有什麼用法？

7.（選做）總結 Hibernate 與 MyBatis 的各方面異同點。

8.（必做）給前面課程提供的 Student/Klass/School 實現自動配置和 Starter。

9.（選做）學習 MyBatis-generator 的用法和原理，學會自定義 TypeHandler 處理複雜類型。

10.（必做）研究一下 JDBC 接口和數據庫連接池，掌握它們的設計和用法：

- 10.1 使用 JDBC 原生接口，實現數據庫的增刪改查操作。

- 10.2 使用事務，PrepareStatement 方式，批處理方式，改進上述操作。

- 10.3 配置 Hikari 連接池，改進上述操作。提交代碼到 GitHub。


## 附加題（可以後面上完數據庫的課再考慮做）

(挑戰) 基於 AOP 和自定義註解，實現 @MyCache(60) 對於指定方法返回值緩存 60 秒。

(挑戰) 自定​​義實現一個數據庫連接池，並整合 Hibernate/Mybatis/Spring/SpringBoot。

(挑戰) 基於 MyBatis 實現一個簡單的分庫分錶 + 讀寫分離 + 分佈式 ID 生成方案。