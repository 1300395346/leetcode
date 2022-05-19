# 剑指Offer 060.出现频率最高的k个数字

给定一个整数数组`nums`和一个整数`k`，请返回其中出现频率前`k`高的元素。可以按**任意顺序**返回答案。

2022/5/19 第一次刷

初见思路：首先遍历一遍数组，计算每个数字出现的次数，形成一个长度最多为n的出现次数的数组，然后找该数组前k大的数字。

代码如下：

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a));
        for (int i = 0; i < k; ++i) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}
```

结果：通过！ 时间复杂度O(nlogn)，空间复杂度O(n)；

进阶：要求时间复杂度小于O(nlogn)

在计算完出现次数的数组后，利用小顶堆，可以使复杂度降到O(nlogk)，但是需要重写堆的排序方法

代码如下：

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (heap.size() == k) {
                if (heap.peek()[1] < count) {
                    heap.poll();
                }
            }
            heap.offer(new int[]{num, count});
        }
        for (int i = 0; i < k; ++i) {
            ans[i] = heap.poll()[0];
        }
        return ans;
    }
}
```

结果：通过！ 时间复杂度O(nlogk)，空间复杂度O(n)
