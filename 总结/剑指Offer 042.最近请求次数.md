# 剑指Offer 042.最近请求次数

写一个`RecentCounter`类来计算特定时间范围内最近的请求。

请事先`RecentCounter`类：

- `RecentCount()`初始化计数器，请求数为0。

- `int ping(int t)`在时间`t`添加一个新请求，其中`t`表示以毫秒为单位的某个时间，并返回过去`3000`毫秒内发生的所有请求数（包括新请求）。确切的说，返回在`[t-3000,t]`内发生的请求数。

**保证**  每次`ping`的调用都使用比之前更大的`t`值

2022/5/6 第一次刷

初见思路：由于每次ping调用的t都比之前大，我们可以维护一个队列，队列中的元素一定是递增的，每次有新的t值入队时，我们都将小于t-3000的值弹出，然后返回的值为队列的大小。

代码如下：

```java
class RecentCounter {
    Queue<Integer> queue;
 
    public RecentCounter() {
        this.queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        queue.offer(t);
        while (queue.peek() < t - 3000) {
            queue.poll();
        }
        return queue.size();
    }
}
```

结果：通过！！ 时间复杂度O(1)，空间复杂度O(n)——每个元素入队一次，因此时间复杂度为常数，空间复杂度为队列的最大元素个数。




