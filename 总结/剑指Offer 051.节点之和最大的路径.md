# 剑指Offer 051.节点之和最大的路径

**路径**被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在同一条路径序列中**至多出现一次**。该路径**至少包含一个**节点，且不一定经过根节点。

**路径和**是路径中各节点值的总和。

给定一个二叉树的根节点`root`，返回其**最大路径和**，即所有路径节点值之和的最大值。

2022/5/12 第一次刷

初见思路：递归遍历，遍历时更新答案，更新规则是：若节点值加上左子树的值或右子树的值大于节点值本身，则更新，否则保留答案本身。

代码如下：

```java
class Solution {
    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        if (root == null) {
            return 0;
        }
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        ans = Math.max(ans, left + right + root.val);
        return root.val + Math.max(left, right);
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(n)
