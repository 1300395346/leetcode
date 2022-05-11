package 树;

import struct.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Offer050 {
    public static void main(String[] args) {
        Offer050 offer050 = new Offer050();
        TreeNode root = new TreeNode(10, new TreeNode(5, new TreeNode(3, new TreeNode(3), new TreeNode(-2)), new TreeNode(2, null, new TreeNode(1))), new TreeNode(-3, null, new TreeNode(11)));
        int ans = offer050.pathSum(root, 8);
        System.out.println(ans);
    }

    //前缀和
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

    //暴力破解
//    public int pathSum(TreeNode root, int targetSum) {
//        if (root == null) {
//            return 0;
//        }
//        int ans = dfs(root, targetSum);
//        ans += pathSum(root.left, targetSum);
//        ans += pathSum(root.right, targetSum);
//        return ans;
//    }
//
//    public int dfs(TreeNode root, int targetSum) {
//        if (root == null) {
//            return 0;
//        }
//        int ans = 0;
//        int temp = root.val;
//        if (temp == targetSum) {
//            ans++;
//        }
//        ans += dfs(root.left, targetSum - temp);
//        ans += dfs(root.right, targetSum - temp);
//        return ans;
//    }
}
