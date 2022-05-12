# 剑指Offer 052.展平二叉搜索树

给你一颗二叉搜索树，请**按中序遍历**将其重新排列为一颗递增顺序搜索树，使树中最左边的节点成为树的根节点，并且按照每个节点没有左子节点，只有一个右子节点。

2022/5/12 第一次刷

初见思路：中序遍历二叉搜索树，保存遍历到的节点值，然后根据保存的节点值重新创建一个递增搜索树。

代码如下：

```java
class Solution {
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        TreeNode dummyNode = new TreeNode(-1);
        TreeNode curr = dummyNode;
        for (int value : list) {
            curr.right = new TreeNode(value);
            curr = curr.right;
        }
        return dummyNode.right;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(n)

在中序遍历的时候，修改节点指向就可以实现。具体地，当我们遍历到一个节点时，把它的左孩子设为空，并将其本身作为上一个遍历到的节点的右孩子。这里需要有一些想象能力。递归遍历的过程中，由于递归函数的调用栈保存了节点的引用，因此上述操作可以实现。下面的幻灯片展示了这样的过程。

代码如下：

```java
class Solution {
    private TreeNode resNode;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;

        inorder(root);
        return dummyNode.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        resNode.right = node;
        node.left = null;
        resNode = node;
        inorder(node.right);
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(n)
