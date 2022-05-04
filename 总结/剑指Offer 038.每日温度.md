# 剑指Offer 038.每日温度

请根据每日`气温`列表`temperature`，重新生成一个列表，要求其对应位置的输出为：想要观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用`0`来代替。

2022/5/4 第一次刷

初见思路：暴力循环，遍历数组，找出比当前气温高的天数，计算需要等待的天数

代码如下：

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] ans = new int[len];
        for (int i = 0; i < len - 1; ++i) {
            for (int j = i + 1; j < len; ++j) {
                if (temperatures[i] < temperatures[j]) {
                    ans[i] = j - i;
                    break;
                }
            }
        }
        ans[len - 1] = 0;
        return ans;
    }
}
```

结果：通过！！ 时间复杂度O(n^2)，空间复杂度O(n)

代码优化：（参考了官方题解）由于当日天气在30~100之间，我们只需要维护这之间的每一个温度第一次出现的时间。然后从右到左遍历一遍气温数组，计算从当天开始第一次出现气温比其高的那天距今的天数。

代码如下：

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] next = new int[101];
        int[] ans = new int[len];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = len - 1; i >= 0; --i) {
            int temp = Integer.MAX_VALUE;
            for (int j = temperatures[i] + 1; j < 101; ++j) {
                if (next[j] < temp) {
                    temp = next[j];
                }
            }
            if (temp < Integer.MAX_VALUE) {
                ans[i] = temp - i;
            }
            next[temperatures[i]] = i;
        }
        return ans;
    }
}
```

结果：通过！！ 时间复杂度O(nm)，空间复杂度O(m)，其中n为气温数组的长度，m为next数组的长度。

继续优化：（仍然是参考了官方题解）维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度应该是递增的。这意味着当某个下标还存在于栈中，则该下标对应的温度还未找到比它气温要高的那一天。遍历气温数组，若栈为空，则将下标入栈；若不为空，则判断栈顶下标对应的气温是否比当前气温低：若低则栈顶元素出栈，同时将当前元素的下标减去出栈下标的值即出栈下标对应温度找到比自己气温的那天需要的天数。

代码实现如下：

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int len = temperatures.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; ++i) {
            int temp = temperatures[i];
            while (!stack.isEmpty() && temp > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(n)

**在实现栈时如果允许请自行定义栈的实现，若不允许则尽量使用Deque双端队列来实现栈而不是采用Stack来实现，因为Stack是继承了Vector实现的，因此允许通过指定索引插入数据；Deque实际上是双端队列，因此其实也不满足栈的具体定义的。** 
