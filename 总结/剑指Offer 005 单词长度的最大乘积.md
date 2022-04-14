# 剑指Offer 005 单词长度的最大乘积

给定一个字符串数组 `words`，请计算当两个字符串 `words[i]` 和 `words[j]` 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。

2022/4/13 第一次刷

初见思路：遍历字符串数组`wrods`中所有的字符串，判断两个字符串之间是否存在相同字母，不存在，则计算两个字符串的乘积，存在则跳出循环。

难点：判断两个字符串之间是否存在相同字母

        思路一：遍历，时间复杂度为O(n^2)

        代码如下：

```java
Class Solution{
    public int maxProduct(String[] words) {
        int len = words.length;
        int ans = 0;
        for (int i = 0; i < len-1; i++){
            for (int j = i+1; j < len; j++){
                int temp = judge(words[i],words[j]);
                if (ans<temp){
                    ans = temp;
                }
            }
        }
        return ans;
    }

    public int judge(String a, String b){
        int ans = 0;
        int i = a.length();
        int j = b.length();
        boolean flag = true;
        for (int m = 0; m < i; m++){
            for (int n = 0; n < j; n++){
                Character x = a.charAt(m);
                Character y = b.charAt(n);
                if (x.equals(y)){
                    flag = false;
                }
            }
        }
        if (flag){
            ans = i * j;
        }
        return ans;
    }
}
```

结果：超出时间限制；最后执行输入：省略；

原因：时间复杂度为$O(∑0≤i<j<n ​li​×lj​)$，时间复杂度远远超过O(n^2)。

        思路二：按位运算——将每个字符串转换成二十六位的掩码，a~z对应对应0~25位上的数字，若存在，则为1；不存在，则为0。然后遍历整个字符串数组两两字符串进行&运算，若结果为0，则表示不存在相同字母。

代码如下：

```java
Class Solution{
        public int maxProduct(String[] words){
        int len = words.length;
        int[] binary = new int[len];
        for (int i =0; i < len; i++){
            for (int j = 0; j < words[i].length(); j++){
                binary[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }

        int ans = 0;

        for (int i = 0; i < len - 1; i++){
            for (int j = i + 1; j < len; j++){
                if ((binary[i] & binary[j]) == 0){
                    ans = Math.max(ans,words[i].length()*words[j].length());
                }
            }
        }
        return ans;
    }
}
```

结果：通过；时间复杂度为O(L+n^2) L为数组words中所有字符串的总长度
