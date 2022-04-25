# 剑指Offer 017.含有所有字符的最短字符串

给定两个字符串`s`和`t`。返回`s`中包含`t`的所有字符串的最短子字符串。如果`s`中不存在符合条件的子字符串，则返回空字符串`""`。

如果`s`中存在多个符合条件的子字符串，返回任意一个。

**注意**：对于`t`中重复字符，我们寻找子字符串中该字符数量必须不少于`t`中该字符串数量。

2022/4/24 第一次刷

初见思路：滑动窗口+哈希表：用哈希表记录t中所有字符出现的次数，同时比较滑动窗口中框住的字符串是否含有t中的字符。

代码如下：

```java
class Solution {
    public String minWindow(String s, String t){
        int n = s.length();
        int m = t.length();
        if (n < m){
            return "";
        }
        int left = 0;
        int right = -1;
        int ansLeft = -1;
        int ansRight = -1;
        HashMap<Character,Integer> map = new HashMap<>();
        HashMap<Character,Integer> check = new HashMap<>();
        for (int i = 0; i < m; ++i){
            int value = map.getOrDefault(t.charAt(i),0);
            map.put(t.charAt(i),value+1);
        }
        int len = Integer.MAX_VALUE;
        while (right < n){
            ++right;
            if (right<n && map.containsKey(s.charAt(right))){
                check.put(s.charAt(right),check.getOrDefault(s.charAt(right),0)+1);
            }
            while (check(map,check)&&left<right){
                if (right - left + 1 < len){
                    len = right - left + 1;
                    ansLeft = left;
                    ansRight = len+left;
                }
                if (map.containsKey(s.charAt(left))){
                    check.put(s.charAt(left),check.get(s.charAt(left))-1);
                }
                ++left;
            }
        }
        return ansRight == -1?"":s.substring(ansLeft,ansRight);
    }
    public boolean check(HashMap<Character,Integer> map, HashMap<Character,Integer> map1){
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if (map1.getOrDefault(key, 0) < value) {
                return false;
            }
        }
        return true;
    }
}
```

结果：执行出错！！ 最后执行输入：s="a",t="a"

错误原因：当left==right时应当进入内循环，而我忽略了这个状态

修改后代码如下：

```java
class Solution {
    public String minWindow(String s, String t){
        int n = s.length();
        int m = t.length();
        if (n < m){
            return "";
        }
        int left = 0;
        int right = -1;
        int ansLeft = -1;
        int ansRight = -1;
        HashMap<Character,Integer> map = new HashMap<>();
        HashMap<Character,Integer> check = new HashMap<>();
        for (int i = 0; i < m; ++i){
            int value = map.getOrDefault(t.charAt(i),0);
            map.put(t.charAt(i),value+1);
        }
        int len = Integer.MAX_VALUE;
        while (right < n){
            ++right;
            if (right<n && map.containsKey(s.charAt(right))){
                check.put(s.charAt(right),check.getOrDefault(s.charAt(right),0)+1);
            }
            while (check(map,check)&&left<=right){
                if (right - left + 1 < len){
                    len = right - left + 1;
                    ansLeft = left;
                    ansRight = len+left;
                }
                if (map.containsKey(s.charAt(left))){
                    check.put(s.charAt(left),check.get(s.charAt(left))-1);
                }
                ++left;
            }
        }
        return ansRight == -1?"":s.substring(ansLeft,ansRight);
    }
    public boolean check(HashMap<Character,Integer> map, HashMap<Character,Integer> map1){
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if (map1.getOrDefault(key, 0) < value) {
                return false;
            }
        }
        return true;
    }
}
```

结果：通过！！ 
