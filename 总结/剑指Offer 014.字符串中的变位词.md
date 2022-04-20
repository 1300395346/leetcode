# 剑指Offer 014.字符串中的变位词

给定两个字符串`s1`和`s2`，写一个函数来判断`s2`是否包含`s1`的某个变位词。

换句话说，第一个字符串的排列之一是第二个字符串的**子串**。

2022/4/20第一次刷

初见思路：判断`s1`字符串的长度，然后依次长度为窗口判断字符串`s2`中是否存在`s1`的变位词

代码如下：

```java
class Solution {
    public boolean checkInclusion(String s1, String s2){
        int len1 = s1.length();
        int len2 = s2.length();
        int n1 = 0;
        int n2 = 0;
        for (int i = 0; i < len1; ++i){
            n1 += s1.charAt(i) - 'a';
            n2 += s2.charAt(i) - 'a';
        }
        if (len1 > len2){
            return false;
        }
        if (n1 == n2){
            return true;
        }
        int j = len1-1;
        for (int i = 1; i < len2-j; ++i){
            n2 += s2.charAt(i+j);
            n2 -= s2.charAt(i-1);
            if (n1 == n2){
                return true;
            }
        }
        return false;
    }
}
```

结果：解答错误！最后执行输入："abc" "ccccbbbbaaaa"

错误原因：如此相减并不能确保相等时一定是相同的字母组合

修改：n1记录字符串s1中每个字母出现的次数，n2记录s2字符串中与s1等长的子字符串字母出现的次数，若两个数组相同，则返回true，否则返回false

代码如下：

```java

```


