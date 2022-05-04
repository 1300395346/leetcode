# 剑指Offer 034.外星语言是否排序

某种外星语言也使用英文小写字母，但可能顺序`order`不同。字母表的顺序（`order`）是一些小写字母的排列。

给定一组用外星语书写的单词`words`，以及其字母表的顺序`order`，只用给定的单词是在何种外星语中按字典排列时，返回`true`；否则返回`false`。

2022/5/4 第一次刷

初见思路：遍历order给每个字符赋值，返回遍历words中的每个单词计算其值，若按照递增排列，则返回true

代码如下：

```java
class Solution{
        public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; ++i) {
            map.put(order.charAt(i), i + 1);
        }
        int cur = 0;
        int temp = 0;
        int len = words.length;
        int index = 0;
        while (index < len) {
            temp = cur;
            System.out.println(temp);
            for (int i = 0; i < words[index].length(); ++i) {
                cur += map.get(words[index].charAt(i));
            }
            System.out.println(cur);
            if (cur < temp) {
                return false;
            }
            ++index;
        }
        return true;
    }
}
```

结果：解答错误！！最后执行输入：words = ["word","world","row"] ，order = "worldabcefghijkmnpqstuvxyz"

错误理由：思路出错！ 字典顺序并不是这样理解的，对于数字1、2、3......n的排列，不同排列的先后关系是从左到右逐个比较对应的数字的先后来决定的。例如对于5个数字的排列 12354和12345，排列12345在前，排列12354在后。按照这样的规定，5个数字的所有的排列中最前面的是12345，最后面的是 54321。

这意味着比较两个单词的字典序，应该依次遍历两个单词之间每个相同位置的字母的排序，若相同则比较下一个。直到遇到第一个不同的单词或某个单词结束。

因此修改后代码如下：

```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; ++i) {
            map.put(order.charAt(i), i + 1);
        }
        int wordLen = words.length;
        for (int i = 1; i < wordLen; ++i) {
            if (compare(words[i - 1], words[i], map) > 0) {
                return false;
            }
        }
        return true;
    }

    public int compare(String word1, String word2, HashMap<Character, Integer> map) {
        int len1 = word1.length();
        int len2 = word2.length();
        int len = Math.min(len1, len2);
        for (int i = 0; i < len; ++i) {
            if (!map.get(word1.charAt(i)).equals(map.get(word2.charAt(i)))) {
                return map.get(word1.charAt(i)) - map.get(word2.charAt(i));
            }
        }
        return len1 - len2;
    }
}
```

结果：通过！！ 时间复杂度O(n+m)，空间复杂度O(n)，其中n为字符集长度（在此处n = 26），m为字符串数组中所有字符串的总长度。
