# 剑指Offer 012. 左右两边子数组和相等

给你一个整数数组`nums`，请计算数组的**中心下标**。

位运算**中心下标**是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。如果中心下标位于数组最左侧，那么左侧数之和视为`0`，因为在下标左侧不存在元素。当中心下标位于数组最右端时同样适用。如果有多个中心下标，应该返回最靠近左边的那一个。如果数组不存在中心下标，返回`-1`。

2022/4/19 第一次刷

初见思路：利用前缀和，因为数组中心下标两侧的元素和相等，因此数组左侧元素之和乘以二加上数组中心下标元素就是数组所有元素之和。

tips：Arrays.stream(nums).sum得到的结果就是数组所有元素之和。

代码如下：

```java
class Solution {
    public int pivotIndex(int[] nums){
        int total = Arrays.stream(nums).sum();
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; ++i){
            if (sum * 2 + nums[i] == total){
                return i;
            }
            sum += nums[i];
        }
        System.out.println(sum);
        return -1;
    }
}
```

结果：通过。时间复杂度O(n)，空间复杂度O(1)


