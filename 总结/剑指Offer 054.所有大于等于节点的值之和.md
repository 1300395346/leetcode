# 剑指Offer 054.所有大于等于节点的值之和

给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或等于该节点值的左右节点值之和。

2022/5/15 第一次见

初见思路：由于该二叉树是二叉搜索树，因此每个节点的值应该是更改为他本身与他的右子树中所有节点值之和。因此，我们只需要以右子树-》根节点-》左子树的顺序遍历该二叉树，在遍历的同时更新节点值即可。

代码如下：

```java
class Solution{
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
```

结果：解答错误！！错误原因：每次递归时都重新令sum=0，因此实际上并没有对节点的值进行更新，sum应该作为类变量初始化而不是作为局部变量初始化。

修改后代码如下：

```java
class Solution {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
```

结果：通过！ 时间复杂度O(n)，空间复杂度O(n):为递归过程中栈的开销，平均情况下为 O(logn)，最坏情况下树呈现链状，为 O(n)。



思路二：（来自官方题解）Morris遍历

Morris遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。其反序中序遍历规则总结如下：

1. 如果当前节点的右子节点为空，处理当前节点，并遍历当前节点的左子节点；

2. 如果当前节点的右子节点不为空，找到当前节点右子树的最左节点（该节点为当前节点中序遍历的前驱节点）：
   
   1.    如果最左节点的左指针为空，将最左节点的左指针指向当前节点，遍历当前节点的右子节点；
   
   2. 如果最左节点的左指针不为空，将最左节点的左指针重新置为空（恢复树的原状），处理当前节点，并将当前节点置为其左节点；       

3. 重复步骤1和步骤2，知道遍历结束。

这样我们利用Morris遍历的方法，反序中序遍历该二叉搜索树，即可实现线性时间与常数空间的遍历。

代码如下：

```java
class Solution {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        TreeNode node = root;

        while (node != null) {
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                TreeNode curr = getLeftNode(node);
                if (curr.left == null) {
                    curr.left = node;
                    node = node.right;
                } else {
                    curr.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }

        return root;
    }

    public TreeNode getLeftNode(TreeNode node) {
        TreeNode temp = node.right;
        while (temp.left != null && temp.left != node) {
            temp = temp.left;
        }
        return temp;
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(1)
