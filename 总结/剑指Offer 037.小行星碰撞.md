# 剑指Offer 037.小行星碰撞

给定一个整数数组`asteroids`，表示在同一行的小行星。

对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。

找出碰撞后剩下的所有小行星。碰撞规则：两个相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星会爆炸。两颗移动方向相同的星星，永远不会发生爆炸，永远不会发生碰撞。

2022/5/4 第一次刷

初见思路：利用栈，遍历数组，当栈中存在数字时，取出栈中数字判断该数字与要压入栈中的数字是否会碰撞，会则递归该过程，不会则将数字压入栈中。

代码如下：

```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            judge(asteroid, stack);
        }
        int len = stack.size();
        int[] ans = new int[len];
        for (int i = len - 1; i >= 0; --i) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    public void judge(int cur, Stack<Integer> stack) {
        if (stack.isEmpty()) {
            stack.push(cur);
        } else {
            int temp = stack.pop();
            if (temp * cur > 0) {
                stack.push(temp);
                stack.push(cur);
            } else {
                if (temp > cur) {
                    if (temp > Math.abs(cur)) {
                        stack.push(temp);
                    } else if (temp < Math.abs(cur)) {
                        judge(cur, stack);
                    }
                } else {
                    stack.push(temp);
                    stack.push(cur);
                }
            }
        }
    }
}
```

结果：通过！！ 时间复杂度O(n^2)，空间复杂度O(n)，n为数组长度，最坏的情况下先遍历一遍数组将前n-1个元素入栈，然后从后往前遍历一遍数组出栈。

优化：stack.peek()获取栈顶元素而不出站，当栈为空，栈顶元素为负数或当前元素为正数时直接入栈；当栈顶元素小于等于当前元素的绝对值时，均需要将栈顶元素弹出，此时若是相等则抵消，小于则继续循环判断（continue代表着跳过此循环后面的语句，继续下一次循环，而break表示从此处结束循环，不进行循环内后面的语句而执行循环外后面的语句）。若栈顶元素大于当前元素，则不需要将当前元素压入栈中。

优化后代码如下：

```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        int len = asteroids.length;
        int index = 0;
        while (index < len) {
            if (stack.isEmpty() || stack.peek() < 0 || asteroids[index] > 0) {
                stack.push(asteroids[index]);
            } else if (stack.peek() <= -asteroids[index]) {
                if (stack.pop() < -asteroids[index]) {//此时已经将栈顶元素弹出了，若栈顶元素等于当前元素，则进入下一个元素的判断
                    continue;
                }
            }
            ++index;
        }
        int size = stack.size();
        int[] ans = new int[size];
        for (int i = size - 1; i >= 0; --i) {
            ans[i] = stack.pop();
        }
        return ans;
    }
}
```

结果：通过！！！
