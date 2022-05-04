# 剑指Offer 036.后缀表达式

根据**逆波兰表示法**，求该后缀表达式的计算结果。

有效的算符包括`+`、`-`、`*`、`/`。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：

- 整数除法只保留整数部分

- 给定逆波兰表达式总是有效的。

2022/5/4 第一次刷

初见思路：就是利用栈实现逆波兰表示法。

代码如下：

```java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (isNumber(s)) {
                stack.push(Integer.parseInt(s));
            } else {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(isOperator(a, b, s));
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String s) {
        return !("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s));
    }

    public int isOperator(int a, int b, String s) {
        int ans = 0;
        switch (s) {
            case "+":
                ans = a + b;
                break;
            case "-":
                ans = b - a;
                break;
            case "*":
                ans = a * b;
                break;
            case "/":
                ans = b / a;
                break;
            default:
        }
        return ans;
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(n)
