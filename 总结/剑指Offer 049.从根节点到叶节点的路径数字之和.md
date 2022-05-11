# 剑指Offer 049.从根节点到叶节点的路径数字之和

给定一个二叉树的根节点`root`，树中每个节点都存放有一个`0`到`9`之间的数字

每条根节点到叶节点的路径都代表一个数字：

- 例如，从根节点到叶节点的路径`1->2->3`表示数字`123`。

计算从根节点到叶节点生成的**左右数字之和**。

**叶节点**是指没有子节点的节点。

2022/5/11 第一次刷

初见思路：BFS，层次遍历，维护两个队列，一个队列存节点，一个队列存节点值。

代码如下：

```java
 class Solution {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> num = new LinkedList<>();
        num.add(root.val);
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int nums = num.poll();
            if (node.left == null && node.right == null) {
                result += nums;
            } else {
                if (node.left != null) {
                    queue.add(node.left);
                    num.offer(nums * 10 + node.left.val);
                }
                if (node.right != null) {
                    queue.add(node.right);
                    num.offer(nums * 10 + node.right.val);
                }
            }
        }
        return result;
    }
}
```

结果：通过！

思路二：dfs，遇到叶子节点就计入总和，否则返回其值并对其子节点递归遍历

代码如下：

```java
class Solution {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int preSum) {
        if (root == null) {
            return 0;
        }
        int sum = preSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }
}
```

结果：通过！
