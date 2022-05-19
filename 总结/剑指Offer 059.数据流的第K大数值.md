# 剑指Offer 059.数据流的第K大数值

设计一个找到数据流中第`k`大元素的类（class）。注意是排序后的第`k`大元素，不是第`k`个不同的元素。

请事先`KthLargest`类：

- `KthLargest(int k, int[] nums)`使用整数`k`和整数流`nums`初始化对象。

- `int add(int val)`将`val`插入数据流`nums`后，返回当前数据流中第`k`大的元素。

2022/5/19 第一次见

初见思路：查找数组中TopK的数字，可以使用小顶堆（优先队列）实现

代码如下：

```java
class KthLargest {
    private PriorityQueue<Integer> heap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        heap = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        heap.offer(val);
        if (heap.size() > k) {
            heap.poll();
        }
        return heap.peek();
    }
}
```

结果：通过！ 时间复杂度O(nlogk)：单次插入时间复杂度为O(logk)，初始化时数组长度为n；空间复杂度O(k)，需要用k大的空间存储前k个数字。


