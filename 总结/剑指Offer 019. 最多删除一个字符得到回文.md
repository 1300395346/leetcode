# 剑指Offer 019. 最多删除一个字符得到回文

给定一个非空字符串`s`，请判断如果**最多**从字符串中删除一个字符能否得到一个回文字符串。

2022/4/25 第一次刷

初见思路：双指针，判断两个字符是否相同，当两个字符不同时可以跳过该字符，判断下一个字符是否是回文字符

代码如下：

```java
class Solution {
    public boolean validPalindrome(String s) {
        int len = s.length();
        int left = 0;
        int right = len - 1;
        while (left <= right){
            Character start = s.charAt(left);
            Character end = s.charAt(right);
            if (start.equals(end)){
                ++left;
                --right;
            } else {
                int lNext = left+1;
                int rNext = right-1;
                Character sNext = s.charAt(lNext);
                Character eNext = s.charAt(rNext);
                if (lNext<=right && sNext.equals(end)){
                    left = ++lNext;
                    right--;
                } else if (left <= rNext && start.equals(eNext)){
                    ++left;
                    right = --rNext;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
```

结果：解答错误！！ 最后执行输入："eeccccbebaeeabebccceea"。错误原因：最多删除一个字符，而代码未曾考虑这一点。修改建议：增加一个变量count记录删除的字符数量。

修改后代码如下：

```java
class Solution{    
    public boolean validPalindrome(String s) {
        int len = s.length();
        int left = 0;
        int right = len - 1;
        int count = 0;
        while (left <= right){
            Character start = s.charAt(left);
            Character end = s.charAt(right);
            if (start.equals(end)){
                ++left;
                --right;
            } else {
                int lNext = left+1;
                int rNext = right-1;
                Character sNext = s.charAt(lNext);
                Character eNext = s.charAt(rNext);
                if (lNext<=right && sNext.equals(end) && count < 1){
                    left = ++lNext;
                    --right;
                    ++count;
                } else if (left <= rNext && start.equals(eNext) && count < 1){
                    ++left;
                    right = --rNext;
                    ++count;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
```

结果：解答错误！！最后执行输入："aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"。错误原因：未考虑删除开头字符之后的字符虽然与结尾字符相同，但不能构成回文，而删除结尾字符后字符与开头字符相同且能构成回文字符串。 修改建议：同时比较删除后的字符串开头结尾的下一个字符是否相同。

修改后代码如下：

```java
class Solution {
    public boolean validPalindrome(String s) {
        int len = s.length();
        int left = 0;
        int right = len - 1;
        int count = 0;
        while (left <= right){
            Character start = s.charAt(left);
            Character end = s.charAt(right);
            if (start.equals(end)){
                ++left;
                --right;
            } else {
                int lNext = left+1;
                int rNext = right-1;
                Character sNext = s.charAt(lNext);
                Character eNext = s.charAt(rNext);
                Character a = s.charAt(lNext+1);
                Character b = s.charAt(rNext-1);
                if (lNext<=right && sNext.equals(end) && count < 1 && a.equals(eNext)){
                    left = ++lNext;
                    --right;
                    ++count;
                } else if (left <= rNext && start.equals(eNext) && count < 1 && b.equals(sNext)){
                    ++left;
                    right = --rNext;
                    ++count;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
```

结果：解答错误！！ 最后执行输入："abca"。错误原因：考虑删除之后的字符的下一个字符是否相同的思路是错误的，这样会导致指针越界。修改建议：采取递归的方法。

修改后代码：

```java
class Solution{
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;
        while (left < right){
            char c1 = s.charAt(left);
            char c2 = s.charAt(right);
            if (c1 == c2){
                ++left;
                --right;
            } else {
                return isPalindrome(s,left,right-1)||isPalindrome(s,left+1,right);
            }
        }
        return true;
    }
    public boolean isPalindrome(String s, int start, int end){
        for (int i = start, j = end; i < j; ++i,--j){
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            if(c1 != c2){
                return false;
            }
        }
        return true;
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(1)
