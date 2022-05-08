# 剑指Offer 044.二叉树每层的最大值

给定一棵二叉树的根节点`root`，请找出该二叉树中每一层的最大值。

2022/5/8 第一次刷

初见思路：与上一题思路相同，利用队列进行层次遍历，计算每一层的最大值。(题解提示：给定一个整数count用于计算是否进入下一层)

代码如下：

```java
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return Collections.emptyList();
        }
        queue.add(root);
        int max = Integer.MIN_VALUE;
        int count = queue.size();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            count--;
            max = Math.max(max, node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            if (count == 0) {
                count = queue.size();
                ans.add(max);
                max = Integer.MIN_VALUE;
            }
        }
        return ans;
    }
}
```

结果：通过！！
