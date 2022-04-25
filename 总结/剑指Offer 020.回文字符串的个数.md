# 剑指Offer 020.回文字符串的个数

给定一个字符串`s`，请计算这个字符串中有多少个回文字符串。

具有不同开始位置或结束位置的子串，即使是由相同的字符串组成，也会被视作不同的子串。

2022/4/25 第一次刷

初见思路：枚举回文中心的个数，分为奇数长度和偶数长度。

代码如下：

```java
class Solution {
    public int countSubstrings(String s) {
        int len = s.length();
        int ans = 0;
        for (int i = 0; i < len; ++i){
            int left = i;
            int right = i;
            while (left<=right&&left>=0&&right<len&&s.charAt(left)==s.charAt(right)){
                --left;
                ++right;
                ++ans;
            }
        }
        for (int i = 0; i < len-1; ++i){
            int left = i;
            int right = i+1;
            while (left<=right&&left>=0&&right<len&&s.charAt(left)==s.charAt(right)){
                --left;
                ++right;
                ++ans;
            }
        }
        return ans;
    }
}
```

结果：通过！！ 时间复杂度O(n^2)，空间复杂多O(1)

改进——考虑是否存在一个式子能够满足一个循环就能够同时考虑回文中心为奇数和回文中心为偶数的情况。（参考了官方题解）

回文长度n=4

![1650895293(1).jpg](C:\Users\86189\Desktop\leetcode\总结\1650895293(1).jpg)

可以归纳出规律：长度为`n`的字符串会生成`2n-1`组回文中心[$li$，$ri$​]，其中$li$=$⌊i/2⌋$，$ri=li+(imod2)$。

因此代码如下：

```java
class Solution {
    public int countSubstrings(String s){
        int len = s.length();
        int ans = 0;
        for (int i = 0; i < 2*len-1; ++i){
            int left = i/2;
            int right = i/2 + (i%2);
            while (left >= 0 && right < len && s.charAt(left)==s.charAt(right)){
                --left;
                ++right;
                ++ans;
            }
        }
        return ans;
    }
}
```

结果：通过！时间复杂度O(n^2)，空间复杂度O(1)
