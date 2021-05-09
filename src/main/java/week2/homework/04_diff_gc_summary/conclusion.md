根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结
===
---
### 在 Heap Size 較小時 (Xmx512m, Xms512m)：

GC 次數：
G1 GC >> CMS GC > Serial GC == Parallel GC

GC 暫停時間：
Serial GC > Parallel GC > CMS GC > G1 GC

### 在 Heap Size 較大時 (Xmx4096m, Xms4096m)：

GC 次數：
G1 GC > CMS GC > Serial GC == Parallel GC

GC 暫停時間：
Serial GC > Parallel GC > CMS GC > G1 GC

---

串行 GC 的 GC 暫停時間大於 併行 GC，且隨著 Heap Size 的增加，暫停時間更明顯。

G1 GC 在 Heap Size 設定為 512m 時，GC 的次數將近兩百次，相較 串行 GC 及 併行 GC 只有 30 次左右，
增加 Heap Size 後 GC 次數大幅降低，
設定 -XX:MaxGCPauseMillis=10 後，GC 次數增加，但 GC 暫停時間符合預期，更頻繁的進行 GC，代表每次處理的量更少。

CMS GC 的 GC 次數 50 次略高於 併行 GC 的 30 次，但平均暫停時間較低，
增加 Heap Size 後 GC 次數大幅降低，
在 Heap Size 較大的情況，CMS GC 表現優於 串行 GC 及 併行 GC。

---

課堂筆記
GC 如何選擇：
选择正确的 GC 算法，唯一可行的方式就是去尝试，一般性的指导原则:
1. 如果系统考虑吞吐优先，CPU 资源都用来最大程度处理业务，用 Parallel GC;
2. 如果系统考虑低延迟有限，每次 GC 时间尽量短，用 CMS GC;
3. 如果系统内存堆较大，同时希望整体来看平均 GC 时间可控，使用 G1 GC。 对于内存大小的考量:
	- 一般 4G 以上，算是比较大，用 G1 的性价比较高。
	- 一般超过 8G，比如 16G-64G 内存，非常推荐使用 G1 GC。