# 剑指Offer 043.往完全二叉树添加节点

完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第`n`层有`2^(n-1)`个节点，并且所有节点都尽可能的集中在左侧。

设计一个用完全二叉树初始化的数据结构`CBTInserter`，它支持以下几种操作：

- `CBTInserter(TreeNode root)`使用根节点为`root`的给定树初始化该数据结构；

- `CBTInserter.insert(int v)`向树中插入一个新节点，节点类型为`TreeNode`，值为`v`。使树保持完全二叉树的状态，**并返回插入的新节点的父节点的值**；

- `CBTInserter.get_root()`将返回树的根节点。

2022/5/8 第一次刷

初见思路：考察的为树的层次遍历，根据给定的根节点，依次遍历树的每一层并从左到右加入节点，同时记录最后一个加入的节点为左节点还是右节点。向树中插入节点是先看最后一个插入的节点是否为左节点，若为做节点，则直接插入，若为右节点，则寻找下一个允许插入的节点。(题解提示：利用队列完成层次遍历)

代码如下：

```java
class CBTInserter {

   private final Queue<TreeNode> queue;
    private final TreeNode root;
    private boolean isLeft;

    public CBTInserter(TreeNode root) {
        this.root = root;
        queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.peek();
            if (node.left == null) {
                isLeft = true;
                break;
            } else {
                queue.add(node.left);
            }
            if (node.right == null) {
                isLeft = false;
                break;
            } else {
                queue.add(node.right);
                queue.poll();
            }
        }
    }

    public int insert(int v) {
        TreeNode father;
        TreeNode child = new TreeNode(v);
        if (isLeft) {
            father = queue.peek();
            father.left = child;
        } else {
            father = queue.poll();
            father.right = child;
        }
        queue.add(child);
        isLeft = !isLeft;
        return father.val;
    }

    public TreeNode get_root() {
        return root;
    }
}
```

结果：通过！！
