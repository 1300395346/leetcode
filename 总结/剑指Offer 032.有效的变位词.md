# 剑指Offer 032.有效的变位词

给定两个字符串`s`和`t`，编写一个函数来判断它们是不是一组变位词（字母异位词）。

**注意**：若`s`和`t`中每个字符出现的次数都相同且**字符顺序不完全相同**，则称`s`和`t`互为变位词（字母异位词）。

2022/5/2 第一次刷

初见思路：计算每个字母出现的次数，当两个字母出现的次数相同且s和t不相等，则返回true

代码如下：

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] sNum = new int[26];
        int[] tNum = new int[26];
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); ++i) {
            sNum[s.charAt(i) - 'a']++;
            tNum[t.charAt(i) - 'a']++;
        }
        return Arrays.equals(sNum, tNum) && !s.equals(t);
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(S) S=26
