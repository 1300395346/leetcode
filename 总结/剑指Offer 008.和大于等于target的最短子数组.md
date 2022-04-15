# 剑指Offer 008.和大于等于**target**的最短子数组

给定一个含有 `n` 个正整数的数组和一个正整数 `target` 。

找出该数组中满足其和 `≥ target` 的长度最小的 **连续子数组** `[numsl, numsl+1, ..., numsr-1, numsr]` ，并返回其长度。如果不存在符合条件的子数组，返回 `0` 。

2022/4/15 第一次刷

初见思路：暴力循环

代码如下：

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums){
        int i = 0;
        int len = nums.length;
        int ans = len + 1;
        while (i < len){
            int j = i + 1;
            int res = nums[i];
            if (res >= target){
                ans = 1;
                break;
            }
            while (j < len){
                res += nums[j];
                int temp = j - i + 1;
                if (res >= target && temp < ans){
                    ans = temp;
                    break;
                }
                ++j;
            }
            ++i;
        }
        if (ans > len){
            ans = 0;
        }
        return ans;
    }
}
```

结果：超出时间限制；时间复杂度为：O(n!)

思路二：滑动窗口（在观看题解后理解，一开始虽然有个想法但不知道怎么实现）

代码如下：

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums){
        int left = 0;
        int right = 0;
        int len = nums.length;
        if (len == 0){
            return 0;
        }
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        while (right < len){
            sum += nums[right];
            while (sum >= target){
                ans = Math.min(ans,right - left + 1);
                sum -= nums[left];
                ++left;
            }
            ++right;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
```

结果：通过！！ 时间复杂度O(n){最坏情况下只有数组最后一个值是满足条件，`left`和`right`都移动了`n`次}，空间复杂度O(1)

思路三：前缀和+二分查找（来源于官方题解）

![1650029149(1).jpg](C:\Users\86189\Desktop\1650029149(1).jpg)



代码如下：

```java
class Solution {
       public int minSubArrayLen(int target, int[] nums){
        int len = nums.length;
        if (len == 0){
            return 0;
        }
        int[] sub = new int[len+1];
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < len + 1 ; ++i){
            sub[i] = sub[i-1] + nums[i-1];//前缀和
        }
        for (int i = 0; i < len; ++i){
            int sum = target + sub[i];
            int bound = Arrays.binarySearch(sub,sum);
            if (bound < 0){
                bound = -bound - 1;
                //binarySearch搜索的值若存在于数组当中，则返回该值在数组中从1开始计数的下标值
                //若不存在数组当中，则返回大于该数字的第一个数的下标值（依旧是从1开始计数）并将其改为负数
            }
            if (bound <= len){
                ans = Math.min(ans,bound - i);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
```

结果：通过！！ 时间复杂度O(nlogn){二分查找的时间复杂度为`logn`，对数组中每一个数都要进行一次二分查找，故时间复杂度为`nlogn`}，空间复杂度O(n)

注意：Arrays.binarySearch(nums,target)这个函数的返回值

- 当target为数组当中的元素时，从`0`开始计数，得到搜索值的索引值

- 当target不是数组中的元素且小于数组中所有元素时，返回值为`-1`

- 当target不是数组中的元素且大于数组中所有元素时，返回值为`-(nums.length+1)`

- 当target不是数组中的元素但在数组范围内时，返回值为从1开始计数，第一个大于target的值的索引值的相反数
