# 剑指Offer 046.二叉树的右侧视图

给定一个二叉树的**根节点**`root`，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧能看到的节点值。

2022/5/9 第一次刷

初见思路：层次遍历（BFS）获取每一层最右侧的节点。

代码如下：

```java
class Solution {
       public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root == null) {
            return Collections.emptyList();
        }
        queue.add(root);
        int temp = queue.size();
        int result;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            temp--;
            result = node.val;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            if (temp == 0) {
                temp = queue.size();
                ans.add(result);
            }
        }
        return ans;
    }
}
```

结果：通过！！

思路二：DFS，前序遍历，同时记录遍历的深度，遍历时将每一层的节点写入同一个index的list中，这样最右边的节点的值最后得到保留。dfs函数要写结束条件，当索引值等于list的大小时，需要向list中加入一个0，扩大list。

代码如下：

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    public void dfs(TreeNode root, int depth, List<Integer> ans) {
        if (root == null) {
            return;
        }
        if (depth == ans.size()) {
            ans.add(0);
        }
        ans.set(depth++, root.val);
        dfs(root.left, depth, ans);
        dfs(root.right, depth, ans);
    }
}
```

结果：通过！
