# 剑指Offer 009.乘积小于K的子数组

给定一个正整数数组`nums`和整数`k`，请找出该数组内乘积小于`k`的连续的子数组的个数

2022/4/17 第一次刷

初见思路：与剑指Offer008很像，利用滑动窗口解

代码如下：

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k){
        int result = 0;
        int left = 0;
        int right = 0;
        int temp = 1;
        int len = nums.length;
        while(right < len){
            temp *= nums[right];
            while (left <= right && temp >= k){
                temp /= nums[left++];
            }
            if (left <= right){
                result += right - left + 1;
            }
            right++;
        }
        return result;
    }
}
```

结果：通过！！！
