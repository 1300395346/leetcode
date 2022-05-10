# 剑指Offer 047.二叉树剪枝

给定一个二叉树**根节点**`root`，树的每个节点的值要么是`0`，要么是`1`。请剪除该二叉树中所有节点值为`0`的子树。节点`node`的子树为`node`本身，已经所有`node`的后代。

2022/5/10 第一次刷

思路：将所有节点存入栈中，然后遍历栈，将值为0且不存在子节点的节点删除。(**不会实现**)

参考了题解：对这棵二叉树进行后序遍历，当遍历到的节点值为0的叶子节点时，将其删除。

代码如下：

```java
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }
}
```

结果：通过！！


