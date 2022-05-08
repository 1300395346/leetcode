# 剑指Offer 045.二叉树最底层最左边的值

给定一个二叉树的**根节点**`root`，请找出该二叉树**最底层** **最左边**节点的值

假设二叉树中至少有一个节点。

2022/5/8 第一次刷

初见思路：BFS和DFS应该都可以解此题

BFS代码如下：

```java
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        int ans = root.val;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int count = queue.size();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            count--;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            if (count == 0) {
                count = queue.size();
                ans = queue.isEmpty() ? ans : queue.peek().val;
            }
        }
        return ans;
    }
}
```

结果：通过！！

DFS思路：先序遍历，同时计算树的深度，当深度最大时对应的节点就是最左最底层的节点。利用递归实现。

DFS代码如下：

```java
class Solution {
    int height = 0;
    TreeNode max = null;

    //DFS
    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 1);
        return max.val;
    }

    public void dfs(TreeNode root, int h) {
        if (root == null) {
            return;
        }
        if (h > height) {
            height = h;
            max = root;
        }
        dfs(root.left, h + 1);
        dfs(root.right, h + 1);
    }
}
```

结果：通过！
