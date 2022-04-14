# 剑指Offer 004 只出现一次的数字

2022/4/12 第一次刷

给你一个整数数组 `nums` ，除某个元素仅出现 **一次** 外，其余每个元素都恰出现**三次** 。请你找出并返回那个只出现了一次的元素。

提示：

- 1 <= nums.length <= 3 * 104

- -2^31 <= nums[i] <= 2^31 - 1

- nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次

初见思路：哈希表存储出现的元素并记录出现的次数，最后输出出现次数为1的数字

    HashMap相关操作：

- map.getOrDefault(key,defaultValue)——获取指定 key 对应对 value，如果找不到 key ，则返回设置的默认值。

- Map.Entry——内部接口，map.entry()——返回一个Set集合，集合的内容为map的键值对

代码如下：

```java
class Solution
     public int singleNumber(int[] nums){
        Map<Integer,Integer> map = new HashMap<>();
        for (int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        int ans = 0;
        for (Map.Entry<Integer,Integer> maps:map.entrySet()){
            int num = maps.getKey(), index = maps.getValue();
            if (index == 1){
                ans = num;
                break;
            }
        }
        return ans;
    }
}
```

结果：通过！
