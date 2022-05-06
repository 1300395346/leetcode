

# 剑指Offer 041.滑动窗口的平均值

给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。

实现`MovingAverage`类：

- `MovingAverage(int size)`用窗口大小`size`初始化对象。

- `double next(int val)`成员函数`next`每次调用的时候都会往滑动窗口增加一个整数，请计算并返回数据流中最后`size`个值的移动平均值，即滑动窗口里所有数字的平均值。

2022/5/6 第一次刷

初见思路：利用双端队列，初始化时维护一个窗口大小size和窗口内数字和total，当加入一个数字时，total增加，当队列大小小于size时，将该值加入队列，然后计算平均值；当队列大小等于size时，移除队列第一个元素，total减去该元素，然后计算平均值。

代码如下：

```java
class MovingAverage {

    int size;
    int total;
    Deque<Integer> queue;

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        movingAverage.next(1);
        movingAverage.next(10);
        movingAverage.next(3);
        movingAverage.next(5);
    }

    public MovingAverage(int size) {
        this.queue = new ArrayDeque<>();
        this.size = size;
        this.total = 0;
    }

    public double next(int val) {
        total += val;
        if (queue.size() == size) {
            total -= queue.getFirst();
            queue.removeFirst();
        }
        queue.addLast(val);
        return (double) total / queue.size();
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(n)


