# 剑指Offer 018.有效回文

给定一个字符串`s`，验证`s`是否是**回文串**，只考虑字母和数字字符，可以忽略字母的大小写。空字符串定义为有效的**回文串**。

2022/4/24 第一次刷

初见思路：利用API，判断字符是否是字母或者数字，同时将字母转为小写，接着比较该字符串与其倒置后的字符串是否相等。

代码如下：

```java
class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); ++i){
            if(Character.isLetterOrDigit(s.charAt(i))){
                stringBuilder.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        return stringBuilder.toString().equals(stringBuilder.reverse().toString());
    }
}
```

结果：通过！！！ 时间复杂度O(n)
