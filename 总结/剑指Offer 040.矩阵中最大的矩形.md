# 剑指Offer 040.矩阵中最大的矩形

给定一个由`0`和`1`组成的矩阵`matrix`，找出只包含`1`的最大矩阵，并返回其面积。

**注意**：此题`matrix`输入格式为一维`01`字符串数组

2022/5/6 第一次刷

初见思路：（参考了官方题解）可以将矩阵转化成柱状图，这道题就变成了剑指Offer 039.找柱状图中最大的矩阵了。

代码如下：

```java
class Solution {
    public int maximalRectangle(String[] matrix) {
        int ans = 0;
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int cols = matrix[0].length();
        int[] heights = new int[cols];
        for (String s : matrix) {
            heights = getHeight(s, heights);
            ans = Math.max(ans, getAre(heights));
        }
        return ans;
    }

    public int getAre(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(right, len);
        for (int i = 0; i < len; ++i) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < len; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * height[i]);
        }

        return ans;
    }

    public int[] getHeight(String matrix, int[] heights) {
        int cols = matrix.length();
        int[] height = new int[cols];
        for (int i = 0; i < cols; ++i) {
            char temp = matrix.charAt(i);
            if (temp != '1') {
                height[i] = 0;
            } else {
                height[i] = heights[i] + 1;
            }
        }
        return height;
    }
}
```

结果：通过！！ 时间复杂度O(nm)，空间复杂度O(m)，n为矩阵的行数，m为矩阵的列数。


