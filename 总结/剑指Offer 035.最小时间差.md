# 剑指Offer 035.最小时间差

给定一个24小时制（小时：分钟 **HH:MM**）的时间列表，找出列表中任意两个小时时间的最小时间差并以分钟数表示。

2022/5/4 第一次刷

初见思路：将所有分钟小时制的时间转化成分钟制，则该数组最长为`24*60`。当传入的数组长度超过这个数字，意味着存在两个相同的时间，直接返回0即可。将转化为分钟值的数组进行排序，最小的时间差只可能存在于两个相邻的数字之间或者最小值和最大值之间。

代码如下：

```java
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int len = timePoints.size();
        int[] time = new int[len];
        for (int i = 0; i < len; ++i) {
            String s = timePoints.get(i);
            String[] temp = s.split(":");
            time[i] = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
        }
        Arrays.sort(time);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < len; ++i) {
            min = Math.min(min, time[i] - time[i - 1]);
        }
        return Math.min(min, time[0] + 1440 - time[len - 1]);
    }
}
```

结果：通过！！ 时间复杂度O(nlogn)，空间复杂度O(n)
