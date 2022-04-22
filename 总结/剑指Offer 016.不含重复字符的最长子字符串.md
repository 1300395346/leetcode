# 剑指Offer 016.不含重复字符的最长子字符串

给定一个字符串`s`，请你找出其中不含有重复字符的**最长连续子字符串**的长度。

2022/4/22 第一次刷

初见思路：

1. 枚举所有子序列肯定能够得到答案。

2. 利用滑动窗口

初始滑动窗口长度为1，然后右边界向右移动，若加入的字符与窗口中某一字符相同，则令左边界右移，同时使窗口长度变为1，否则右边界继续右移，同时窗口长度增加。当左边界到字符串末尾的距离小于最大长度时，跳出循环。

滑动窗口代码如下：

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        Set<Character> set = new HashSet<>();
        int right = -1;
        int n = s.length();
        for (int left = 0; left < n; ++left){
            if (left !=0){
                set.remove(s.charAt(left-1));
            }
            while (n-left >= ans && right+1 < n && !set.contains(s.charAt(right+1))){
                set.add(s.charAt(right+1));
                ++right;
                ans = Math.max(ans,right-left+1);
            }
        }
        return ans;
    }
}
```

结果：通过！时间复杂度O(n)，空间复杂度O(∣Σ∣)，Σ为字符串中可能出现的字符的数量。
