# 剑指Offer 056.二叉搜索树中两个节点之和

给定一个二叉搜索树的**根节点**`root`和一个整数`k`，请判断该二叉树中是否存在两个节点它们的值之和等于`k`。假设二叉搜索树中节点的值均唯一。

2022/5/16 第一次刷

初见思路：以节点对应的值作为index保存节点，然后遍历集合判断是否存在解

代码如下：

```java
class Solution {
    Map<Integer, TreeNode> map;

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        map = new HashMap<>();
        dfs(root, 12);
        for (Map.Entry<Integer, TreeNode> entry : map.entrySet()) {
            int temp = entry.getKey();
            if (map.containsKey(k - temp) && k - temp != temp) {
                return true;
            }
        }
        return false;
    }

    public void dfs(TreeNode node, int target) {
        if (node == null) {
            return;
        }
        dfs(node.left, target);
        map.put(node.val, node);
        dfs(node.right, target);
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(n)


