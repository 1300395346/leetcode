# 剑指Offer 033.变位词组

给定一个字符串数组`strs`，将**变位词**组合在一起。可以按任意顺序返回结果列表。

**注意**：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。

2022/5/2 第一次刷

初见思路：变位词重新排序过后的形成的字符串是相同的，因此设置一个map，key为重新排序后的字符串，value为该字符串原本的形状。

代码如下：

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            String key = new String(temp);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
}
```

结果：通过！！ 时间复杂度O(nklogk)，空间复杂度O(nk)，n为strs的长度，k为该字符串数组中最长字符串的长度，需要遍历每一个字符串并进行排序，因此时间复杂度为O(nklogk)，同时需要将所有字符串都存入哈希表中，因此空间复杂度为O(nk)
