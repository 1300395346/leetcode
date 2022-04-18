# 剑指Offer 010.和为k的子数组

给定一个整数数组和一个整数`k`，请找到该数组中和为`k`的连续子数组的个数

2022/4/18 第一次刷

初见思路：枚举所有连续子数组，找出和为`k`的连续子数组的个数

代码如下：

```java
class Solution {
    public int subarraySum(int[] nums, int k){
        int len = nums.length;
        int res = 0;
        int ans = 0;
        for (int i = 0; i < len; ++i){
            for (int j = i; j < len; ++j){
                res += nums[j];
                if (res > k){
                    res = 0;
                    break;
                } else if (res == k){
                    res = 0;
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}
```

结果：解答错误！最后执行输入：nums = {-1,-1,1}，k=1

反思：未曾考虑nums中的数字存在负数的情况，因此只需要判断`res == k`的情况，同时，未曾考虑输入为`nums = {-1,1,0},k=0`的情况，这种情况下当`res == k`时不需要跳出循环，而当`res == k`的情况下不跳出循环的话，也不能在此时将`res`置为0。

修改后代码如下：

```java
class Solution {
    public int subarraySum(int[] nums, int k){
        int len = nums.length;
        int res = 0;
        int ans = 0;
        for (int i = 0; i < len; ++i){
            for (int j = i; j < len; ++j){
                res += nums[j];
                if (res == k){
                    ans++;
                }
                if (j == len-1){
                    res = 0;
                    break;
                }
            }
        }
        return ans;
    }
}
```

时间复杂度O(n!)，空间复杂度O(1)

思路二：前缀和+哈希表（查看了官方题解）

详解：

1. 使用`pre`记录开始元素到第i个元素的前缀和

2. 使用`map`记录`pre`出现的次数（即以`pre`为`key`，出现次数为`value`）

3. 查询`pre - k`在`map`中是否存在，这里可以理解为查找在`map`保存的`pre`中是否存在一个`oldPre`，使得`oldPre = pre - k`，则`pre - oldPre = k`,意味着`oldPre`到`pre`的和为`key`。（`oldPre`这里即`map.get(pre - k)`,为当前元素前面的某个节点的和）

4. 查询`pre - k`在`map`中是否存在，这里可以理解为查找在`map`保存的`pre`中是否存在一个`oldPre`，使得`oldPre = pre - k`，则`pre - oldPre = k`,意味着`oldPre`到`pre`的和为`key`。（`oldPre`这里即`map.get(pre - k)`,为当前元素前面的某个节点的和）

代码如下：

```java
class Solution {
    public int subarraySum(int[] nums, int k){
        HashMap<Integer,Integer> map = new HashMap<>();
        int pre = 0;
        int ans = 0;
        map.put(0,1);
        for (int num : nums) {
            pre += num;
            int value = map.getOrDefault(pre, 0) + 1;
            map.put(pre, value);
            if (map.containsKey(pre - k)){
                ans += map.get(pre - k);
            }
        }
        return ans;
    }
}
```

结果：解答错误！最后执行输入：`nums = {1}，k = 0`

错误原因：查询`pre - k`早`map`中是否存在其实是查询`map`中是否存在一个是否处在一个`key`使得 `pre - k`，而当`pre - k = key`时，先存储还是先查询会导致得到的结果不同。而最后执行输入正是上述情况，先执行存储会导致查询时发现存在一个`key`使得`pre - k = key`存在，然而实际上并不存在。

修改后代码：

```java
class Solution {
    public int subarraySum(int[] nums, int k){
        HashMap<Integer,Integer> map = new HashMap<>();
        int pre = 0;
        int ans = 0;
        map.put(0,1);
        for (int num : nums) {
            pre += num;
            int value = map.getOrDefault(pre, 0) + 1;
            if (map.containsKey(pre - k)){
                ans += map.get(pre - k);
            }
            map.put(pre, value);

        }
        return ans;
    }
}
```

结果：通过！ 时间复杂度O(n)，空间复杂度O(n)


