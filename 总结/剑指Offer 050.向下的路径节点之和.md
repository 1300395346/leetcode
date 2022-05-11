# 剑指Offer 050.向下的路径节点之和

给定一个二叉树的根节点`root`，和一个整数`targetSum`，求该二叉树里节点值之和等于`targetSum`的**路径**的数目。

**路径**不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

2022/5/11 第一次刷

初见思路：DFS，遍历所有可能的节点，计算其中的和为目标值的路径的个数(参考了官方题解)

代码如下：

```java
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ans = dfs(root, targetSum);
        ans += pathSum(root.left, targetSum);
        ans += pathSum(root.right, targetSum);
        return ans;
    }

    public int dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        int temp = root.val;
        if (temp == targetSum) {
            ans++;
        }
        ans += dfs(root.left, targetSum - temp);
        ans += dfs(root.right, targetSum - temp);
        return ans;
    }
}
```

结果：通过！ 时间复杂度O(n^2)，空间复杂度O(n)

思路二：前缀和（有想到这个解法，但是不会实现）深度遍历每个节点，同时利用map存储每个节点的前缀和，key为从根节点到当前节点的前缀和，value为和为key值的路径的个数。同时需要注意的是，map中保存的节点的前缀和应该属于同一个路径，不同路径的节点不能进行加和，因此需要进行倒退操作。

代码如下：

```java
class Solution {
    Map<Integer, Integer> map = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        map.put(0, 1);
        return dfs(root, targetSum, 0);
    }

    public int dfs(TreeNode root, int targetSum, int curr) {
        if (root == null) {
            return 0;
        }
        int ans;
        curr += root.val;
        ans = map.getOrDefault(curr - targetSum, 0);
        map.put(curr, map.getOrDefault(curr, 0) + 1);
        ans += dfs(root.left, targetSum, curr);
        ans += dfs(root.right, targetSum, curr);
        map.put(curr, map.get(curr) - 1);
        return ans;
    }
}
```

结果：通过！ 时间复杂度O(n)，空间复杂度O(n)




























