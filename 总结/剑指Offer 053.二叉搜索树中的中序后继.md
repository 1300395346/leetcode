# 剑指Offer 053.二叉搜索树中的中序后继

给定一棵二叉搜索树和其中一个节点`p`，找到该节点在数中的中序后继。如果节点没有中序后继，请返回`null`。

节点`p`的后继是值比`p.val`大的节点中键值最小的节点，即按中序遍历的顺序节点`p`的下一个节点。

2022/5/13 第一次刷

初见思路：中序遍历这棵二叉树，将搜索到的节点存入list当中，然后在找指定节点的中序后继。

代码如下：

```java
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        if (list.contains(p)) {
            int index = list.indexOf(p);
            for (int i = index; i < list.size(); ++i) {
                if (list.get(i).val > p.val) {
                    return list.get(i);
                }
            }
        }
        return null;
    }

    public void dfs(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        dfs(node.left, list);
        list.add(node);
        dfs(node.right, list);
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(n)；

思路二：参考了官方题解，利用栈进行中序遍历，降低了平均的空间复杂度

栈实现中序遍历：利用栈先进后出的特点，判断当前节点是否有左节点，有则将当前节点压入栈中，同时将当前节点更新为其左节点。直到当前节点为null，然后将当前节点的值更新为栈顶元素，此时节点为二叉树的最左节点，然后判断其是否存在右节点，有则将该节点再次压入栈中同时循环执行上述操作。

代码如下：

```java
class Solution{
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode prev = null, curr = root;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (prev == p) {
                return curr;
            }
            prev = curr;
            curr = curr.right;
        }
        return null;
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度平均为O(logn)，最差为O(n)

思路三：利用二叉搜索树的性质

二叉搜索树的一个性质是中序遍历序列单调递增，因此二叉搜索树中的节点 pp 的中序后继满足以下条件：

- 中序后继的节点值大于p的节点值；

- 中序后继是节点值大于p的节点值的所有节点中节点值最小的一个节点。

利用二叉搜索树的性质，可以在不做中序遍历的情况下找到节点p的中序后继。

如果节点p的右子树不为空，则节点p的中序后继在其右子树中，在其右子树中定位到最左边的节点，即为节点p的中序后继。

如果节点p的右子树为空，则需要从根节点开始遍历寻找节点p的祖先节点。

将答案初始化为null。用node表示遍历到的节点，初始时，node = root。每次比较node的节点值和p的节点值，执行相应操作：

- 如果node的节点值大于p的节点值，则p的中序后继可能是node或者在node的左子树中，因此用node更新答案，并将node移动到其左子节点继续遍历；

- 如果node的节点值小于或等于p的节点值，则p的中序后继可能在node的右子树中，因此将node移动到其右子节点继续遍历。

由于在遍历过程中，当且仅当node的节点值大于p的节点值的情况下，才会用node更新答案，因此当节点p有中序后继时一定可以找到中序后继，当节点p没有中序后继时答案一定为null。

代码如下：

```java
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode ans = null;
        if (p.right != null) {
            ans = p.right;
            while (ans.left != null) {
                ans = ans.left;
            }
            return ans;
        }
        TreeNode node = root;
        while (node != null) {
            if (node.val > p.val) {
                ans = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return ans;
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(1)
