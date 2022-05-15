# 剑指Offer 055.二叉搜索树迭代器

实现一个二叉搜索树迭代器类`BSTIterator`，表示一个按中序遍历二叉搜索树（BST）的迭代器：

- `BSTIterator(TreeNode root)`初始化`BSTIterator`类的一个对象。BST的根节点`root`会作为构造函数的一部分给出。指针应初始化为一个不存在BST中的数字，且该数字小于BST中的任何元素。

- `boolean hasNext()`如果想指针右侧遍历存在数字，则返回`true`；否则返回`false`

- `int next()`将指针向右移动，然后返回指针处的数字。

注意，指针初始化为一个不存在与BST中的数字，所以对`next()`的首次调用将返回BST的最小元素。

可以假设`next()`调用总是有效的，也就是说，当调用`next()`时，BST的中序遍历至少存在一个下一个数字。

2022/5/15 第一次刷

初见思路：直接遍历一遍二叉树，同时将结果保存到数组当中，然后利用数据进行迭代

代码如下：

```java
class BSTIterator {

    private List<Integer> list;
    private int index;

    public BSTIterator(TreeNode root) {
        index = 0;
        list = new ArrayList<>();
        inorderTraversal(root, list);
    }

    public int next() {
        return list.get(index++);
    }

    public boolean hasNext() {
        return index < list.size();
    }

    private void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(n)
