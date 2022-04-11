# 剑指Offer 002 二进制加法

2022/4/11 第一次刷

给定两个 01 字符串 `a` 和 `b` ，请计算它们的和，并以二进制字符串的形式输出。

输入为 **非空** 字符串且只包含数字 `1` 和 `0`。

提示：

- 每个字符串仅由字符 '0' 或 '1' 组成。

- 1 <= a.length, b.length <= 10^4

- 字符串如果不是 "0" ，就都不含前导零。

初见思路：将a，b两个01字符串划分成单个字符，然后根据二进制加法的规则进行计算，注意：1+1=0，同时要高位进1。 

```
class Solution {
        public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder result = new StringBuilder();
        while (i >= 0 || j >= 0){
            int A = i >= 0 ? a.charAt(i--) - '0' : 0;
            int B = j >= 0 ? b.charAt(j--) - '0' : 0;
            int sum = A +  B;
            carry = sum >= 2 ? 1 : 0;
            sum = sum>=2 ? sum - 2 : sum;
            result.append(sum);
        }
        if(carry == 1){
            result.append(carry);
        }
        return result.reverse().toString();
    }
}
```

结果：答案错误；最后执行输入：a = "1010",b = "1011"

错误原因：忘记加上进位；

修改后代码：

```
int sum = A + B + carry;
```

结果：通过


