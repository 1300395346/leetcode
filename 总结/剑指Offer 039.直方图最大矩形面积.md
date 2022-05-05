# 剑指Offer 039.直方图最大矩形面积

给定非负整数数组`heights`，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为`1`。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

2022/5/5 第一次刷

初见思路：暴力循环

代码如下：

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = heights[0];
        int len = heights.length;
        for (int i = 0; i < len; ++i) {
            int h = heights[i];
            for (int j = i; j < len; ++j) {
                int wide = j - i + 1;
                h = Math.min(h, heights[j]);
                max = Math.max(max, wide * h);
            }
        }
        return max;
    }
}
```

结果：超出时间限制  时间复杂度为O(n^2)，当数据量过大时，显然是超出时间限制的。

思路二：（参考了官方题解）利用单调栈。首先，我们需要考虑如何找到最大矩形面积的高。

- 首先，我们枚举某一根柱子i作为高h=heigh[i]；

- 随后，我们需要进行向左右两边的扩展，使得扩展到的柱子的高度均不小于h。换句话说，我们需要找到**左右两侧最近的高度小于h的柱子**，这样这两根柱子之间（不包括其本身）的所有柱子高度均不小于h，并且就是i能够扩展到的最远范围。

如果有两根柱子j0和j1，其中j0在j1的左侧，并且j0的高度大于j1，那么在j1后面的柱子i在向左找小于其高度的柱子时，j1会*挡住*j0，j0不会作为答案。

这样一来，我们可以对数组从左到右进行遍历，同时维护一个*可能作为答案*的数据结构，其中按照从小到大的顺序存放了一些j值。根据上面的结论，j值对应的高度一定是递增的。因此，我们在枚举到第i根柱子时，可以把结构中所有高度大于height[i]的j值移除，然后结构中剩下的j值中高度最高的就是答案。随后，我们将i放入数据结构中，继续接下来的枚举。由此可以看出，我们需要使用的数据结构就是**栈**.

- 栈中存放了j值。从栈底到栈顶，j的值严格单调递增，同时对应的高度值也严格单调递增；

- 当我们枚举到第i根柱子时，我们从栈顶不断地移除height[j]≥height[i]的j值。在移除完毕后，栈顶的j值就一定满足height[j]＜height[i]，此时的j就是i左侧且最近的小于其高度的柱子
  
  - 这里有一种特殊情况。如果我们移除了栈中所有的j值，那说明i左侧的所有柱子高度都大于height[i]，那么我们可以认为i左侧最近的小于其高度的柱子的位置j=-1，它是一根*虚拟*的、高度无限低的柱子。这样的定义不会对我们的答案产生任何影响，我们也称这根*虚拟*的柱子为*哨兵*.

- 我们再将i放入栈顶。

栈中存放的元素具有单调性，这就是经典的数据结构*单调栈*。

代码实现如下：

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; ++i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        for (int i = len - 1; i >= 0; --i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? len : stack.peek();
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < len; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(n)

对单调栈进行常数优化（官方题解）：

在单调栈的方法中，我们首先从左往右对数组进行遍历，借助单调栈求出了每根柱子的左边界，随后从右往左对数组进行遍历，借助单调栈求出了每根柱子的右边界。那么我们是否可以只遍历一次就求出答案呢？

答案是可以的。在上述方法中，我们对位置i进行入栈操作时，确定了它的左边界。从直觉上来说，与之对应的，我们在对位置i进行出栈操作时，可以确定它的右边界。仔细想一想，这确实是对的。当位置i被弹出栈时，说明此事遍历到的位置i0的高度**小于等于**height[i]，并且在i0与i之间没有其他高度小于等于height[i]的柱子。这是因为，如果在i和i0之间还有其他位置的高度小于等于height[i]的，那么在遍历到那个位置的时候，i应该已经被弹出栈了。所以位置i0就是位置i的右边界。

值得注意的是，我们需要的是*一根柱子的左侧且最近的**小于**其高度的柱子*，但这里我们求的是**小于等于**，那么会造成什么影响呢？答案是：我们确实无法求出正确的右边界，但是对于最终答案没有任何影响。这是因为在答案对应的矩阵中，如果有若干个柱子的高度都等于矩形的高度，那么**最右侧的那根柱子是可以求出正确的右边界的**，而我们没有对求出左边界的算法进行任何修改，因此最终答案还是可以从最右侧的与矩形高度相同的柱子求得的。

在遍历结束后，栈中仍然有一些位置，这些位置对应的就是位置为n的*哨兵*。我们可以将他们以此出栈并更新右边界，也可以在初始化右边界数组时就将所有元素的值置为n。

代码如下：

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(right, len);

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; ++i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < len; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }

        return ans;
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(n)


