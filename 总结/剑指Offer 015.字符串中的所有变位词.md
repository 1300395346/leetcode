# 剑指Offer 015.字符串中的所有变位词

给定两个字符串`s`和`p`，找到`s`中所有`p`的**变位词**的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

**变位词**指字母相同，但排列不同的字符串。

2022/4/22 第一次刷到

初见思路：与上道题类似，根据p的长度，在s中截取子串，记录每个字母出现的次数，然后与p中每个字母出现的次数比较，若相等，则将该子串的起始索引加入list当中。

代码如下：

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int len1 = s.length();
        int len2 = p.length();
        int[] n1 = new int[26];
        int[] n2 = new int[26];
        List<Integer> ans = new ArrayList<>();
        if (len2 > len1){
            return ans;
        }
        for (int i = 0; i < len2; ++i){
            ++n1[s.charAt(i) - 'a'];
            ++n2[p.charAt(i) - 'a'];
        }
        if (Arrays.equals(n1,n2)){
            ans.add(0);
        }
        for (int i = len2; i < len1; ++i){
            ++n1[s.charAt(i) - 'a'];
            --n1[s.charAt(i-len2) - 'a'];
            if (Arrays.equals(n1,n2)){
                ans.add(i-len2+1);
            }
        }
        return ans;
    }
}
```

结果：通过！！

复杂度分析

- 时间复杂度：`O(m+(n−m)×Σ)`，其中 n 为字符串 `s` 的长度，`m` 为字符串 `p` 的长度，`Σ` 为所有可能的字符数。我们需要`O(m)`来统计字符串 `p` 中每种字母的数量；需要 `O(m)` 来初始化滑动窗口；需要判断 `n−m+1` 个滑动窗口中每种字母的数量是否与字符串 `p` 中每种字母的数量相同，每次判断需要 `O(Σ)` 。因为 `s` 和 `p` 仅包含小写字母，所以 `Σ=26`。

- 空间复杂度：`O(Σ)`。用于存储字符串 `p` 和滑动窗口中每种字母的数量。
