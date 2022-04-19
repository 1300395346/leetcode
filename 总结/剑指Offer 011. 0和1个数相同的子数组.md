# 剑指Offer 011. 0和1个数相同的子数组

给定一个二进制数组`nums`，找到含有相同数量的`0`和`1`的最长连续子数组，并返回该子数组的长度。

2022/4/19 第一次刷

初见思路：当二进制数组中的值0变为-1时，题目要求可以变更为找到和为0的最长连续子数组，参考Offer 010，采用前缀和+哈希表的方式解决。

注意：当两个下标前缀和相同时，代表这两个下标之间的数组具有相同个数的0和1

代码如下：

```java
class Solution {
    public int findMaxLength(int[] nums){
        int len = nums.length;
        int count = 0;
        int result = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        for (int i = 0; i < len; ++i){
            if (nums[i] == 1){
                count++;
            } else {
                count--;
            }
            if (map.containsKey(count)){
                result = Math.max(result,i-map.get(count));
            } else {
                map.put(count,i);
            }
        }
        return result;
    }
}
```

结果：通过！ 时间复杂度O(n)，空间复杂度O(n

)
